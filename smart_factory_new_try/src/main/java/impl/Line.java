package impl;

import impl.enums.MachineState;
import impl.enums.ProductEnum;
import impl.lineItems.LineItem;
import impl.lineItems.Machine;
import impl.memento.MachineStateOriginator;
import impl.product.Product;
import impl.visitor.Visitor;


import java.util.*;

import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * The line entity, which implements observer (tick) interface and entity (visitor) interface.
 */
public class Line implements Observer, Entity{
    private Factory factory;
    private int id;
    private List<LineItem> workingItems = new ArrayList<>();
    private Tick tick = Tick.getInstance();
    private MachineStateOriginator history = MachineStateOriginator.getInstance();
    private ProductEnum productType;
    private boolean isWorking = true;
    private Product product;

    private static final Logger LOG = Logger.getLogger("LOGGER");

    public Line(Factory factory, int id, Product product) {
        this.factory = factory;
        this.id = id;
        this.product = product;
        this.productType = product.getType();
        tick.attach(this);
    }

    /**
     * Setting line items by the product
     * @param product
     */
    public void setLineItems(Product product) {
        this.product = product;
        this.productType = product.getType();
        LOG.info("Setting machines and workers to line...");
        try {
            productType.getLineItemSequence().forEach(i -> workingItems.add(findMatchingAvailableLineItem(factory.getAvailableLineItems(), i)));
            this.orderLineItems();
        } catch (Exception e) {
            LOG.warning("There are no available line items to set a new line");
            System.exit(0);
        }

    }

    private LineItem findMatchingAvailableLineItem(List<LineItem> items, String type) {
        LineItem item = items.stream()
                .filter(i -> i.getName().startsWith(type))
                .collect(Collectors.toList()).get(0);
        items.remove(item);
        return item;
    }

    /**
     * Ordering line items
     */
    private void orderLineItems() {
        LineItem next = null;
        Iterator<LineItem> iter = workingItems.iterator();
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

    /**
     * Reorder line items, if the product was changed.
     * @param product
     */
    public void reorderLineItems(Product product) {
        LOG.info("Got the request for a new batch of products");
        workingItems.forEach(i -> tick.detach(i));
        factory.getAvailableLineItems().addAll(workingItems); //return working items to available line items list
        workingItems.clear();
        this.setLineItems(product);
    }

    /**
     * Get machines from working line items on the line
     * @return list of machines
     */
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

    /**
     * Updating every tick and saving machines states.
     * Checks if all the working machines have state WORKING,
     * then set "starting" on the first machine of the working items.
     *  Afterward on this machine will be started all the chain of line items.
     */
    @Override
    public void update() {
        if (checkIfAllMachinesAreWorking()) {
            workingItems.get(0).setStarting(true);
        }
        history.saveMachineStates(getMachines());
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
