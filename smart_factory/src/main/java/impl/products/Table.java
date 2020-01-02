package impl.products;

public class Table extends Product  {
    private final String name = "Table";
    private int material = 10;

    private final int unitsPerTick = 2;
    public Table(int id) {
        super(id);
    }

    @Override
    public String getName() {
        return name;
    }
}
