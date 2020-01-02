package impl.repairman;

import impl.lineItems.Machine;

import static impl.enums.MachineState.UNDER_REPAIR;

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
        machine.setState(UNDER_REPAIR);
    }






}
