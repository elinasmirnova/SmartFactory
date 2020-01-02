package impl;

import impl.lineItems.LineItem;
import impl.lineItems.Machine;
import impl.products.Product;
import impl.strategy.ProductStrategy;
import impl.visitor.Visitor;

import java.util.*;
import java.util.stream.Collectors;

public class Line implements Observer{

    private ProductStrategy strategy;

    private int id;
    private List<LineItem> workingItems = new ArrayList<>();
    private List<LineItem> availableLineItems;
    private Tick tick = Tick.getInstance();
    private Product currentProduct;
    private int tickCounter = 0;
    private boolean isWorking;

    public Line(int id, List<LineItem> availableLineItems, Product product) {
        this.id = id;
        this.availableLineItems = availableLineItems;
        currentProduct = product;
        isWorking = true;
        tick.attach(this);
    }

    /***
     * start production by moving the product from one machine to other
     * @param product product need to be produced
     */
    public void startProduction(Product product) {
        this.setLineItems();
        workingItems.forEach(i -> i.work(product));
    }

    /***
     * realisation of the observer pattern
     */
    @Override
    public void update() {
      if (currentProduct.getName().startsWith("Chair")) {
            Factory.chairs += currentProduct.getUnitsPerTick();
      } else if (currentProduct.getName().startsWith("Table")) {
          Factory.tables += currentProduct.getUnitsPerTick();
      } else if (currentProduct.getName().startsWith("Wardrobe")) {
          Factory.wardrobes += currentProduct.getUnitsPerTick();
      }
    }

    public void reorderLineItems() {
        //TODO
    }

    /***
     * set machines and workers to line
     */
    public void setLineItems() {
        System.out.println("Setting machines and workers to line...");
        getStrategy().getSequence().forEach(i -> this.addWorkingItem(findMatchingAvailableLineItem(availableLineItems, i)));
    }

    /***
     * filter the line item by type
     * @param items line items
     * @param type filter
     * @return needed line item
     */
    private LineItem findMatchingAvailableLineItem(List<LineItem> items, String type) {
        LineItem item = items.stream()
                .filter(i -> i.getId().startsWith(type))
                .collect(Collectors.toList()).get(0);
        items.remove(item);
        return item;
    }

//    public void setStrategy(ProductStrategy strategy) {
//        this.strategy = strategy;
//    }

    /**
     * realisation of the visitor pattern
     * @param visitor visitor
     */
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /***
     * get machines
     * @param all the list of lineitems
     * @return all machines
     */
    private List<Machine> getMachines(List<LineItem> all) {
        List<Machine> machines = new ArrayList<Machine>();
        for (LineItem item : all) {
            if (item instanceof Machine) {
                machines.add((Machine) item);
            }
        }
        return machines;
    }

    /***
     * sort by condition
     * @return filtered machineList
     */
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

    public List<LineItem> getWorkingItems() {
        return workingItems;
    }

    /***
     * add lineitem to workingitems
     * @param item item need to be added
     */
    public void addWorkingItem(LineItem item) {
        workingItems.add(item);
        LineItem next = null;
        Iterator<LineItem> iter = workingItems.iterator();
        for (int i = 0; i < workingItems.size(); i++) {
            LineItem current = workingItems.get(i);
            if(iter.hasNext()) {
                current.setNextLineItem(iter.next());
            }
        }

    }

    public ProductStrategy getStrategy() {
        return strategy;
    }


}
