package mishas.clientofapp.logic;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private long id;
    private List<Product> products;
    public boolean isPaid;
    public boolean isDelivered;
    public boolean isMade;

    public Order() {
        this.products = new ArrayList<>();
        this.isPaid = false;
        this.isDelivered = false;
        this.isMade = false;
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public static Order makeOrder(){
        return new Order();
    }
}