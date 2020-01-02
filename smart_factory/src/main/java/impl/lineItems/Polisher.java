package impl.lineItems;

import impl.visitor.Visitor;

public class Polisher extends Machine {
    private final String name = "Polisher";
    private final int oil = 2;
    private final int ec = 4;

    @Override
    public int getEC() {
        return ec;
    }

    @Override
    public int getOil() {
        return oil;
    }

    public Polisher(String id) {
        super(id);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void update() {

    }
}
