package impl.visitor;

import impl.Factory;
import impl.Line;
import impl.enums.MachineState;
import impl.events.BreakdownEvent;
import impl.events.EventHandler;
import impl.lineItems.Machine;
import impl.lineItems.Worker;
import impl.repairman.Queue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Inspector visits machines in order from the most damaged and logs their condition
 */
public class Inspector implements Visitor {
//    private int id;
    private String name = "Lera Ch.";
    private static Inspector instance;
    private EventHandler eventHandler = EventHandler.getInstance();
    private Queue queue = Queue.getInstance();

//    private static final Logger LOG = LoggerFactory.getLogger(Manager.class);
//    Logger logger = Logger.getLogger(MyClass.class.getName());

    public static Inspector getInstance() {
        if (instance == null) {
            instance = new Inspector();
        }
        return instance;
    }

    @Override
    public void visit(Machine machine) {
//        LOG.info("Inspector visited machine: " + machine.getName() + "(" + machine.getId() + ") - condition: " + machine.getCondition());
        System.out.println("Inspector visited machine: " + machine.getName() + "(" + machine.getId() + ") - condition: " + machine.getCondition());
        if (machine.getCondition() <= 30 && machine.getState().equals(MachineState.WORKING)) {
            if (!queue.getMachineQueue().contains(machine)){
                machine.setState(MachineState.BROKEN);
                eventHandler.addEvent(new BreakdownEvent("Breakdown", machine));
            }

        }
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
