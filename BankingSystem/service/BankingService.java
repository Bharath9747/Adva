package BankingSystem.service;

import BankingSystem.model.Account;
import BankingSystem.model.Transaction;
import BankingSystem.service.impl.BankingServiceImpl;

import java.math.BigDecimal;
import java.util.List;

public class BankingService {
    BankingServiceImpl bankingService = new BankingServiceImpl();
    public void withdrawFund(int accountNoWithdraw, BigDecimal withdraw) {
        bankingService.withdrawFund(accountNoWithdraw,withdraw,"Withdraw Amount "+withdraw);
    }
    public BigDecimal getBalance(int accountNo){
        return bankingService.getBalance(accountNo);
    }
    public void depositFund(int accountNoDeposit,BigDecimal deposit) {
        bankingService.depositFund(accountNoDeposit,deposit,"Deposited Amount "+deposit);
    }

    public void createAccount(Account account){
        bankingService.createAccount(account);
    }

    public List<Account> getAllZeroBalanceAccount(){
        return getAllZeroBalanceAccount();
    }
    public List<Account> getAllNonKYCAccount(){
        return bankingService.getAllNonKYCAccount();
    }

    public void levyBalanceifZero() {
        List<Account> accountList = bankingService.getAllZeroBalanceAccount();
        bankingService.levyBalance(accountList);
    }


    public void calculateInterest(){

        bankingService.calculateInterest();
    }

}
