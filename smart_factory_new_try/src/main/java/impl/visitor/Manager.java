package impl.visitor;


import impl.Factory;
import impl.Line;
import impl.enums.MachineState;
import impl.events.BreakdownEvent;
import impl.events.EventHandler;
import impl.lineItems.Machine;
import impl.lineItems.Worker;

/**
 * Manazer navstevuje entity tovarny, pokud je nejaky stroj poskozen na 50% posle ho opravit
 */
public class Manager implements Visitor {
    private String name = "Elina S.";
    private static Manager instance;
    private EventHandler eventHandler = EventHandler.getInstance();

    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    @Override
    public void visit(Machine machine) {
        System.out.println("Manager visited machine: " + machine.getName() + " with id " + machine.getId());
        if (machine.getCondition() <= 50 && machine.getState().equals(MachineState.WORKING)) {
            machine.setState(MachineState.BROKEN);
            eventHandler.addEvent(new BreakdownEvent("Breakdown", machine));
        }
    }

    @Override
    public void visit(Worker worker) {
        System.out.println("Manager visited worker: " + worker.getName() + worker.getId());
    }

    @Override
    public void visit(Line line) {
        System.out.println("Manager visited line: " + line.getId() + ", is working: "+line.isWorking());
    }

    @Override
    public void visit(Factory factory) {
        System.out.println("Manager visited factory: " + factory.getName() );
    }
}
