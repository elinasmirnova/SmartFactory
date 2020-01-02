package impl.products;

public class Wardrobe extends Product {
    private final String name = "Wardrobe";
    private final int unitsPerTick = 1;
    private final int material = 4;

    public Wardrobe(int id) {
        super(id);
    }

    @Override
    public String getName() {
        return name;
    }

    public int getUnitsPerTick() {
        return unitsPerTick;
    }
}
