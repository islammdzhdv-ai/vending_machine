package util;

public interface PaymentAcceptor {
    int getAmount();
    void addAmount(int amount);
    boolean deductAmount(int amount);
    void showBalance();
}