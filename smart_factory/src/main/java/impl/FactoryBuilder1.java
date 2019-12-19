package impl;

import impl.strategy.ChairStrategy;
import impl.strategy.ProductStrategy;

public class FactoryBuilder1 implements Builder{

    private Factory factory;
    private ProductStrategy strategy;

    @Override
    public void createFactory() {

    }

    @Override
    public void createLines() {
        //setStrategy() on Line
        Line line = new Line();
        line.startProduction(new ChairStrategy());
    }

    @Override
    public void createLineItems() {

    }

    @Override
    public void generateReports() {

    }

    @Override
    public void startTicking() {

    }

}
