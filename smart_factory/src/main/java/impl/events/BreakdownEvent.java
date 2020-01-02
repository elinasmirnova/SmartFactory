package impl.events;

import impl.lineItems.Machine;

import java.util.Date;

public class BreakdownEvent extends Event {
    public BreakdownEvent(String type, Date datetime, Machine machine) {
        super(type, datetime, machine);
    }
}