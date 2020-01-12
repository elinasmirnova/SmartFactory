package impl;

import impl.enums.MachineState;
import impl.enums.ProductEnum;
import impl.events.Event;
import impl.events.EventHandler;
import impl.lineItems.LineItem;
import impl.lineItems.Machine;
import impl.product.Product;
import impl.visitor.Visitor;

import java.util.*;
import java.util.stream.Collectors;

public class Line implements Observer, Entity{
    private Factory factory;
    private int id;
    private List<LineItem> workingItems = new ArrayList<>();
    private EventHandler handler = EventHandler.getInstance();
    private Tick tick = Tick.getInstance();
   // private MachineStateHistory history = MachineStateHistory.getInstance();
    private ProductEnum productType;
    private boolean isWorking = true;
    private Product product;

    public Line(Factory factory, int id, Product product) {
        this.factory = factory;
        this.id = id;
        this.product = product;
        this.productType = product.getType();
        tick.attach(this);
    }

    public void setLineItems(ProductEnum type) {
        System.out.println("Setting machines and workers to line...");
        type.getLineItemSequence().forEach(i -> workingItems.add(findMatchingAvailableLineItem(factory.getAvailableLineItems(), i)));
        this.orderLineItems(); //TODO: hodit vyjimku, kdyz se nepodarilo nastavit linu

    }

    private LineItem findMatchingAvailableLineItem(List<LineItem> items, String type) {
        LineItem item = items.stream()
                .filter(i -> i.getName().startsWith(type))
                .collect(Collectors.toList()).get(0);
        items.remove(item);
        return item;
    }

    private void orderLineItems() {
        LineItem next = null;
        Iterator<LineItem> iter = workingItems.iterator();
       // workingItems.get(0).setStarting(true);
        for (LineItem item: workingItems) {
            tick.attach(item);
            item.setLine(this);
        }
        for (int i = 1; i < workingItems.size(); i++) {
            next = workingItems.get(i);
      //      chain of responsibility
            if (iter.hasNext()) {
                iter.next().setNextLineItem(next);
            }
        }

    }

    public void reorderLineItems(ProductEnum type) {
        System.out.println("Got the request for a new batch of products");
        workingItems.forEach(i -> tick.detach(i));
        factory.getAvailableLineItems().addAll(workingItems); //return working items to available line items list
        this.setLineItems(type);
    }

    public List<Machine> getMachines() {
        List<Machine> machines = new ArrayList<Machine>();
        for (LineItem item : getWorkingItems()) {
            if (item instanceof Machine) {
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

    @Override
    public void update() {
        if (checkIfAllMachinesAreWorking()) {
            workingItems.get(0).setStarting(true);
        }
        if (tick.getCurrentTick() != 1) {
            factory.addProductUnits(product);
        }
        //history.saveMachineStates(getMachines());
    }

    public boolean checkIfAllMachinesAreWorking() {
        int count = 0;
        for (LineItem item : workingItems) {
            if (item instanceof Machine && ((Machine) item).getState().equals(MachineState.WORKING))  {
                count++;
            }
        }
        return count == getMachines().size();
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

    public boolean isWorking() {
        return isWorking;
    }

    public ProductEnum getProductType() {
        return productType;
    }

    public void setProductType(ProductEnum productType) {
        this.productType = productType;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public Product getProduct() {
        return product;
    }

    public Factory getFactory() {
        return factory;
    }
}
