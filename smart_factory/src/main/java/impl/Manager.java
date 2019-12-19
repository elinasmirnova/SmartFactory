package impl;

import impl.lineItems.Machine;
import impl.lineItems.Worker;

public class Manager implements Visitor {

    @Override
    public void visit(Machine machine) {
        System.out.println("Manager visited machine");
    }

    @Override
    public void visit(Worker worker) {
        System.out.println("Manager visited worker");
    }
}
