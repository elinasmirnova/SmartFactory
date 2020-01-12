package impl.lineItems;

import impl.Constants;

public class Lathe extends Machine {
    private final int oil = 3;
    private final int ec = 3;
    private final int repairTime = 2;
    private final String type = "LATHE";

    @Override
    public String getType() {
        return type;
    }

    public Lathe(int id, String name) {
        super(id, name);
    }

    public Lathe(int condition) {
        super(condition);
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
