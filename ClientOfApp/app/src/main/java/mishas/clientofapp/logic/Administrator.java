package mishas.clientofapp.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

// данный класс заменяет совокупность бд - в будущем всё будет реализовано через бд и этот класс будет удалён
public final class Administrator {

    private static volatile Administrator admin = null;

    public static Administrator getAdmin() {
        if (admin == null) {
            synchronized (Administrator.class) {
                if (admin == null)
                    admin = new Administrator();
            }
        }
        return admin;
    }

    public static Map<ProductType, Integer> products;
    private static Map<ProductType, Integer> prices;
    public static List<User> users;
    public static List<BankCard> cards;
    public static User currentUser;
    public static Order currentOrder;
    public static long currentUserId;

    private Administrator() {
        products = new HashMap<>();
        prices = new HashMap<>();
        users = new ArrayList<>();
        cards = new ArrayList<>();
        makeProducts();
        prices();
        addSomeUsers();
        addBankCardsForUsers();
        currentUserId = 5L;
    }

    private static String generatePassword() {
        StringBuilder sb = new StringBuilder();
        String alphabet = "abcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+=-";
        for (int i = 0; i < 5; i++) {
            sb.append(alphabet.charAt(new Random().nextInt(alphabet.length() + 1)));
        }
        return sb.toString();
    }

    public static boolean isExist(String email) {
        for (User user : users)
            if (user.getEmail().equals(email)) {
                String newPassword = generatePassword();
                user.setPassword(newPassword);
                return true;
            }
        return false;
    }

    public static Map<Product, Integer> productsToOrder() {
        Map<Product, Integer> order = new HashMap<>();
        ProductType type;
        int amount, price;
        for (Map.Entry<ProductType, Integer> prod : products.entrySet()) {
            amount = prod.getValue();
            if (amount != 0) {
                type = prod.getKey();
                price = prices.get(type);
                order.put(new Product(type, price), amount);
            }
        }
        return order;
    }

    /*
    *
    * Epic database
    *
    * */

    private static Map<ProductType, Integer> prices() {
        prices.put(ProductType.HOT_DOG, 100);
        prices.put(ProductType.HAMBURGER, 150);
        prices.put(ProductType.HOT_CORN, 50);
        prices.put(ProductType.CHIPS, 100);
        prices.put(ProductType.TEA, 100);
        prices.put(ProductType.COFFEE, 150);
        prices.put(ProductType.WATER, 100);
        prices.put(ProductType.JUICE, 120);
        return prices;
    }

    private static void makeProducts() {
        products.put(ProductType.HOT_DOG, 0);
        products.put(ProductType.HAMBURGER, 0);
        products.put(ProductType.HOT_CORN, 0);
        products.put(ProductType.CHIPS, 0);
        products.put(ProductType.TEA, 0);
        products.put(ProductType.COFFEE, 0);
        products.put(ProductType.WATER, 0);
        products.put(ProductType.JUICE, 0);
    }

    private static void addSomeUsers() {
        users.add(new User(0L, "misha", "123", "misha@mail.ru", true));
        users.add(new User(1L, "andr", "456", "adnr@mail.ru", true));
        users.add(new User(2L, "alex", "789", "alex@mail.ru", true));
        users.add(new User(3L, "john", "012", "johb@mail.ru", true));
        users.add(new User(4L, "qwerty", "345", "asd@mail.ru", true));
    }

    private static void addBankCardsForUsers() {
        cards.add(new BankCard(0L, "4276550014789652", 3, 18, "Misha", 123));
        cards.add(new BankCard(1L, "5465824659861327", 4, 19, "Andrey", 456));
        cards.add(new BankCard(2L, "9614752364589820", 5, 20, "Alex", 789));
        cards.add(new BankCard(3L, "4203905602410388", 6, 21, "John", 147));
        cards.add(new BankCard(4L, "4285559321463985", 7, 22, "QWERTY", 258));
    }
}