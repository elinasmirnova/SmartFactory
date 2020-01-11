package impl.lineItems;

public class Miller extends Machine {
    private final int oil = 4;
    private final int ec = 2;


    public Miller(int id, String name) {
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
