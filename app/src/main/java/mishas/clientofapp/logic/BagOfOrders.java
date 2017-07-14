package mishas.clientofapp.logic;

public final class BagOfOrders {

    private static volatile BagOfOrders bagOfOrders;
    private Order order;

    private BagOfOrders() {

    }

    public static BagOfOrders getInstance() {
        if (bagOfOrders == null)
            synchronized (BagOfOrders.class) {
                if (bagOfOrders == null)
                    bagOfOrders = new BagOfOrders();
            }
        return bagOfOrders;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

}