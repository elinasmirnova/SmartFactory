package impl.lineItems;

import impl.Line;
import impl.enums.MachineState;
import impl.events.BreakdownEvent;
import impl.events.EventHandler;
import impl.events.FinishRepairEvent;
import impl.events.StartRepairEvent;
import impl.visitor.Visitor;

public abstract class Machine extends LineItem {

    private int condition = 100;
    private int repairTime;
    private final int typeId = 0;
    //private int repairTime;
    private MachineState state = MachineState.WORKING;
    private EventHandler eventHandler = EventHandler.getInstance();

    public Machine(int id, String name) {
        super(id, name);
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public int getRepairTime() {
        return repairTime;
    }

    public void setRepairTime(int repairTime) {
        this.repairTime = repairTime;
    }

    public int getTypeId() {
        return typeId;
    }

    public MachineState getState() {
        return state;
    }

    public void setState(MachineState state) {
        this.state = state;
    }

//    public int countRepairTime() {
//        getCondition()
//    }

    @Override
    public void work() {
//        if (getState().equals(MachineState.UNDER_REPAIR)) {
//            eventHandler.addEvent(new StartRepairEvent("Start Repair", this));
//            System.out.println("Machine" + this.getName() + " with id " + this.getId() + " state is changed to Under Repair");
//
//
//        } else if (getState().equals(MachineState.AFTER_REPAIR) ) {
//            eventHandler.addEvent(new FinishRepairEvent("Finish Repair", this));
//            // continue production on this line
//            this.setState(MachineState.WORKING);
//        } else {
            if (getNextLineItem() == null) {
                System.out.println("The batch is done");
            } else {
                condition -= 15;
                if (condition < 30) {
                    System.out.println(this.getName() + " with id " + this.getId() + " is broken :(");
                    setState(MachineState.BROKEN);
                    this.getLine().setWorking(false);
                    eventHandler.addEvent(new BreakdownEvent("Breakdown", this));
                    //stop production on the line

                } else {
                    System.out.println(this.getName() + " with id " + this.getId() + " is working");
                    getNextLineItem().work();
                }
            }
        }
   // }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
