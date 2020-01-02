package impl.lineItems;

import impl.visitor.Visitor;

public class Miller extends Machine {
    public Miller(String id) {
        super(id);
    }
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
