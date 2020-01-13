package impl.lineItems;

import impl.visitor.Visitor;

public class Worker extends LineItem {
    private int salary;
    private final String type = "WORKER";

    @Override
    public String getType() {
        return type;
    }

    public Worker(int id, String name) {
        super(id, name);
    }

    @Override
    public void work() {
        if (getNextLineItem() == null) {
            System.out.println(this.getName() + " with id " + this.getId()  + " is working") ;
            salary += 200;
            System.out.println("The batch is done");
            this.getLine().getFactory().addProductUnits(this.getLine().getProduct());
        } else {
            System.out.println(this.getName() + " with id " + this.getId()  + " is working") ;
            salary += 200;
            getNextLineItem().work();
        }
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
