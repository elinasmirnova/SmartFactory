package impl.visitor;


import impl.Factory;
import impl.Line;
import impl.enums.MachineState;
import impl.events.BreakdownEvent;
import impl.events.EventHandler;
import impl.lineItems.Machine;
import impl.lineItems.Worker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manager visits and logs about the factory
 */
public class Manager implements Visitor {
    private String name = "Elina S.";
    private static Manager instance;
    private EventHandler eventHandler = EventHandler.getInstance();

//    private static final Logger LOG = LoggerFactory.getLogger(Manager.class);

    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    @Override
    public void visit(Machine machine) {
//        LOG.info("Manager visited machine: " + machine.getName() + " with id " + machine.getId());
        System.out.println("Manager visited machine: " + machine.getName() + " with id " + machine.getId());
        if (machine.getCondition() <= 50 && machine.getState().equals(MachineState.WORKING)) {
            machine.setState(MachineState.BROKEN);
            eventHandler.addEvent(new BreakdownEvent("Breakdown", machine));
        }
    }

    @Override
    public void visit(Worker worker) {
//        LOG.info("Manager visited worker: " + worker.getName() + worker.getId());
        System.out.println("Manager visited worker: " + worker.getName() + worker.getId());
    }

    @Override
    public void visit(Line line) {
//        LOG.info("Manager visited line: " + line.getId() );
        System.out.println("Manager visited line: " + line.getId() );
    }

    @Override
    public void visit(Factory factory) {
//        LOG.info("Manager visited factory: " + factory.getName() );
        System.out.println("Manager visited factory: " + factory.getName() );
    }
}
