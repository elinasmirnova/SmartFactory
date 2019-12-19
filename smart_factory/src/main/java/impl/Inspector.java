package impl;

import impl.lineItems.Machine;
import impl.lineItems.Worker;

public class Inspector implements Visitor {

    @Override
    public void visit(Machine machine) {
        System.out.println("Inspector visited machine");
    }

    @Override
    public void visit(Worker worker) {
        System.out.println("Inspector visited machine");
    }
}
