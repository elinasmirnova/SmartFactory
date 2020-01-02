package impl.lineItems;

import impl.enums.MachineState;

public class Sander extends Machine {
    public Sander(MachineState state, int condition, int repairTime) {
        super(state, condition, repairTime);
    }
}
