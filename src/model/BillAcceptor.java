package model;

import util.PaymentAcceptor;
import java.util.Scanner;

public class BillAcceptor implements PaymentAcceptor {
    private int amount;
    private final Scanner scanner = new Scanner(System.in);

    public BillAcceptor(int initialAmount) {
        this.amount = initialAmount;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public void addAmount(int ignored) {
        System.out.print("Введите номинал купюры (50, 100, 200): ");
        String input = scanner.nextLine();

        try {
            int bill = Integer.parseInt(input);
            if (bill == 50 || bill == 100 || bill == 200) {
                amount += bill;
                System.out.println("Вы внесли " + bill);
            } else {
                System.out.println("Автомат не принимает такие купюры.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка ввода!");
        }
    }

    @Override
    public boolean deductAmount(int price) {
        if (amount >= price) {
            amount -= price;
            return true;
        }
        return false;
    }

    @Override
    public void showBalance() {
        System.out.println("Баланс (купон): " + amount);
    }
}
