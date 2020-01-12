package impl.visitor;

import impl.Factory;
import impl.Line;
import impl.enums.MachineState;
import impl.events.BreakdownEvent;
import impl.events.EventHandler;
import impl.lineItems.Machine;
import impl.lineItems.Worker;
import impl.repairman.Queue;

/**
 * Inspector navstevuje stroje tovarny, pokud je nejaky stroj poskozen na 30% posle ho opravit
 */
public class Inspector implements Visitor {
    private String name = "Lera Ch.";
    private static Inspector instance;
    private EventHandler eventHandler = EventHandler.getInstance();
    private Queue queue = Queue.getInstance();

    public static Inspector getInstance() {
        if (instance == null) {
            instance = new Inspector();
        }
        return instance;
    }

    @Override
    public void visit(Machine machine) {
        System.out.println("\nInspector visited machine: " + machine.getName() + "(" + machine.getId() + ") - condition: " + machine.getCondition());
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
