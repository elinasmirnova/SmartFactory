package impl.lineItems;

import impl.Constants;

public class Polisher extends Machine {
    private final int oil = 1;
    private final int ec = 2;
    private final String type = "POLISHER";

    @Override
    public String getType() {
        return type;
    }

    public Polisher(int id, String name) {
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
