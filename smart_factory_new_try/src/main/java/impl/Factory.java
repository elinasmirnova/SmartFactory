package impl;

import impl.enums.ProductEnum;
import impl.lineItems.LineItem;
import impl.product.Product;
import impl.product.Table;
import impl.product.Wardrobe;
import impl.repairman.RepairmenPool;
import impl.report.Report;
import impl.visitor.Inspector;
import impl.visitor.Manager;
import impl.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * The factory entity, which implements observer (tick) interface and entity (visitor) interface.
 */
public class Factory implements Observer, Entity{

    private String name;
    private Tick t = Tick.getInstance();
    private Report report = new Report(this);
    private List<Line> lines;
    private List<LineItem> availableLineItems;
    private RepairmenPool pool = RepairmenPool.getInstance();
    private int chairs = 0;
    private int tables = 0;
    private int wardrobes = 0;
    private int tickToReorder1 = 66;
    private int tickToReorder2 = 132;
    private List<LineItem> allItems = new ArrayList<LineItem>();

    private Manager manager = Manager.getInstance();
    private Inspector inspector = Inspector.getInstance();

    private static final Logger LOG = Logger.getLogger("LOGGER");

    public Factory(String name) {
        this.name = name;
        t.attach(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public int getChairs() {
        return chairs;
    }

    public int getTables() {
        return tables;
    }

    public int getWardrobes() {
        return wardrobes;
    }

    /**
     * Added to the total amount of products amount of units per tick.
     * @param product product, what we produce at the moment
     */
    public void addProductUnits(Product product) {
        switch (product.getId()) {
            case 1: chairs += product.getUnitsPerTick();
            case 2: tables += product.getUnitsPerTick();
            case 3: wardrobes += product.getUnitsPerTick();
        }
    }

    public List<LineItem> getAvailableLineItems() {
        return availableLineItems;
    }

    public void setAvailableLineItems(List<LineItem> availableLineItems) {
        this.availableLineItems = availableLineItems;
    }

    public void addAvailableLineItem(LineItem item) {
        getAvailableLineItems().add(item);
    }

    /**
     * Set and order line items on the each line of the factory by its product type.
     */
    public void startProduction() {
        for (Line line : lines) {
            line.setLineItems(line.getProduct());
        }
    }

    public RepairmenPool getPool() {
        return pool;
    }

    @Override
    public void accept(Visitor visitor) {
        if (visitor instanceof Inspector) {
            acceptInspector(visitor);
        } else {
            acceptManager(visitor);
        }
    }

    public void acceptManager(Visitor visitor) {
        visitor.visit(this);
        for (Line line : lines) {
            line.accept(visitor);
            for (LineItem lineItem : line.getWorkingItems()) {
                lineItem.accept(visitor);
            }
        }
    }

    public void acceptInspector(Visitor visitor) {
        for (Line line: lines) {
            for (LineItem machine : line.sortByCondition()){
                machine.accept(visitor);
            }
        }

    }

    /**
     * Updates every tick. If tick is equal to some specific one, then some actions take place.
     */
    @Override
    public void update() {
        if (t.getCurrentTick() == tickToReorder1) {
            if (lines.get(0).checkIfAllMachinesAreWorking()) {
                lines.get(0).reorderLineItems(new Table());
            } else {
                tickToReorder1++;
            }
        }
        if (t.getCurrentTick() == tickToReorder2) {
            if (lines.get(0).checkIfAllMachinesAreWorking()) {
                lines.get(0).reorderLineItems(new Wardrobe());
            } else {
                tickToReorder2++;
            }
        }
        if (t.getCurrentTick()%100 == 0) {
            accept(inspector);
            report.generateConsumptionReport(1,10);
            report.generateEventReport(1,50);
        } else if (t.getCurrentTick()%50 == 0) {
            accept(manager);
        } else if (t.getCurrentTick() == 10) {
            report.generateFactoryConfiguration();
        }
    }

    public void reportItems(List<LineItem> availableLineItems) {
        for (LineItem item : availableLineItems) {
            allItems.add(item);
        }
        report.saveItems(allItems);
    }
}
