package impl;

import impl.enums.MachineType;
import impl.lineItems.LineItem;
import impl.lineItems.MachineFactory;
import impl.lineItems.Worker;
import impl.strategy.ChairStrategy;
import impl.strategy.ProductStrategy;

import java.util.List;

public class FactoryBuilder implements Builder{

    private Factory factory = Factory.getInstance("factory1");
    private MachineFactory machineFactory;
    private ProductStrategy strategy;
    private List<LineItem> availableLineItems;

    @Override
    public void createFactory() {
        this.factory = Factory.getInstance("factory1");
    }

    @Override
    public void createLines() {
        Line line = new Line(1, getAvailableLineItems());
       // line.setStrategy(new ChairStrategy(line, getAvailableLineItems()));
    }

    @Override
    public void createLineItems() {
        availableLineItems.add(machineFactory.createMachine(MachineType.SAW));
        availableLineItems.add(machineFactory.createMachine(MachineType.LATHE));
        availableLineItems.add(machineFactory.createMachine(MachineType.MILLER));
        availableLineItems.add(machineFactory.createMachine(MachineType.POLISHER));
        availableLineItems.add(machineFactory.createMachine(MachineType.SANDER));
        availableLineItems.add(new Worker("Worker1", "Martin", 18000));
        availableLineItems.add(new Worker("Worker2", "Ivan", 20000));
        availableLineItems.add(new Worker("Worker3", "Daniel", 21000));
    }

    @Override
    public void startTicking() {

    }

    public List<LineItem> getAvailableLineItems() {
        return availableLineItems;
    }
}
