package impl;

import impl.enums.ProductEnum;
import impl.lineItems.LineItem;
import impl.product.Product;
import impl.repairman.RepairmenPool;
import impl.report.Report;
import impl.visitor.Inspector;
import impl.visitor.Manager;
import impl.visitor.Visitor;

import java.util.List;

public class Factory implements Observer, Entity{

    private String name;
    private Tick t = Tick.getInstance();
    private Report report = new Report(this);
    //private static Factory instance = null;
    private List<Line> lines;
    private List<LineItem> availableLineItems;
    private RepairmenPool pool = RepairmenPool.getInstance();
    private int tick = 0;
    private int chairs = 0;
    private int tables = 0;
    private int wardrobes = 0;

    private Manager manager = Manager.getInstance();
    private Inspector inspector = Inspector.getInstance();

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

    public void addProductUnits(Product product) {
        switch (product.getId()) {
            case 1: chairs += product.getUnitsPerTick();
            case 2: tables += product.getUnitsPerTick();
            case 3: wardrobes += product.getUnitsPerTick();
        }
    }

    //    public static Factory getInstance(String name) {
////        if (instance == null) {
////            instance = new Factory(name);
////        }
////        return instance;
////    }


    public List<LineItem> getAvailableLineItems() {
        return availableLineItems;
    }

    public void setAvailableLineItems(List<LineItem> availableLineItems) {
        this.availableLineItems = availableLineItems;
        report.saveItems(availableLineItems);
    }

    public void addAvailableLineItem(LineItem item) {
        getAvailableLineItems().add(item);
    }

    public void startProduction() {
        //lines.forEach(Line::setLineItems);
        for (Line line : lines) {
            line.setLineItems(line.getProductType());
        }
      //  lines.get(0).reorderLineItems(ProductEnum.TABLE);
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

    @Override
    public void update() {
        if (t.getCurrentTick()%100 == 0) {
            accept(inspector);
            report.generateConsumptionReport(1,10);
            report.generateFactoryConfiguration();
            report.generateEventReport(1,50);
        } else if (t.getCurrentTick()%50 == 0) {
            accept(manager);
        }
    }

}
