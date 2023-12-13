package BankingSystem.model;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class AccountInfo {
    int no;
    LocalDate dateOfOpening;

    @Override
    public String toString() {
        return  "no : "+no+"\ndateOfOpening : " + dateOfOpening ;
    }

    public AccountInfo(int no, LocalDate dateOfOpening) {
        this.no = no;
        this.dateOfOpening = dateOfOpening;

    }

    public int getNo() {
        return no;
    }

    public LocalDate getDateOfOpening() {
        return dateOfOpening;
    }


}
