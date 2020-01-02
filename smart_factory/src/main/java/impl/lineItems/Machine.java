package impl.lineItems;

import impl.Line;
import impl.Observer;
import impl.events.BreakdownEvent;
import impl.events.EventHandler;
import impl.events.FinishRepairEvent;
import impl.events.StartRepairEvent;
import impl.products.Product;
import impl.repairman.Queue;
import impl.visitor.Visitor;
import impl.enums.MachineState;

public abstract class Machine extends LineItem implements Observer {

    private int condition = 100;
    private MachineState state = MachineState.WORKING;
    private EventHandler eventHandler;
    private Queue brokenMachines;
    //private int totalConsumption;
    private int electricityConsumption;
    private int repairTime;
    private Line line;
    //private int materialConsumption;
    private final int typeId = 0;

    public Machine(String id) {
        super(id);
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public MachineState getState() {
        return state;
    }

    public void setState(MachineState state) {
        this.state = state;
    }

    abstract public int getOil();

    public void accept(Visitor visitor) {
        visitor.visit(this);
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
        System.out.println(getId() + " now is working on" + product.getName() + "with id" + product.getId()) ;
        condition -= 10;
        if (condition < 10) {
            setState(MachineState.BROKEN);
            eventHandler.addEvent(new BreakdownEvent("Breakdown", this));
            brokenMachines.getMachineQueue().add(this);
        }
        getNextLineItem().work(product);

    }

    public abstract int getEC();


    public int getRepairTime() {
        return repairTime;
    }

    /***
     * realisation of the observer pattern: check state and add event
     */
    @Override
    public void update() {
        if (getState().equals(MachineState.UNDER_REPAIR)) {
            eventHandler.addEvent(new StartRepairEvent("Start Repair", this));

        } else if (getState().equals(MachineState.AFTER_REPAIR) ) {
            eventHandler.addEvent(new FinishRepairEvent("Finish Repair", this));
            // continue production on this line
            this.setState(MachineState.WORKING);
        }
    }




}
