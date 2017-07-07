package mishas.clientofapp.logic;

public final class BankCard {

    private final class Date{

        private int month;
        private int year;

        public Date(int month, int year) {
            this.month = month;
            this.year = year;
        }
    }

    private long id;
    private long userId;
    private String number;
    private Date date;
    private String name;
    private int ccv;

    public BankCard(long userId, String number, int month, int year, String name, int ccv) {
        this.userId = userId;
        this.number = number;
        this.date = new Date(month, year);
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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