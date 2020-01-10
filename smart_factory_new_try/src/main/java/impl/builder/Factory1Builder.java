package impl.builder;

import impl.Factory;
import impl.Line;
import impl.Tick;
import impl.enums.MachineType;
import impl.enums.ProductEnum;
import impl.lineItems.LineItem;
import impl.lineItems.MachineFactory;
import impl.lineItems.Worker;

import java.util.ArrayList;
import java.util.List;

public class Factory1Builder implements Builder {
    private Factory factory;
    private MachineFactory machineFactory;
    private List<LineItem> availableLineItems = new ArrayList<>();
    private List<Line> lines = new ArrayList<>();
    private static int idCounter = 0;

    @Override
    public void createFactory() {
        factory = new Factory("factory1");
    }

    @Override
    public void createLines() {
        lines.add(new Line(factory, 1, ProductEnum.CHAIR));
        factory.setLines(lines);
//        lines.add(new Line(factory, 2, ProductEnum.TABLE));
//        lines.add(new Line(factory, 3, ProductEnum.WARDROBE));
    }

    @Override
    public void createLineItems() {
        availableLineItems.add(MachineFactory.getInstance().createMachine(idCounter++, MachineType.SAW));
        availableLineItems.add(MachineFactory.getInstance().createMachine(idCounter++, MachineType.LATHE));
        availableLineItems.add(MachineFactory.getInstance().createMachine(idCounter++, MachineType.MILLER));
        availableLineItems.add(MachineFactory.getInstance().createMachine(idCounter++, MachineType.POLISHER));
        availableLineItems.add(MachineFactory.getInstance().createMachine(idCounter++, MachineType.SANDER));
        availableLineItems.add(new Worker(idCounter++,"Machine tool operator"));
        availableLineItems.add(new Worker(idCounter++,"Saw operator"));
        availableLineItems.add(new Worker(idCounter++,"Polisher operator"));
        availableLineItems.add(new Worker(idCounter++,"Operator"));
        factory.setAvailableLineItems(availableLineItems);
    }

    @Override
    public void startTicking() {
        factory.startProduction();
        Tick.getInstance().run();
    }
}
