package impl.lineItems;

import impl.visitor.Visitor;

public class Worker extends LineItem {
    private int salary;
    private final int typeId = 1;

    public Worker(int id, String name) {
        super(id, name);
    }

    @Override
    public void work() {
        if (getNextLineItem() == null) {
            System.out.println(this.getName() + " with id " + this.getId()  + " is working") ;
            System.out.println("The batch is done");
        } else {
            System.out.println(this.getName() + " with id " + this.getId()  + " is working") ;
            getNextLineItem().work();
        }
    }

}
