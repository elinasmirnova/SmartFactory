package impl.lineItems;

import impl.visitor.Visitor;

public class Lathe extends Machine {
    private final String name = "Lathe";
    private final int oil = 2;
    public Lathe(String id) {
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
