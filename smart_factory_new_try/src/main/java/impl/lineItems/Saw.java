package impl.lineItems;

import impl.Constants;

public class Saw extends Machine {
    /** oil consumption */
    private final int oil = 6;
    /** electricity consumption */
    private final int ec = 4;
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

}
