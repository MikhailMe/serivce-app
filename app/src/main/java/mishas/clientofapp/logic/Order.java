package mishas.clientofapp.logic;

import java.util.HashMap;
import java.util.Map;

public final class Order {

    private long id;
    private Map<Product, Integer> products;
    public boolean isPaid;  // оплачен и готов к отправке в магазин
    public boolean isDelivered; // заказ забрали - удаляется по этому флагу
    public boolean isMade; // можно забирать

    public Order() {
        this.products = new HashMap<>();
        this.isPaid = false;
        this.isDelivered = false;
        this.isMade = false;
    }

    public String getOrderString() {
        if (products.isEmpty()){
            return "Sorry, but your bag is empty! :(";
        } else{
            StringBuilder sb = new StringBuilder();
            Product curProduct;
            int amount, curSum, sum = 0;
            for (Map.Entry<Product, Integer> product : products.entrySet()) {
                curProduct = product.getKey();
                amount = product.getValue();
                curSum = curProduct.getPrice() * amount;
                sum += curSum;
                sb.append(curProduct.getType()).append(" x ").append(amount).append(" = ").append(curSum).append("\n");
            }
            sb.append("----------------------------------------\n").append("The sum of your order is ").append(sum).append("rub");
            return sb.toString();
        }
    }

    public String makeSendString() {
        StringBuilder sb = new StringBuilder();
        sb.append("productList={");
        boolean flag = true;
        for (Map.Entry<Product, Integer> product : products.entrySet()) {
            if (flag){
                flag = false;
            } else sb.append(", ");
            sb.append(product.getKey().toString()).append("=").append(product.getValue());
        }
        sb.append("}");
        return sb.toString();
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }
}