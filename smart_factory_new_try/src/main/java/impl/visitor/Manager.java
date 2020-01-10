package impl.visitor;


import impl.Factory;
import impl.Line;
import impl.lineItems.Machine;
import impl.lineItems.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manager visits and logs about the factory
 */
public class Manager implements Visitor {

    private static final Logger LOG = LoggerFactory.getLogger(Manager.class);

    @Override
    public void visit(Machine machine) {
        LOG.info("Manager visited machine: " + machine.getId());
    }

    @Override
    public void visit(Worker worker) {
        LOG.info("Manager visited worker: " + worker.getName());
    }

    @Override
    public void visit(Line line) {
        LOG.info("Manager visited line: " + line.getId() );
    }

    @Override
    public void visit(Factory factory) {
        LOG.info("Manager visited factory: " + factory.getName() );
    }
}
