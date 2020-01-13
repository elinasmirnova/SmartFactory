package impl.lineItems;

import impl.Constants;

public class Sander extends Machine {
    /** oil consumption */
    private final int oil = 2;
    /** electricity consumption */
    private final int ec = 4;
    private final String type = "SANDER";

    @Override
    public String getType() {
        return type;
    }

    public Sander(int id, String name) {
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
