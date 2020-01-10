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
    private int id;
    private String name;
    private static Manager instance;

    private static final Logger LOG = LoggerFactory.getLogger(Manager.class);

    public Manager(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager(1, "Elina");
        }
        return instance;
    }

    @Override
    public void visit(Machine machine) {
        LOG.info("Manager visited machine: " + machine.getName() + " with id " + machine.getId());
    }

    @Override
    public void visit(Worker worker) {
        LOG.info("Manager visited worker: " + worker.getName() + worker.getId());
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
