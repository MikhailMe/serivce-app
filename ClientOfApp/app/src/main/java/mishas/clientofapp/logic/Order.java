package mishas.clientofapp.logic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Order {

    private long id;
    private Map<Product, Integer> products;
    public boolean isPaid;  // оплачен и готов к отправке в магазин
    public boolean isDelivered; // заказ забрали - удаляется по этому флагу
    public boolean isMade; // можно забирать

    private List<ProductType> list = Arrays.asList(ProductType.HOT_DOG,
            ProductType.HAMBURGER,
            ProductType.HOT_CORN,
            ProductType.CHIPS,
            ProductType.TEA,
            ProductType.COFFEE,
            ProductType.WATER,
            ProductType.JUICE);
    private String[] productArray = {
            "Хот-дог",
            "Гамбургер",
            "Кукуруза",
            "Чипсы",
            "Чай",
            "Кофе",
            "Вода",
            "Сок"
    };

    public Order() {
        this.products = new HashMap<>();
        this.isPaid = false;
        this.isDelivered = false;
        this.isMade = false;
    }

    public String getOrderString() {
        if (products.isEmpty()){
            return "Ваша корзина пуста! :(";
        } else{
            StringBuilder sb = new StringBuilder();
            Product curProduct;
            int amount, curSum, sum = 0;
            for (Map.Entry<Product, Integer> product : products.entrySet()) {
                curProduct = product.getKey();
                amount = product.getValue();
                curSum = curProduct.getPrice() * amount;
                sum += curSum;
                sb.append(productArray[list.indexOf(curProduct.getType())]).append(" x ").append(amount).append(" = ").append(curSum).append(" руб").append("\n");
            }
            sb.append("----------------------------------------\n").append("Сумма вашего заказа - ").append(sum).append("руб");
            return sb.toString();
        }
    }

    public String makeSendString() {
        if (products.isEmpty())
            return null;
        else {
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
    }

    public void setProducts(Map<Product, Integer> products) {
        this.products = products;
    }
}