package impl.products;

public class Table extends Product  {
    private final String name = "Table";
    private final int material = 3;


    private final int unitsPerTick = 2;
    public Table(int id) {
        super(id);
    }

    @Override
    public String getName() {
        return name;
    }
}
