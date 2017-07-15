package mishas.clientofapp.logic;

public final class Product {

    private long id;
    private int price;
    private ProductType type;

    public Product(ProductType type, int price) {
        this.type = type;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return "Product(productType=" +
                type + ", price=" + price + ".0)";

    }
}