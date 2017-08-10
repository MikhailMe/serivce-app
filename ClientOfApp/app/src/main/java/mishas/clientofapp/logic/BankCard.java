package mishas.clientofapp.logic;

public final class BankCard {

    private long id;
    private long userId;
    private String number;
    private int month;
    private int  year;
    private String name;
    private int ccv;

    public BankCard(long userId, String number, int month, int year, String name, int ccv) {
        this.userId = userId;
        this.number = number;
        this.month = month;
        this.year = year;
        this.name = name;
        this.ccv = ccv;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCcv() {
        return ccv;
    }

    public void setCcv(int ccv) {
        this.ccv = ccv;
    }
}