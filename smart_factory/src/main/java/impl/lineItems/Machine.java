package impl.lineItems;

import impl.Visitor;

public class Machine {

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
