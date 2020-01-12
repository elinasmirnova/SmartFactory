package impl.lineItems;

import impl.Constants;

public class Saw extends Machine {
    private final int oil = 6;
    private final int ec = 4;
    private final int repairTime = 1;
    private final String type = "SAW";

    @Override
    public String getType() {
        return type;
    }

    public Saw(int id, String name) {
        super(id, name);
    }

    public int getConsumptionPerTick() {
        return oil* Constants.getOilPrice() +ec*Constants.getElectricityPrice();
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
