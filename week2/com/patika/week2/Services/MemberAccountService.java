package com.patika.week2.Services;

import java.util.ArrayList;
import com.patika.week2.Classes.MemberAccount;

public class MemberAccountService extends MemberAccount {

    protected static ArrayList<MemberAccountService> memberList = new ArrayList<>();
    private static int lastID = 0;

    public static void addMember(MemberAccountService m){
        memberList.add(m);
    }

    public static void removeMember(String memberCode){
        // Search the member list and find the index of it. If it exists, remove it.
        int memberIndex = memberList.indexOf(queryMember(memberCode));

        if (memberIndex != -1){
            memberList.remove(memberIndex);
        }
    }

    public static void removeMember(String memberName, String memberSurname, String memberPhone){
        // Search the member list and find the index of it. If it exists, remove it.
        int memberIndex = memberList.indexOf(queryMember(memberName, memberSurname, memberPhone));

        if (memberIndex != -1){
            memberList.remove(memberIndex);
        }
    }

    public static int queryMemberID(String memberCode){
        // Iterate in memberList and find the matching member code, then return ID.
        for (MemberAccountService m : memberList){
            if (m.getMemberCode().equals(memberCode)){
                return m.accountID;
            }
        }
        return -1;
    }

    public static MemberAccountService queryMember(String memberCode){
        // Iterate in memberList and find the matching member code, then return the member.
        for (MemberAccountService m : memberList){
            if (m.getMemberCode().equals(memberCode)){
                return m;
            }
        }
        return null;
    }

    public static MemberAccountService queryMember(String memberName, String memberSurname, String memberPhone){
        // Iterate in memberList and find the matching member full name combination, then return the member.
        for (MemberAccountService m : memberList){
            if ((m.getMemberName().equals(memberName)) && (m.getMemberSurname().equals(memberSurname)) && (m.getMemberPhone().equals(memberPhone))){
                return m;
            }
        }
        return null;
    }

    // Constructors
    public MemberAccountService() {}
    public MemberAccountService (String memberName, String memberSurname, String memberPhone, double accountBalance){
        this.accountID = lastID;
        this.accountBalance = accountBalance;
        this.memberName = memberName;
        this.memberSurname = memberSurname;
        this.memberPhone = memberPhone;
        this.memberCode = this.accountID + this.memberName.substring(0, 2);
        // This ensures that each member will have a unique ID.
        lastID++;
    }

    // Setters
    public void setAccountID(int accountID){
        this.accountID = accountID;
    }

    public void setAccountBalance(double accountBalance){
        this.accountBalance = accountBalance;
    }

    public void setMemberName(String memberName){
        this.memberName = memberName;
    }

    public void setMemberSurname(String memberSurname){
        this.memberSurname = memberSurname;
    }

    public void setMemberPhone(String memberPhone){
        this.memberPhone = memberPhone;
    }   

    public void updateMemberCode(){
        this.memberCode = this.accountID + this.memberName.substring(0, 2);
    }

    // Getters
    public int getAccountID(){
        return this.accountID;
    }

    public double getAccountBalance(){
        return this.accountBalance;
    }  

    public String getMemberName(){
        return this.memberName;
    }

    public String getMemberSurname(){
        return this.memberSurname;
    }

    public String getMemberPhone(){
        return this.memberPhone;
    }

    public String getMemberCode(){
        return this.memberCode;
    }

    @Override
    public String toString(){
        return String.format("%nAccount Information%nMember Code: %s%nAccount ID: %d%nMember Fullname: %s %s%nMember Phone Number: %s%nAccount Balance: %.2f%n%n", getMemberCode(), getAccountID(), getMemberName(), getMemberSurname(), getMemberPhone(), getAccountBalance());
    }

}
