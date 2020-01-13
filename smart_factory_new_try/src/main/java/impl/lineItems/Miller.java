package impl.lineItems;

import impl.Constants;

public class Miller extends Machine {
    /** oil consumption */
    private final int oil = 4;
    /** electricity consumption */
    private final int ec = 2;
    private final String type = "MILLER";

    @Override
    public String getType() {
        return type;
    }


    public Miller(int id, String name) {
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
