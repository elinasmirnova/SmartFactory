package impl.events;

import impl.Tick;
import impl.lineItems.Machine;

public abstract class Event {

    private Tick tick = Tick.getInstance();
    private int tickStarted;
    private String type;
    private Machine machine;

    public Event(String type, Machine machine){
        this.type = type;
        this.tickStarted = tick.getCurrentTick();
        this.machine = machine;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTickStarted() {
        return tickStarted;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
}
