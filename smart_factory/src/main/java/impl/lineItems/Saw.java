package impl.lineItems;

import impl.visitor.Visitor;

public class Saw extends Machine {
    public Saw(String id) {
        super(id);
    }
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
