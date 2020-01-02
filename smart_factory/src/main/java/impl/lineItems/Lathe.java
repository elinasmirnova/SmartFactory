package impl.lineItems;

import impl.visitor.Visitor;

public class Lathe extends Machine {
    private final String name = "Lathe";
    private final int oil = 2;
    private final int ec = 4;

    @Override
    public int getEC() {
        return ec;
    }

    public Lathe(String id) {
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
    public void update() {

    }

    @Override
    public int getOil() {
        return oil;
    }
}
