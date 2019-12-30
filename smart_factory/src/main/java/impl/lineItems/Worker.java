package impl.lineItems;

import impl.visitor.Visitor;

public class Worker {

    private int materialConsumption;
    private int salary;
    private String name;

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
