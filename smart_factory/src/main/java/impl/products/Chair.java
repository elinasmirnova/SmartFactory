package impl.products;

public class Chair extends Product {
    private final String name = "Chair";
    public Chair(int id) {
        super(id);
    }

    @Override
    public String getName() {
        return name;
    }
}
