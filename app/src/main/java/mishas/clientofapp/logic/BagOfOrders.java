package mishas.clientofapp.logic;

import java.util.List;

public final class BagOfOrders {

    private static volatile BagOfOrders bagOfOrders;

    private BagOfOrders() {

    }

    public static BagOfOrders getInstance() {
        if (bagOfOrders == null)
            synchronized (BagOfOrders.class){
                if (bagOfOrders == null)
                    bagOfOrders = new BagOfOrders();
            }
        return bagOfOrders;
    }

    public List<Product> products;



}