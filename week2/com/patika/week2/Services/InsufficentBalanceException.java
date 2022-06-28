package com.patika.week2.Services;

public class InsufficentBalanceException extends Exception{
    public InsufficentBalanceException(double currentBalance, double invoiceAmount){
        System.err.printf("Account has insufficient balance. Current balance is %d and invoice amount is %d.%n", currentBalance, invoiceAmount);
    }
    
}
