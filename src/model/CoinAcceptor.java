package model;

import util.PaymentAcceptor;

public class CoinAcceptor implements PaymentAcceptor {
    private int amount;

    public CoinAcceptor(int initialAmount) {
        this.amount = initialAmount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void addAmount(int amount) {
        this.amount += amount;
    }

    @Override
    public boolean deductAmount(int amount) {
        if (this.amount >= amount) {
            this.amount -= amount;
            return true;
        }
        return false;
    }

    @Override
    public void showBalance() {
        System.out.println("Баланс: " + amount);
    }
}
