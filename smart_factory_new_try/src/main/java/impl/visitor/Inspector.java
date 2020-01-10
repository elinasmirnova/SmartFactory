package impl.visitor;

import impl.Factory;
import impl.Line;
import impl.lineItems.Machine;
import impl.lineItems.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Inspector visits machines in order from the most damaged and logs their condition
 */
public class Inspector implements Visitor {
    private int id;
    private String name;
    private Inspector instance;
    private static final Logger LOG = LoggerFactory.getLogger(Manager.class);

    public Inspector(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Inspector getInstance() {
        if (instance == null) {
            instance = new Inspector(1, "Lera");
        }
        return instance;
    }

    @Override
    public void visit(Machine machine) {
        LOG.info("Inspector visited machine: " + machine.getName() + "(" + machine.getId() + ") - condition: " + machine.getCondition());
    }

    @Override
    public void visit(Worker worker) {

    }

    @Override
    public void visit(Line line) {

    }

    @Override
    public void visit(Factory factory) {

    }
}
