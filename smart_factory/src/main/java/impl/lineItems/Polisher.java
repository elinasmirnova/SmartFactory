package impl.lineItems;

import impl.visitor.Visitor;

public class Polisher extends Machine {
    private final String name = "Polisher";

    private final int oil = 4;

    public Polisher(String id) {
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
