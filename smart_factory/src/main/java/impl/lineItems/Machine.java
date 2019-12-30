package impl.lineItems;

import impl.visitor.Visitor;
import impl.enums.MachineState;

public abstract class Machine extends LineItem{

    private int condition;
    private MachineState state;
    private int electricityConsumption;
    private int materialConsumption;

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
}
