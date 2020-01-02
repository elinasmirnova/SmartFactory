package impl.products;

public class Wardrobe extends Product {
    private final String name = "Wardrobe";

    public Wardrobe(int id) {
        super(id);
    }

    @Override
    public String getName() {
        return name;
    }
}
