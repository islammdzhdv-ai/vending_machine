import model.BillAcceptor;
import model.CoinAcceptor;
import util.PaymentAcceptor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        PaymentAcceptor acceptor;

        while (true) {
            try {
                System.out.println("Выберите способ оплаты:");
                System.out.println("1 - Монеты");
                System.out.println("2 - Купюры");
                System.out.print("> ");

                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    throw new IllegalArgumentException("Пустой ввод!");
                }

                int choice = Integer.parseInt(input);

                if (choice == 1) {
                    acceptor = new CoinAcceptor(0);
                    break;
                } else if (choice == 2) {
                    acceptor = new BillAcceptor(0);
                    break;
                } else {
                    throw new IllegalArgumentException("Введите только 1 или 2!");
                }

            } catch (NumberFormatException e) {
                System.out.println("Ошибка: нужно вводить только цифры!");
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }
        }

        AppRunner app = new AppRunner(acceptor);
        app.runLoop();
    }
}
