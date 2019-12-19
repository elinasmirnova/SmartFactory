package impl.lineItems;

import impl.Visitor;

public class Worker {

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
