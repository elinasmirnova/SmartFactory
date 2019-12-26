package impl.events;

import impl.lineItems.Machine;

import java.util.Date;

public class FinishRepairEvent extends Event {
    public FinishRepairEvent(String type, Date datetime, Machine machine) {
        super(type, datetime, machine);
    }
}
