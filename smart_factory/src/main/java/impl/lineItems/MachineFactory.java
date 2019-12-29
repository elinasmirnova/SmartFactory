package impl.lineItems;

import impl.Visitor;

public class MachineFactory extends LineItem{

    @Override
    public void accept(Visitor visitor) {

    }

    @Override
    public void createMachine(String type) { //or enum
        //TODO
    }
}
