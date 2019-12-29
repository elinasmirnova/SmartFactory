package impl.lineItems;


import impl.Entity;

public abstract class LineItem implements Entity {

    private int id;

    public void createMachine() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
