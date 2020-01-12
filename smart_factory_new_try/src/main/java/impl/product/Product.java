package impl.product;

import impl.enums.ProductEnum;

public abstract class Product {

    abstract public String getName();
    abstract public int getUnitsPerTick();
    abstract public int getId();
    abstract public int getMaterial();
    abstract public ProductEnum getType();
}
