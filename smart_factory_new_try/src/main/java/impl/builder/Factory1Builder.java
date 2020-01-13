package impl.builder;

import impl.Factory;
import impl.Line;
import impl.Tick;
import impl.enums.MachineType;
import impl.lineItems.LineItem;
import impl.lineItems.MachineFactory;
import impl.lineItems.Worker;
import impl.product.Chair;
import impl.repairman.Repairman;
import impl.repairman.RepairmenPool;
import impl.report.Report;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Builds factory and all the necessary entities for starting production.
 * Implements Builder interface.
 */
public class Factory1Builder implements Builder {
    private Factory factory;
    private MachineFactory machineFactory;
    private List<LineItem> availableLineItems = new ArrayList<>();
    private List<Line> lines = new ArrayList<>();
    private static int idCounter = 0;
    private Report report;

    /**
     * Create new instance of factory.
     */
    @Override
    public void createFactory() {
        factory = new Factory("factory1");
    }

    /**
     * Create new lines of the factory.
     */
    @Override
    public void createLines() {
        lines.add(new Line(factory, 1, new Chair()));
        factory.setLines(lines);
//        lines.add(new Line(factory, 2, ProductEnum.TABLE));
//        lines.add(new Line(factory, 3, ProductEnum.WARDROBE));
    }

    /**
     * Create new line item (machines and workers) and put these to the list of all the available line items.
     */
    @Override
    public void createLineItems() {
        availableLineItems.add(MachineFactory.getInstance().createMachine(idCounter++, MachineType.SAW));
        availableLineItems.add(MachineFactory.getInstance().createMachine(idCounter++, MachineType.LATHE));
        availableLineItems.add(MachineFactory.getInstance().createMachine(idCounter++, MachineType.MILLER));
        availableLineItems.add(MachineFactory.getInstance().createMachine(idCounter++, MachineType.POLISHER));
        availableLineItems.add(MachineFactory.getInstance().createMachine(idCounter++, MachineType.SANDER));
        availableLineItems.add(new Worker(idCounter++,"Operator"));
        availableLineItems.add(new Worker(idCounter++,"Operator"));
        availableLineItems.add(new Worker(idCounter++,"Operator"));
        availableLineItems.add(new Worker(idCounter++,"Operator"));
        availableLineItems.add(new Worker(idCounter++,"Operator"));
        factory.setAvailableLineItems(availableLineItems);
        factory.reportItems(availableLineItems);
    }

    /**
     * Put some repairmen to the factory.
     */
    @Override
    public void setRepairmen() {
        LinkedList<Repairman> repairmen = new LinkedList<>();
        repairmen.add(new Repairman(1));
        repairmen.add(new Repairman(2));
//        repairmen.add(new Repairman(3, true));
//        repairmen.add(new Repairman(4, true));
        RepairmenPool.getInstance().setAvailableRepairmen(repairmen);
        System.out.println("Available repairmen after initializing:" + RepairmenPool.getInstance().getAvailableRepairmen().toString());
    }

    /**
     * Starting simulation of the factory.
     */
    @Override
    public void startTicking() {
        factory.startProduction();
        Tick.getInstance().run();
    }


}
