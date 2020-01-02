package impl.lineItems;

import impl.visitor.Visitor;

public class Worker extends LineItem{

    private int salary;
    private String name;

    public Worker(String id, String name, int salary) {
        super(id);
        this.name = name;
        this.salary = salary;
    }

    @Override
    protected void work() {
        if (getNextLineItem() == null) {
            System.out.println("The product is done");
        }
        getNextLineItem().work();
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
