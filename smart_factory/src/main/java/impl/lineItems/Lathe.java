package impl.lineItems;

import impl.visitor.Visitor;

public class Lathe extends Machine {
    public Lathe(String id) {
        super(id);
    }
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }


}
