package impl.lineItems;

import impl.visitor.Visitor;

public class Saw extends Machine {
    private final String name = "Saw";
    private final int oil = 2;
    public Saw(String id) {
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
