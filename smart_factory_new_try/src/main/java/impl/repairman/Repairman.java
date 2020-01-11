package impl.repairman;

import impl.enums.MachineState;
import impl.lineItems.Machine;

public class Repairman {
    private int id;
    private boolean available;
    private Machine repairedMachine;

    public Repairman(int id, boolean available){
        this.id = id;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

//need getId() and setCondition() from Machine class

    //set machine's HP to 100%
//    public void fix(Machine machine){
//        System.out.println("Start fixing the machine number " + (String)machine.getId());
//        machine.setCondition(100);
//        System.out.println("Machine "+ (String)machine.getId() + " fixed");
//        machine.setState("Normal");
//    }
//
//    //simulate fixing process
    public void simulateFixing(Machine machine){
        repairedMachine = machine;
        machine.setState(MachineState.UNDER_REPAIR);
    }

    public Machine getRepairedMachine() {
        return repairedMachine;
    }

    public void setRepairedMachine(Machine repairedMachine) {
        this.repairedMachine = repairedMachine;
    }
}
