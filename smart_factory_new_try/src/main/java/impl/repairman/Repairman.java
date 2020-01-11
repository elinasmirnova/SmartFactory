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

    public Machine getRepairedMachine() {
        return repairedMachine;
    }

    public void setRepairedMachine(Machine repairedMachine) {
        this.repairedMachine = repairedMachine;
    }

    public void simulateFixing(Machine machine){
        repairedMachine = machine;
        machine.setState(MachineState.UNDER_REPAIR);
    }






}
