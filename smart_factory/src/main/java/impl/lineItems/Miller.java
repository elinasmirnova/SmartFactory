package impl.lineItems;

import impl.visitor.Visitor;

public class Miller extends Machine {
    private final String name = "Miller";
    private final int oil = 3;
    private final int ec = 4;

    @Override
    public int getEC() {
        return ec;
    }

    public Miller(String id) {
        super(id);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int getOil() {
        return oil;
    }
}
