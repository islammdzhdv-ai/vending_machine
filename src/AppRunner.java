import enums.ActionLetter;
import model.*;
import util.UniversalArray;
import util.UniversalArrayImpl;
import java.util.Scanner;

public class AppRunner {
    private final UniversalArray<Product>products = new UniversalArrayImpl<>();
    private final CoinAcceptor coinAcceptor;
    private final Scanner scanner = new Scanner(System.in);
    private static boolean isExit = false;
    private AppRunner() { products.addAll(new Product[]{
            new Water(ActionLetter.B, 20),
            new CocaCola(ActionLetter.C, 50),
            new Soda(ActionLetter.D, 30),
            new Snickers(ActionLetter.E, 80),
            new Mars(ActionLetter.F, 80),
            new Pistachios(ActionLetter.G, 130) });
        coinAcceptor = new CoinAcceptor(100);
    }

    public static void run() {
        AppRunner app = new AppRunner();
        while (!isExit) { app.startSimulation();
        }
    }

    private void startSimulation() {
        print("В автомате доступны:");
        showProducts(products);
        print("Монет на сумму: " + coinAcceptor.getAmount());
        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        allowProducts.addAll(getAllowedProducts().toArray());
        chooseAction(allowProducts);
    }

    private UniversalArray<Product> getAllowedProducts() {
        UniversalArray<Product> allowProducts = new UniversalArrayImpl<>();
        for (int i = 0; i < products.size(); i++) {
            if (coinAcceptor.getAmount() >= products.get(i).getPrice())
            {
                allowProducts.add(products.get(i));
            }
        }
        return allowProducts;
    }

    private void chooseAction(UniversalArray<Product> products) {
        print(" a - Пополнить баланс");
        showActions(products);
        print(" h - Выйти");

        String input = fromConsole();
        if (input.isEmpty()) {
            print("Пустой ввод. Попробуйте ещё раз.");
            return;
        }

        String action = input.substring(0, 1);

        if ("a".equalsIgnoreCase(action)) {
            coinAcceptor.setAmount(coinAcceptor.getAmount() + 10);
            print("Вы пополнили баланс на 10");
            return;
        }

        if ("h".equalsIgnoreCase(action)) {
            isExit = true;
            return;
        }

        ActionLetter chosenLetter;
        try {
            chosenLetter = ActionLetter.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            print("Недопустимая буква. Попробуйте ещё раз.");
            return;
        }

        boolean bought = false;
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            if (p.getActionLetter() == chosenLetter) {
                if (coinAcceptor.getAmount() >= p.getPrice()) {
                    coinAcceptor.setAmount(coinAcceptor.getAmount() - p.getPrice());
                    print("Вы купили " + p.getName());
                    bought = true;
                } else {
                    print("Недостаточно средств для " + p.getName());
                }
                break;
            }
        }

        if (!bought) {
            print("Товар с такой буквой не найден в доступных продуктах.");
        }
    }

    private void showActions(UniversalArray<Product> products) {

        for (int i = 0; i < products.size(); i++) {

            print(String.format(" %s - %s", products.get(i).getActionLetter().getValue(), products.get(i).getName()));
        }
    }

    private String fromConsole() {
        String line = scanner.nextLine();
        return line == null ? "" : line.trim();
    }

    private void showProducts(UniversalArray<Product> products) {

        for (int i = 0; i < products.size(); i++)
        {
            print(products.get(i).toString());
        }
    }

    private void print(String msg) { System.out.println(msg);

    }
}