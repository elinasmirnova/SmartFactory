package impl.lineItems;


import impl.Entity;
import impl.Line;
import impl.Observer;
import impl.Tick;
import impl.enums.MachineState;
import impl.events.Event;
import impl.events.EventHandler;
import impl.events.FinishRepairEvent;
import impl.events.StartRepairEvent;
import impl.visitor.Visitor;

import java.util.List;

public abstract class LineItem implements Observer, Entity {
    private int typeId;
    private int id;
    private String name;
    private Tick tick = Tick.getInstance();
    private LineItem nextLineItem;
    private boolean isStarting;
    private Line line;
    List<Event> history;
    private EventHandler eventHandler = EventHandler.getInstance();

    public LineItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public LineItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void update() {
        if (this instanceof Machine) {
            if (((Machine) this).getState().equals(MachineState.UNDER_REPAIR)) {
                eventHandler.addEvent(new StartRepairEvent("Start Repair", (Machine) this));
                System.out.println("Machine" + this.getName() + " with id " + this.getId() + " state is changed to Under Repair");

            } else if (((Machine) this).getState().equals(MachineState.AFTER_REPAIR)) {
                eventHandler.addEvent(new FinishRepairEvent("Finish Repair", (Machine) this));
                ((Machine) this).setState((MachineState.WORKING));
                this.setStarting(true);
                line.setWorking(true); // continue production on this line
            }
            if (line.isWorking()) {
                if (isStarting) {
                    isStarting = false;
                    this.work();
                }
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LineItem getNextLineItem() {
        return nextLineItem;
    }

    public void setNextLineItem(LineItem nextLineItem) {
        this.nextLineItem = nextLineItem;
    }

    public boolean isStarting() {
        return isStarting;
    }

    public void setStarting(boolean starting) {
        isStarting = starting;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public abstract void work();

    public abstract String getType();
}
