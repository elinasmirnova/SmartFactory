package impl.events;

import impl.lineItems.Machine;

import java.util.Date;

public class StartRepairEvent extends Event {
    public StartRepairEvent(String type, Machine machine) {
        super(type, machine);
    }
}
