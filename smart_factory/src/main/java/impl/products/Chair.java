package impl.products;

public class Chair extends Product {
    private final String name = "Chair";
    private final int unitsPerTick = 3;
    private final int material = 1;

    public Chair(int id) {
        super(id);
    }

    @Override
    public String getName() {
        return name;
    }
}
