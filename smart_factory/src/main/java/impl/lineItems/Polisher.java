package impl.lineItems;

import impl.visitor.Visitor;

public class Polisher extends Machine {
    private final String name = "Polisher";
    private final int oil = 2;
    private final int ec = 4;
    private final int mat = 1;

    public Polisher(String id) {
        super(id);
    }
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int getEC() {
        return ec;
    }

    @Override
    public int getMat() {
        return mat;
    }

    @Override
    public int getOil() {
        return oil;
    }

    @Override
    public String getName() {
        return name;
    }
}
