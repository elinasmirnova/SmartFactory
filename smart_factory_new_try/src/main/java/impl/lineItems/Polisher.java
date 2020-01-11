package impl.lineItems;

public class Polisher extends Machine {
    private final int oil = 1;
    private final int ec = 2;

    public Polisher(int id, String name) {
        super(id, name);
    }

    public int getConsumptionPerTick() {
        return oil*120+ec*40;
    }

    @Override
    public int getOil() {
        return oil;
    }

    @Override
    public int getEc() {
        return ec;
    }

}
