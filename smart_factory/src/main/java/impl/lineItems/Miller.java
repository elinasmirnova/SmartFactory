package impl.lineItems;

import impl.enums.MachineState;

public class Miller extends Machine {

    public Miller(MachineState state, int condition, int repairTime) {
        super(state, condition, repairTime);
    }
}
