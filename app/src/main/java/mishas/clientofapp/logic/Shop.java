package mishas.clientofapp.logic;

import java.util.LinkedList;
import java.util.Queue;

public class Shop {

    private long id;
    private long sectorId;
    private ShopType type;
    private Queue<Order> orders;

    public Shop(long id, long sectorId, ShopType type){
        this.id = id;
        this.sectorId = sectorId;
        this.type = type;
        orders = new LinkedList<>();
    }

    public Shop(long id, long sectorId, ShopType type, Queue<Order> orders) {
        this.id = id;
        this.sectorId = sectorId;
        this.type = type;
        this.orders = orders;
    }

    public void comeOrder(Order order){
        orders.add(order);
    }

    public void delOrder(){

    }
}