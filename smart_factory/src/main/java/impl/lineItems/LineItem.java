package impl.lineItems;


import impl.Entity;

public abstract class LineItem implements Entity {

    private String id;
    private LineItem nextLineItem;

    public LineItem(String id) {
        this.id = id;
    }

    public void createMachine() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LineItem getNextLineItem() {
        return nextLineItem;
    }

    public void setNextLineItem(LineItem nextLineItem) {
        this.nextLineItem = nextLineItem;
    }

    protected abstract void work();
}
