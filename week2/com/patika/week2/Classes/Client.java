package com.patika.week2.Classes;

import com.patika.week2.Services.InvoiceService;

public class Client extends InvoiceService {
    public static double queryInvoice(int billType, String memberCode){
        // Iterate in invoiceList and find the matching invoice, then return the invoice amount.
        for (InvoiceService invoice : InvoiceService.invoiceList){
            if ((invoice.memberCode.equals(memberCode)) && (invoice.billType == billType)){
                return invoice.invoiceAmount;
            }
        }

        return -1;
    }

    public static double queryInvoice(int billType, String memberCode, String processDate){
        // Iterate in invoiceList and find the matching invoice, then return the invoice amount.
        for (InvoiceService invoice : InvoiceService.invoiceList){
            if ((invoice.memberCode.equals(memberCode)) && (invoice.billType == billType) && (invoice.processDate.equals(processDate))){
                return invoice.invoiceAmount;
            }
        }

        return -1;    
    }


}
