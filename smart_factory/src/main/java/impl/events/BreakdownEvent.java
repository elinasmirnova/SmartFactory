package impl.events;

import impl.lineItems.Machine;

import java.util.Date;

public class BreakdownEvent extends Event {
    public BreakdownEvent(String type, Machine machine) {
        super(type, machine);
    }
}