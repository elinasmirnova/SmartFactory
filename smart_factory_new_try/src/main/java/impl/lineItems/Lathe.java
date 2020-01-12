package impl.lineItems;

public class Lathe extends Machine {
    private final int oil = 3;
    private final int ec = 3;
    private final int repairTime = 2;

    public Lathe(int id, String name) {
        super(id, name);
    }

    public Lathe(int condition) {
        super(condition);
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
