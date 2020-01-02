package impl.lineItems;

import impl.enums.MachineState;

public class Polisher extends Machine {

    private int condition;
    private MachineState state;
    private int repairTime;

    public Polisher(MachineState state, int condition, int repairTime){
        super(state, condition, repairTime);

    }
}
