package mishas.clientofapp.logic;

import java.util.LinkedList;
import java.util.List;

public class Product {

    private long price;
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

    // потом брать всё из бд
    public static List<Product> madeProducts() {
        List<Product> products = new LinkedList<>();
        products.add(new Product(250, ProductType.HOTDOG));
        products.add(new Product(250, ProductType.BURGER));
        products.add(new Product(150, ProductType.HOTCORN));
        products.add(new Product(150, ProductType.TEA));
        products.add(new Product(150, ProductType.COFFEE));
        products.add(new Product(150, ProductType.WATER));
        products.add(new Product(150, ProductType.JUICE));
        products.add(new Product(150, ProductType.BEER));
        return products;
    }
}