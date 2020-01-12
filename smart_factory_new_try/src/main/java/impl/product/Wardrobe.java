package impl.product;

import impl.enums.ProductEnum;

public class Wardrobe extends Product {
    private final int id = 3;
    private final String name = "Wardrobe";
    private final int unitsPerTick = 1;
    private final int material = 4;
    private final ProductEnum type = ProductEnum.WARDROBE;

    public ProductEnum getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
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
