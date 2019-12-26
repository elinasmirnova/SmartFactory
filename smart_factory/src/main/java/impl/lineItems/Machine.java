package impl.lineItems;

import impl.Visitor;

public class Machine {

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
