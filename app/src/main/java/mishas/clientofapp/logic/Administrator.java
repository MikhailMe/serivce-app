package mishas.clientofapp.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// данный класс заменят бд - в будущем всё будет реализовано через бд и этот класс будет удалён
public final class Administrator {

    private static volatile Administrator admin;

    private Administrator() {
        products = new HashMap<>();
        prices = new HashMap<>();
        users = new ArrayList<>();
        cards = new ArrayList<>();
        bag = BagOfOrders.getInstance();
        makeProducts();
        prices();
        addSomeUsers();
        addBankCardsForUsers();
    }

    public static Administrator getInstance() {
        if (admin == null)
            synchronized (Administrator.class) {
                if (admin == null)
                    admin = new Administrator();
            }
        return admin;
    }

    public static Map<ProductType, Integer> products;
    public static Map<ProductType, Integer> prices;
    public static List<User> users;
    public static List<BankCard> cards;
    public static BagOfOrders bag;

    public static String makeOrderString(){
        StringBuilder sb = new StringBuilder();
        int amount, sum = 0, temp = 0;
        for (ProductType type : ProductType.values()){
            amount = products.get(type);
            if (amount != 0) {
                temp = amount * prices.get(type);
                sum += temp;
                sb.append(type).append(": ").append(amount).append("  = ").append(temp).append("rub").append("\n");
            }
        }
        sb.append("------------------------------------------\n").append("The sum of your order is ").append(sum);
        return sb.toString();
    }


    // потом брать всё из бд
    private static Map<ProductType, Integer> prices() {
        prices.put(ProductType.HOTDOG, 250);
        prices.put(ProductType.BURGER, 250);
        prices.put(ProductType.HOTCORN, 150);
        prices.put(ProductType.CHIPS, 100);
        prices.put(ProductType.TEA, 150);
        prices.put(ProductType.COFFEE, 150);
        prices.put(ProductType.WATER, 100);
        prices.put(ProductType.JUICE, 150);
        prices.put(ProductType.BEER, 150);
        return prices;
    }

    private void makeProducts(){
        products.put(ProductType.HOTDOG, 0);
        products.put(ProductType.BURGER, 0);
        products.put(ProductType.HOTCORN, 0);
        products.put(ProductType.CHIPS, 0);
        products.put(ProductType.TEA, 0);
        products.put(ProductType.COFFEE, 0);
        products.put(ProductType.WATER, 0);
        products.put(ProductType.JUICE, 0);
        products.put(ProductType.BEER, 0);
    }

    private void addSomeUsers() {
        users.add(new User(0L, "misha", "123", "misha@mail.ru", "Mikhail", "Medvedev", 10, "89818542622", true));
        users.add(new User(1L, "andr", "456", "adnr@mail.ru", "Andrey", "Chugunov", 10, "89812345433", true));
        users.add(new User(2L, "alex", "789", "alex@mail.ru", "Alex", "Xela", 80, "89523134562", true));
        users.add(new User(3L, "john", "012", "johb@mail.ru", "John", "Nhoj", 40, "89819874561", true));
        users.add(new User(4L, "qwerty", "345", "asd@mail.ru", "QWERTY", "YTREWQ", 50, "89812135269", true));
    }

    private void addBankCardsForUsers() {
        cards.add(new BankCard(0L, "4276550014789652", 3, 18, "Misha", 123));
        cards.add(new BankCard(1L, "5465824659861327", 4, 19, "Andrey", 456));
        cards.add(new BankCard(2L, "9614752364589820", 5, 20, "Alex", 789));
        cards.add(new BankCard(3L, "4203905602410388", 6, 21, "John", 147));
        cards.add(new BankCard(4L, "4285559321463985", 7, 22, "QWERTY", 258));
    }
}