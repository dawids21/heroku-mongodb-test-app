package xyz.stasiak.herokumongodbtestapp;

import java.math.BigDecimal;

public class CreditCard {

    private String number;
    private BigDecimal balance;

    public CreditCard() {
    }

    public CreditCard(String number, BigDecimal balance) {
        this.number = number;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
