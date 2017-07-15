package mishas.clientofapp.logic;

// This class is needed for other pay systems like YandexMoney, WebMoney, etc

public final class OtherCard {

    private final long id;
    private final String number;
    private final String password;

    public OtherCard(long id, String number, String password) {
        this.id = id;
        this.number = number;
        this.password = password;
    }

}