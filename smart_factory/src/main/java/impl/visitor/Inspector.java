package impl.visitor;

import impl.lineItems.Machine;
import impl.lineItems.Worker;
import impl.visitor.Visitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Inspector extends Visitor {

    private static final Logger LOG = LoggerFactory.getLogger(Manager.class);

//    @Override
    public void visit(Machine machine) {
        LOG.info("Inspector visited machine: " + machine.getId() + " - condition: " + machine.getCondition());
    }
}
