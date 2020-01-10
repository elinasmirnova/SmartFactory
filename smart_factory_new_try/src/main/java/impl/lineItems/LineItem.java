package impl.lineItems;


import impl.Entity;
import impl.Observer;
import impl.Tick;
import impl.visitor.Visitor;

public abstract class LineItem implements Observer, Entity {
    private int typeId;
    private int id;
    private String name;
    private Tick tick = Tick.getInstance();
    private LineItem nextLineItem;
    private boolean isStarting;

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
        if (isStarting) {
            isStarting = false;
            this.work();
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
