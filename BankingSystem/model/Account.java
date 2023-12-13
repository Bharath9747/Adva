package BankingSystem.model;


import java.math.BigDecimal;
import java.util.Objects;

public class Account   {
    AccountInfo accountInfo;
    String name;
    Address address;
    String phNo;
    BigDecimal balance;
    String aadhar;

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }


    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;
        return Objects.equals(accountInfo, account.accountInfo) && Objects.equals(name, account.name) && Objects.equals(address, account.address) && Objects.equals(phNo, account.phNo) && Objects.equals(balance, account.balance) && Objects.equals(aadhar, account.aadhar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountInfo, name, address, phNo, balance, aadhar);
    }

    @Override
    public String toString() {
        return  "" + accountInfo + "\nname : "+name+ address +
                "phNo : " + phNo + "\n" +
                "balance : " + balance +
                "\naadhar : " + aadhar ;
    }


    public Account(AccountInfo accountInfo,String name, Address address, String phNo, BigDecimal balance, String aadhar) {
        this.accountInfo = accountInfo;
        this.name = name;
        this.address = address;
        this.phNo = phNo;
        this.balance = balance;
        this.aadhar = aadhar;

    }

    public Address getAddress() {
        return address;
    }

    public String getPhNo() {
        return phNo;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getAadhar() {
        return aadhar;
    }



    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }



}
