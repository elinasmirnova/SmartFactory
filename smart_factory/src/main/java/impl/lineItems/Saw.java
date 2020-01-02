package impl.lineItems;

import impl.visitor.Visitor;

public class Saw extends Machine {
    private final String name = "Saw";
    private final int oil = 2;
    private final int ec = 4;

    @Override
    public int getEC() {
        return ec;
    }

    public Saw(String id) {
        super(id);
    }

    /***
     * realisation of the visitor pattern
     * @param visitor visitor
     */
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public int getOil() {
        return oil;
    }
}
