package impl.events;

import impl.lineItems.Machine;

import java.util.Date;

public class FinishRepairEvent extends Event {
    public FinishRepairEvent(String type, Machine machine) {
        super(type, machine);
    }
}
