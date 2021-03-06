package impl.visitor;

import impl.Factory;
import impl.Line;
import impl.lineItems.Machine;
import impl.lineItems.Worker;

/**
 * Visitor interface for manager and inspector
 */
public interface Visitor {

    public void visit(Factory factory);

    public void visit(Machine machine);

    public void visit(Worker worker);

    public void visit(Line line);
}
