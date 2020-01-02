package impl;

import impl.lineItems.LineItem;
import impl.lineItems.Machine;
import impl.visitor.Inspector;
import impl.visitor.Manager;
import impl.visitor.Visitor;

import java.util.List;

public class Factory implements Entity{

    private String name;
    private static Factory instance = null;
    private List<Line> lines;
    private int tick = 0;
    private int chairs = 0;
    private int tables = 0;
    private int wardrobes = 0;

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

    public static Factory getInstance(String name) {
        if (instance == null) {
            instance = new Factory(name);
        }
        return instance;
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
            for (LineItem lineItem : line.getSequence()) {
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

    public int getChairs() {
        return chairs;
    }

    public void setChairs(int chairs) {
        this.chairs = chairs;
    }

    public int getTables() {
        return tables;
    }

    public void setTables(int tables) {
        this.tables = tables;
    }

    public int getWardrobes() {
        return wardrobes;
    }

    public void setWardrobes(int wardrobes) {
        this.wardrobes = wardrobes;
    }

    public int getTotalMaterial() {
        int total = chairs * 1 + tables * 3 + wardrobes * 4;
        return total;
    }

}
