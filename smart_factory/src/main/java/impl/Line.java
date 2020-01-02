package impl;

import impl.lineItems.LineItem;
import impl.lineItems.Machine;
import impl.products.Product;
import impl.strategy.ProductStrategy;
import impl.visitor.Visitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Line {

    private ProductStrategy strategy;

    private int id;
    private Product product;
    private List<LineItem> workingItems = new ArrayList<>();
    private List<LineItem> availableLineItems;

    public Line(int id, List<LineItem> availableLineItems) {
        this.id = id;
        this.availableLineItems = availableLineItems;
    }

    public void startProduction() {
        strategy.setLineItems();
    }

    public void setLineItems() {
        System.out.println("Setting machines and workers to line...");
        getStrategy().getSequence().forEach(i -> this.addWorkingItem(findMatchingAvailableLineItem(availableLineItems, i)));
    }

    private LineItem findMatchingAvailableLineItem(List<LineItem> items, String type) {
        LineItem item = items.stream()
                .filter(i -> i.getId().startsWith(type))
                .collect(Collectors.toList()).get(0);
        items.remove(item);
        return item;
    }

    public void setStrategy(ProductStrategy strategy) {
        this.strategy = strategy;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Machine> getMachines() {
        List<LineItem> all = this.workingItems;
        List<Machine> machines = new ArrayList<Machine>();
        for (LineItem item : all) {
            if (item.getTypeId() == 0) {
                machines.add((Machine) item);
            }
        }
        return machines;
    }

    public List<Machine> sortByCondition() {

        List<Machine> machines = getMachines();
        Collections.sort(machines, new Comparator<Machine>() {
            @Override
            public int compare(Machine o1, Machine o2) {
                return o1.getCondition() - o2.getCondition();
            }
        });
        return machines;
    }

    public int getTotalOil() {
        List<Machine> machines = getMachines();
        int totalOil = 0;
        for (Machine machine:machines) {
            totalOil += machine.getOil();
        }
        return totalOil;
    }

    public int getTotalEC() {
        List<Machine> machines = getMachines();
        int totalEC = 0;
        for (Machine machine:machines) {
            totalEC += machine.getEC();
        }
        return totalEC;
    }

    public int getTotalMat() {
        List<Machine> machines = getMachines();
        int totalMat = 0;
        for (Machine machine:machines) {
            totalMat += machine.getMat();
        }
        return totalMat;
    }

    public List<LineItem> getSequence() {
        return workingItems;
    }

    public void addWorkingItem(LineItem item) {
        workingItems.add(item);
    }

    public ProductStrategy getStrategy() {
        return strategy;
    }

}
