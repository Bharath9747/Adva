package BankingSystem.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    int id;
    BigDecimal amount;
    int accountNO;
    LocalDate dateofTransaction;
    int transactionType;
    BigDecimal balanceAfterTransaction;
    String comment;

    @Override
    public String toString() {
        return  "id : " + id +"\n"+"account No : " + accountNO +"\n"+
                "amount : " + amount +"\n"+
                "dateofTransaction : " + dateofTransaction +"\n"+
                "transactionType : " + transactionType +"\n"+
                "balanceAfterTransaction : " + balanceAfterTransaction +"\n"+
                "comment : " + comment + "\n";
    }

    public Transaction(int id,int accountNO, BigDecimal amount, LocalDate dateofTransaction, int transactionType, BigDecimal balanceAfterTransaction, String comment) {
        this.id = id;
        this.accountNO=accountNO;
        this.amount = amount;
        this.dateofTransaction = dateofTransaction;
        this.transactionType = transactionType;
        this.balanceAfterTransaction = balanceAfterTransaction;
        this.comment = comment;
    }
}
