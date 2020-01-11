package impl.lineItems;

public class Sander extends Machine {
    private final int oil = 2;
    private final int ec = 4;
    private final int repairTime = 1;

    public Sander(int id, String name) {
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
