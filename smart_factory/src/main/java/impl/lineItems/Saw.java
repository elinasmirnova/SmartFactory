package impl.lineItems;

import impl.enums.MachineState;

public class Saw extends Machine {
    public Saw(MachineState state, int condition, int repairTime) {
        super(state, condition, repairTime);
    }
}
