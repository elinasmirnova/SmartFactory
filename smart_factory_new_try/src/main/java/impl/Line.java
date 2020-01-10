package impl;

import impl.enums.ProductEnum;
import impl.lineItems.LineItem;
import impl.lineItems.Machine;
import impl.visitor.Visitor;

import java.util.*;
import java.util.stream.Collectors;

public class Line implements Observer, Entity{
    private Factory factory;
    private int id;
    private List<LineItem> workingItems = new ArrayList<>();
    private Tick tick = Tick.getInstance();
    private ProductEnum productType;
    private boolean isWorking;

    public Line(Factory factory, int id, ProductEnum type) {
        this.factory = factory;
        this.id = id;
        this.productType = type;
        tick.attach(this);
    }

    public void setLineItems() {
        System.out.println("Setting machines and workers to line...");
        productType.getLineItemSequence().forEach(i -> workingItems.add(findMatchingAvailableLineItem(factory.getAvailableLineItems(), i)));
        this.orderLineItems();

    }

    private LineItem findMatchingAvailableLineItem(List<LineItem> items, String type) {
        LineItem item = items.stream()
                .filter(i -> i.getName().startsWith(type))
                .collect(Collectors.toList()).get(0);
        items.remove(item);
        return item;
    }

    public void orderLineItems() {
        LineItem next = null;
        Iterator<LineItem> iter = workingItems.iterator();
       // workingItems.get(0).setStarting(true);
        for (LineItem item: workingItems) {
            tick.attach(item);
        }
        for (int i = 1; i < workingItems.size(); i++) {
            next = workingItems.get(i);
      //      chain of responsibility
            if (iter.hasNext()) {
                iter.next().setNextLineItem(next);
            }
        }

    }

    public void reorderLineItems() {

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

        List<Machine> machines = getMachines(workingItems);
        Collections.sort(machines, new Comparator<Machine>() {
            @Override
            public int compare(Machine o1, Machine o2) {
                return o1.getCondition() - o2.getCondition();
            }
        });
        return machines;
    }

    @Override
    public void update() {
        workingItems.get(0).setStarting(true);
        if (this.productType == ProductEnum.CHAIR) {
            Factory.chairs += 3; //TODO: implementovat getUnitsPerTick a udelat tak, aby se to volalo po vytvareni serie
        } else if (this.productType == ProductEnum.TABLE) {
            Factory.tables += 2;
        } else if (this.productType == ProductEnum.WARDROBE) {
            Factory.wardrobes += 1;
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<LineItem> getWorkingItems() {
        return workingItems;
    }

    public void setWorkingItems(List<LineItem> workingItems) {
        this.workingItems = workingItems;
    }
}
