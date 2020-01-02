package impl.products;

public abstract class Product {
    private int id;
    abstract public int getUnitsPerTick();

    public Product(int id) {
        this.id = id;
    }

    abstract public String getName();

    public int getId() {
        return id;
    }

}
