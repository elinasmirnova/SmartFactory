package impl;

import impl.lineItems.LineItem;
import impl.lineItems.Machine;
import impl.strategy.ProductStrategy;
import impl.visitor.Visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Line extends FactoryBuilder1 {

    //private ProductStrategy strategy;

    private int id;
    private List<LineItem> sequence;

    public Line(int id, List<LineItem> sequence) {
        this.id = id;
        this.sequence = sequence;
    }

    public void startProduction(ProductStrategy strategy) {
        strategy.createProduct();
        strategy.getSequence();
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<LineItem> getSequence() {
        return sequence;
    }

    public void setSequence(List<LineItem> sequence) {
        this.sequence = sequence;
    }


    private List<Machine> getMachines(List<LineItem> all) {
        List<Machine> machines = new ArrayList<Machine>();
        for (LineItem item : all) {
            if (item instanceof Machine) {
                machines.add((Machine) item);
            }
        }
        return machines;
    }


    public List<Machine> sortByCondition() {

        List<Machine> machines = getMachines(sequence);
        Collections.sort(machines, new Comparator<Machine>() {
            @Override
            public int compare(Machine o1, Machine o2) {
                return o1.getCondition() - o2.getCondition();
            }
        });
        return machines;
    }


}
