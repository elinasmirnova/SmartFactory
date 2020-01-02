package impl.lineItems;

import impl.visitor.Visitor;

public class Miller extends Machine {
    private final String name = "Miller";
    private final int oil = 3;

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
