package mishas.clientofapp.logic;

import java.util.ArrayList;
import java.util.List;

// данный класс заменят бд - в будущем всё будет реализовано через бд и этот класс будет удалён
public final class Administrator {

    private static volatile Administrator admin;

    private Administrator() {
        users = new ArrayList<>();
        cards = new ArrayList<>();
        bag = BagOfOrders.getInstance();
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

    public static List<User> users;
    public static List<BankCard> cards;
    public static BagOfOrders bag;


    private void addSomeUsers() {
        users.add(new User(0L, "misha", "123", "misha@mail.ru", "Mikhail", "Medvedev", 10, "89818542622"));
        users.add(new User(1L, "andr", "456", "adnr@mail.ru", "Andrey", "Chugunov", 10, "89812345433"));
        users.add(new User(2L, "alex", "789", "alex@mail.ru", "Alex", "Xela", 80, "89523134562"));
        users.add(new User(3L, "john", "012", "johb@mail.ru", "John", "Nhoj", 40, "89819874561"));
        users.add(new User(4L, "qwerty", "345", "asd@mail.ru", "QWERTY", "YTREWQ", 50, "89812135269"));
    }

    private void addBankCardsForUsers() {
        cards.add(new BankCard(0L, "4276550014789652", 3, 18, "Misha", 123));
        cards.add(new BankCard(1L, "5465824659861327", 4, 19, "Andrey", 456));
        cards.add(new BankCard(2L, "9614752364589820", 5, 20, "Alex", 789));
        cards.add(new BankCard(3L, "4203905602410388", 6, 21, "John", 147));
        cards.add(new BankCard(4L, "4285559321463985", 7, 22, "QWERTY", 258));
    }
}