package impl.products;

/**
 * Product
 */
public abstract class Product {
    private int id;

    public Product(int id) {
        this.id = id;
    }

    abstract public String getName();

    public int getId() {
        return id;
    }
}
