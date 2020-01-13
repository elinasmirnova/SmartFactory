package impl.product;

import impl.enums.ProductEnum;

public class Table extends Product  {
    private final int id = 2;
    private final String name = "Table";
    private final int material = 3;
    /** Quantity of tables per one batch */
    private final int unitsPerTick = 2;
    private final ProductEnum type = ProductEnum.TABLE;

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
