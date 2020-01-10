package impl.events;

import impl.lineItems.Machine;

public class FinishRepairEvent extends Event {
    public FinishRepairEvent(String type, Machine machine) {
        super(type, machine);
    }
}
