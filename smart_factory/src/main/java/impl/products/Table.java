package impl.products;

public class Table extends Product  {
    private final String name = "Table";
    public Table(int id) {
        super(id);
    }

    @Override
    public String getName() {
        return name;
    }
}
