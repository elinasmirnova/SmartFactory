package impl.lineItems;

import impl.visitor.Visitor;

public class Polisher extends Machine {
    public Polisher(String id) {
        super(id);
    }
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
