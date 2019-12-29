package impl.lineItems;

import impl.Visitor;

public class Worker {

    private int materialConsumption;
    private int salary;

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
