package BankingSystem.repository;

import BankingSystem.model.Account;
import BankingSystem.model.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BankRepo {
    public static HashMap<Integer, Account> accountHashMap = new HashMap<>();
    public static List<Transaction> transactionList = new ArrayList<>();

}
