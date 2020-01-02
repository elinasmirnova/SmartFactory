package impl.lineItems;

import impl.products.Product;
import impl.visitor.Visitor;

public class Worker extends LineItem{

    private int salary;

    private final int typeId = 1;

    public Worker(String id, String name, int salary) {
        super(id, name);
        this.salary = salary;
    }

    /***
     * handle production process, move to the next machine
     * @param product the product need to be produced
     */
    @Override
    public void work(Product product) {
        if (getNextLineItem() == null) {
            System.out.println("The product is done");
        }
        getNextLineItem().work(product);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

}
