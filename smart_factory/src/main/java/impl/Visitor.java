package impl;

import impl.lineItems.Machine;
import impl.lineItems.Worker;

public interface Visitor {
    void visit(Machine machine);
    void visit(Worker worker);

}
