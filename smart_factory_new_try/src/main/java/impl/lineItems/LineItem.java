package impl.lineItems;


import impl.Entity;
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
    List<Event> history;
    private EventHandler eventHandler = EventHandler.getInstance();

    public LineItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void update() {
        history = EventHandler.getInstance().getEventHistory();
        if (this instanceof Machine) {
            if (((Machine) this).getState().equals(MachineState.UNDER_REPAIR)) {
                eventHandler.addEvent(new StartRepairEvent("Start Repair", (Machine) this));
                System.out.println("Machine" + this.getName() + " with id " + this.getId() + " state is changed to Under Repair");


            } else if (((Machine) this).getState().equals(MachineState.AFTER_REPAIR)) {
                eventHandler.addEvent(new FinishRepairEvent("Finish Repair", (Machine) this));
                // continue production on this line
                ((Machine) this).setState((MachineState.WORKING));
            } else if (!history.isEmpty() && history.get(history.size() - 1).getType().equals("Finish Repair")) {
                if (isStarting) {
                    isStarting = false;
                    this.work();
                }
            } else {
                if (isStarting) {
                    isStarting = false;
                    this.work();
                }
            }
//            if (this instanceof Machine) {
//                if (((Machine) this).getState().equals(MachineState.UNDER_REPAIR)) {
//                    System.out.println("Repair in progress");
//                }
//            }

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

    public abstract void work();
}
