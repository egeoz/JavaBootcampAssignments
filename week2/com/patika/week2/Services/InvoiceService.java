package com.patika.week2.Services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.patika.week2.Classes.Invoice;

public class InvoiceService extends Invoice{

    protected static ArrayList<InvoiceService> invoiceList = new ArrayList<>();
    private static int lastID = 0;

    public static Response newInvoice(InvoiceService invoice) throws InsufficentBalanceException{
        // Before proceeding, check if the account balance will be negative after the issuance of the invoice. If so, throw a custom exception.
        if (MemberAccountService.queryMember(invoice.memberCode).getAccountBalance() < invoice.invoiceAmount){    
            throw new InsufficentBalanceException(MemberAccountService.queryMember(invoice.memberCode).getAccountBalance(), invoice.invoiceAmount);
        }
        invoiceList.add(invoice);
        return new Response("0");
    }

    public static Response cancelInvoice(String memberCode, int billType, double invoiceAmount, String processDate){
        // Search the invoice list and find the index of it. If it exists, remove it.
        int invoiceIndex = invoiceList.indexOf(queryInvoice(billType, memberCode, invoiceAmount, processDate));

        if (invoiceIndex != -1){
            invoiceList.remove(invoiceIndex);
            return new Response("0");
        }
        return new Response("1");
    }

    public static int queryInvoiceID(int billType, String memberCode, double invoiceAmount){        
        // Iterate in invoiceList and find the matching invoice, then return ID.
        for (InvoiceService invoice : invoiceList){
                if ((invoice.memberCode.equals(memberCode)) && (invoice.billType == billType) && (invoice.invoiceAmount == invoiceAmount)){
                    return invoice.invoiceID;
                }
        }

        return -1;
    }

    public static InvoiceService queryInvoice(int billType, String memberCode, double invoiceAmount, String processDate){        
        // Iterate in invoiceList and find the matching invoice, then return the invoice.
        for (InvoiceService invoice : invoiceList){
            if ((invoice.memberCode.equals(memberCode)) && (invoice.billType == billType) && (invoice.invoiceAmount == invoiceAmount) && (invoice.processDate.equals(processDate))){
                return invoice;
            }
        }

        return null;
    }

    // Constructors
    public InvoiceService(){}
    public InvoiceService(int billType, String memberCode, double invoiceAmount) {
        this.invoiceID = lastID;
        this.billType = billType;
        this.invoiceAmount = invoiceAmount;
        this.memberCode = memberCode;
        this.processDate = getCurrentDate();
        // This ensures that each member will have a unique ID.
        lastID++;
    }

    private String getCurrentDate() {
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormatter.format(new Date());
    }

    // Getters
    public int getInvoiceID() {
        return this.invoiceID;
    }

    public int getBillType() {
        return this.billType;
    }

    public double getInvoiceAmount() {
        return this.invoiceAmount;
    }

    public String getMemberCode() {
        return this.memberCode;
    }

    public String getProcessDate(){
        return this.processDate;
    }

    // Setters
    public void setBillType(int billType){
        this.billType = billType;
    }

    public void setInvoiceAmount(double invoiceAmount){
        this.invoiceAmount = invoiceAmount;
    }
    
    public void setMemberCode(String memberCode){
        this.memberCode = memberCode;
    }

    public void setProcessDate(String processDate){
        this.processDate = processDate;
    }

    @Override
    public String toString(){
        String billTypeName;
        switch (billType){
            case 1:
                billTypeName = "Phone";
                break;
            case 2:
                billTypeName = "Internet";
                break;
            case 3:
                billTypeName = "Water";
                break;
            default:
                billTypeName = "Invalid";
                break;
        }
        return String.format("%nInvoice Information%nMember Code: %s%nInvoice ID: %d%nBill Type: %s%nProcess Date: %s%nInvoice Amount: %.2f%n%n", getMemberCode(), getInvoiceID(), billTypeName, getProcessDate(), getInvoiceAmount());
    }

}
