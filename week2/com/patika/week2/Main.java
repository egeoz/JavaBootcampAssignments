package com.patika.week2;

import com.patika.week2.Services.InsufficentBalanceException;
import com.patika.week2.Services.InvoiceService;
import com.patika.week2.Services.MemberAccountService;
import com.patika.week2.Services.Response;

public class Main {
    public static void main(String[] args) throws InsufficentBalanceException{

        MemberAccountService mem;
        InvoiceService inv;
        Response res;

        // Create a new member and add it to memberList.
        mem = new MemberAccountService("Ege", "Öz", "+90 555 555 5555", 100.0);
        MemberAccountService.addMember(mem);
        // Print out the account details by querying for member.
        System.out.print((MemberAccountService.queryMember("Ege", "Öz", "+90 555 555 5555").toString()));
        
        // Create another member.
        MemberAccountService.addMember(new MemberAccountService("Ege2", "Öz2", "+90 666 666 6666", 95.0));
        // Remove a member.
        MemberAccountService.removeMember("Ege", "Öz", "+90 666 666 6666");
        
        inv = new InvoiceService(1, mem.getMemberCode(), 85.5);
        
        // Attempt to an invoice and check if the to be net balance is negative.
        try {
            res = InvoiceService.newInvoice(inv);
            if (res.getReturnStatus().equals("0")){
                System.out.printf("Invoice creation order was successfully completed at %s.%n", res.getTimestamp());
            }
        } catch (InsufficentBalanceException e) {
            throw new InsufficentBalanceException(mem.getAccountBalance(), inv.getInvoiceAmount());
        }

        System.out.print(inv.toString());

        // Remove an invoice
        res = InvoiceService.cancelInvoice(inv.getMemberCode(), inv.getBillType(), inv.getInvoiceAmount(), inv.getProcessDate());
        if (res.getReturnStatus().equals("0")){
            System.out.printf("Invoice cancellation order was successfully completed at %s.%n", res.getTimestamp());
        }else{
            System.out.printf("Invoice cancellation order was failed at %s.%n", res.getTimestamp());
        }

    }
}
