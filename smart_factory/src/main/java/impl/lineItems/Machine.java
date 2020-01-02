package impl.lineItems;

import impl.events.BreakdownEvent;
import impl.events.EventHandler;
import impl.repairman.Queue;
import impl.visitor.Visitor;
import impl.enums.MachineState;

public abstract class Machine extends LineItem{

    private int condition = 100;
    private MachineState state = MachineState.WORKING;
    private EventHandler eventHandler;
    private Queue brokenMachines;
    //private int totalConsumption;
    private int electricityConsumption;
    private int repairTime;
    //private int materialConsumption;

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

    // time = TickManager.getInstance().getCurrentTime()

    //enum states? NORMAL, BROKEN, UNDER_REPAIR

    //add events to the eventQueue

    //if (machine.getState().equals(BROKEN){
    //getEventHandler().addEvent(new BrokenEvent("Breakdown",time,getMachine()));
    // add the machine to queue of broken machines} ...

    //if state is UNDER_REPAIR --> create new event StartRepairEvent()

    //must have int repairTime !!!

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void work() {
        if (getNextLineItem() == null) {
            System.out.println("The product is done");
        }
        condition -= 10;
        if (condition < 10) {
            setState(MachineState.BROKEN);
            //eventHandler.addEvent(new BreakdownEvent("Breakdown"), time, getId());
            //brokenMachines. ADD
        }
        getNextLineItem().work();

    }

}
