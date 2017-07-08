package mishas.clientofapp.logic;

public class Product {

    private static long price;
    private ProductType type;
    private boolean isMade;

    public Product(){
        this.price = 0;
        this.type = null;
        this.isMade = false;
    }

    public Product(long price, ProductType type) {
        this.price = price;
        this.type = type;
        this.isMade = false;
    }

}