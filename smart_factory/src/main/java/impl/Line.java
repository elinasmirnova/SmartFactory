package impl;

import impl.lineItems.LineItem;
import impl.strategy.ProductStrategy;

import java.util.List;

public class Line extends FactoryBuilder1 {

    //private ProductStrategy strategy;

    private List<LineItem> lineItems;

    public void startProduction(ProductStrategy strategy) {
        strategy.createProduct();
        strategy.getSequence();
    }

    public void accept(Visitor visitor) {

    }



}
