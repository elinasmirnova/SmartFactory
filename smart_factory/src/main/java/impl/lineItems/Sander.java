package impl.lineItems;

import impl.visitor.Visitor;

public class Sander extends Machine {
    private final String name = "Sander";
    private final int oil = 2;
    private final int ec = 4;

    @Override
    public int getEC() {
        return ec;
    }

    @Override
    public int getOil() {
        return oil;
    }

    public Sander(String id) {
        super(id);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
