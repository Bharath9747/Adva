package BankingSystem.service.impl;

import BankingSystem.model.Account;
import BankingSystem.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static BankingSystem.repository.BankRepo.accountHashMap;
import static BankingSystem.repository.BankRepo.transactionList;

public class BankingServiceImpl{
    AtomicInteger transactionId = new AtomicInteger(1);
    public void withdrawFund(int accountNoWithdraw,BigDecimal withdraw) {
        Account acountFound1 = accountHashMap.values().stream().filter(e->e.getAccountInfo().getNo()==accountNoWithdraw).findFirst().orElse(null);
        if(acountFound1.equals(null))
            System.out.println("Account Not Found");
        else {
            BigDecimal afterWithdraw =acountFound1.getBalance().subtract(withdraw);
            if(afterWithdraw.compareTo(BigDecimal.ZERO)>0) {
                acountFound1.setBalance(afterWithdraw);
                addTransaction(new Transaction(transactionId.getAndIncrement(),accountNoWithdraw,withdraw, LocalDate.now(),1,afterWithdraw,"Withdraw Amount "+withdraw));
                System.out.println("Withdraw Done");
            }
            else
            {
                acountFound1.setBalance(afterWithdraw.add(withdraw));
                System.out.println("Not Withdraw Done");
            }
            accountHashMap.put(accountNoWithdraw, acountFound1);
        }


    }
    public BigDecimal getBalance(int accountNo) {
        return accountHashMap.values().stream().filter(e->e.getAccountInfo().getNo()==accountNo).findFirst().get().getBalance();
    }

    public void depositFund(int accountNoDeposit, BigDecimal deposit) {
        Account acountFound = accountHashMap.values().stream().filter(e->e.getAccountInfo().getNo()==accountNoDeposit).findFirst().orElse(null);
        if(acountFound.equals(null))
            System.out.println("Account Not Found");
        else {
            acountFound.setBalance(acountFound.getBalance().add(deposit));
            accountHashMap.put(accountNoDeposit, acountFound);
            addTransaction(new Transaction(transactionId.getAndIncrement(),accountNoDeposit,deposit, LocalDate.now(),1,acountFound.getBalance(),"Deposited Amount "+deposit));
            System.out.println("Deposit Done");
        }

    }

    public void createAccount(Account account) {
        accountHashMap.put(account.getAccountInfo().getNo(),account);
    }

    public List<Account> getAllZeroBalanceAccount() {
        return accountHashMap.values().stream().filter(c->c.getBalance().compareTo(new BigDecimal(0))==0).collect(Collectors.toList());
    }

    public List<Account> getAllNonKYCAccount() {
        return accountHashMap.values().stream().filter(c-> !c.getAadhar().isEmpty()).collect(Collectors.toList());
    }
    public  void levyBalance(List<Account> accountList) {
        if(!accountHashMap.isEmpty()) {
            Date timenow = new Date();
            int hrs = timenow.getHours();
            int min = timenow.getMinutes();
            if (hrs == 18 && min == 00)
                accountList.forEach(x -> x.setBalance(x.getBalance().subtract(new BigDecimal(500))));
//            System.out.println();
//            System.out.println("Levy Balance Updated");
//            System.out.println();
        }
    }

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    public void calculateInterest() {
        if(!accountHashMap.isEmpty()) {
            /*accountHashMap.forEach(((integer, account) ->
            {
                LocalDate date = account.getAccountInfo().getDateOfOpening();
                LocalDate date1 = date.plusDays(30);
                LocalDate dateNow = LocalDate.now();
                while (date1.compareTo(dateNow)<0) {
                    System.out.println(date1);
                    date1 = date1.plusDays(30);
                }
            }));*/
            System.out.println();
            accountHashMap.values().stream().filter(account -> account.getBalance().compareTo(BigDecimal.ZERO)!=0)
                    .map(account -> {account.setBalance(account.getBalance().add(account.getBalance().multiply(BigDecimal.valueOf(0.06))));
                    return account;}).forEach(System.out::println);
            System.out.println();
            System.out.println("Calculating Interest Updated");
            System.out.println();
        }
    }



}
