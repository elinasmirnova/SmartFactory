package impl.lineItems;

import impl.visitor.Visitor;

public class Sander extends Machine {
    public Sander(String id) {
        super(id);
    }
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
