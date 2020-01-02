package impl.lineItems;

import impl.enums.MachineState;

public class Lathe extends Machine {
    public Lathe(MachineState state, int condition, int repairTime) {
        super(state, condition, repairTime);
    }
}
