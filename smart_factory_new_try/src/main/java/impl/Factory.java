package impl;

import impl.enums.ProductEnum;
import impl.lineItems.LineItem;
import impl.repairman.RepairmenPool;
import impl.visitor.Inspector;
import impl.visitor.Visitor;

import java.util.List;

public class Factory implements Entity{

    private String name;
    //private static Factory instance = null;
    private List<Line> lines;
    private List<LineItem> availableLineItems;
    private RepairmenPool pool = RepairmenPool.getInstance();
    private static int tick = 0;
    public static int chairs = 0;
    public static int tables = 0;
    public static int wardrobes = 0;

    public Factory(String name) {
        this.name = name;
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

//    public static Factory getInstance(String name) {
//        if (instance == null) {
//            instance = new Factory(name);
//        }
//        return instance;
//    }


    public List<LineItem> getAvailableLineItems() {
        return availableLineItems;
    }

    public void setAvailableLineItems(List<LineItem> availableLineItems) {
        this.availableLineItems = availableLineItems;
    }

    public void addAvailableLineItem(LineItem item) {
        getAvailableLineItems().add(item);
    }

    public void startProduction() {
        //lines.forEach(Line::setLineItems);
        for (Line line : lines) {
            line.setLineItems(line.getProductType());
        }
        lines.get(0).reorderLineItems(ProductEnum.TABLE);
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

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public int getTotalMaterial() {
        int total = chairs * 1 + tables * 3 + wardrobes * 4;
        return total;
    }

}
