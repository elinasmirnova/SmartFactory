package impl.lineItems;

public class Saw extends Machine {
    private final int oil = 6;
    private final int ec = 4;
    private final int repairTime = 1;

    public Saw(int id, String name) {
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
    @Override
    public int getRepairTime() {
        return repairTime;
    }

}
