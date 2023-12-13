package BankingSystem;

import BankingSystem.model.Account;
import BankingSystem.model.AccountInfo;
import BankingSystem.model.Address;
import BankingSystem.service.BankingService;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static BankingSystem.repository.BankRepo.accountHashMap;
import static BankingSystem.repository.BankRepo.transactionList;

public class MainRunner extends TimerTask{
//

    static BankingService bankingService = new BankingService();
    //LevyBalances
    public void run() {
        bankingService.levyBalanceifZero();
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AtomicInteger accNo = new AtomicInteger(1);
        List<String> accountInputs = new ArrayList<>();
        Timer timer = new Timer();
        TimerTask task = new MainRunner();
        timer.schedule(task,10000);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                bankingService.calculateInterest();
            }
        }, 10, 20, TimeUnit.SECONDS);
        try{
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            String s;
            while((s=reader.readLine())!=null)
                accountInputs.add(s);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while(true)
        {
            System.out.println("1.Add account");
            System.out.println("2.Get Balance");
            System.out.println("3.Deposit");
            System.out.println("4.Withdraw");
            System.out.println("5.Find All account with 0 balance");
            System.out.println("6.isKycDone");
            int ch = sc.nextInt();
            switch (ch)
            {
                case 1: int accountNo = accNo.getAndIncrement();
                        String[] accountInputString = accountInputs.get(accountNo-1).split(" ");
                        int year=Integer.parseInt(accountInputString[0]);
                        int month = Integer.parseInt(accountInputString[1]);
                        int day = Integer.parseInt(accountInputString[2]);
                        LocalDate dateOfOpening = LocalDate.of(year,month,day);
                        String accountHolderName = accountInputString[3];
                        String city  = accountInputString[4];
                        String state = accountInputString[5];
                        String pincode = accountInputString[6];
                        String address = accountInputString[7];
                        String phno = accountInputString[8];
                        BigDecimal balance = new BigDecimal(Long.parseLong(accountInputString[9]));
                        String aadhar = accountInputString.length==10?"":accountInputString[10];
                        Account account = new Account(new AccountInfo(accountNo,dateOfOpening),accountHolderName,new Address(city,state,pincode,address),phno,balance,aadhar);
                        bankingService.createAccount(account);

                        break;
                case 2 : int accountNoSearch = sc.nextInt();
                    System.out.println("Balance for Account number "+accountNoSearch+" : "+bankingService.getBalance(accountNoSearch));
                    break;
                case 3 : int accountNoDeposit = sc.nextInt();
                    BigDecimal deposit = sc.nextBigDecimal();
                    bankingService.depositFund(accountNoDeposit,deposit);
                    break;
                case 4 : int accountNoWithdraw = sc.nextInt();
                    BigDecimal withdraw = sc.nextBigDecimal();
                    bankingService.withdrawFund(accountNoWithdraw,withdraw);
                    break;
                case 5 : bankingService.getAllZeroBalanceAccount().forEach(System.out::println);
                break;
                case 6 :
                    bankingService.getAllNonKYCAccount().forEach(System.out::println);
                    break;
                default: scheduledExecutorService.shutdown();
                        System.exit(0);
            }
            System.out.println();
            System.out.println("Bank Accounts After Process");
            accountHashMap.values().forEach(System.out::println);
            System.out.println();
            System.out.println("Transaction History For all Process");
            transactionList.forEach(System.out::println);
            System.out.println();
        }
    }

}
