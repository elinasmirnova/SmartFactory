package impl.product;

import impl.enums.ProductEnum;

public class Chair extends Product {
    private final int id = 1;
    private final String name = "Chair";
    private final int unitsPerTick = 3;
    private final int material = 1;
    private final ProductEnum type = ProductEnum.CHAIR;

    public ProductEnum getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getUnitsPerTick() {
        return unitsPerTick;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public int getMaterial() {
        return material;
    }

}
