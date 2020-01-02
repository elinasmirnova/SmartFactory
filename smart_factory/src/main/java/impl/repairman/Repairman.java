package impl.repairman;

import impl.*;
import impl.enums.MachineState;
import impl.events.Event;
import impl.lineItems.Machine;

public class Repairman {
    private int id;
    private boolean available;

    public Repairman(int id, boolean available){
        this.id = id;
        this.available = available;
    }

    /***
     * simulate the start of the repair process
     * @param machine the broken machine
     */
    public void simulateFixing(Machine machine){
        machine.setState(MachineState.UNDER_REPAIR);
    }






}
