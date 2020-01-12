package impl.lineItems;

import impl.Line;
import impl.enums.MachineState;
import impl.events.BreakdownEvent;
import impl.events.EventHandler;
import impl.visitor.Visitor;

import java.util.concurrent.ThreadLocalRandom;

public abstract class Machine extends LineItem {

    private int condition = 100;
    private int repairTime;
//    private final int typeId = 0;
    //private int repairTime;
    private MachineState state = MachineState.WORKING;
    private EventHandler eventHandler = EventHandler.getInstance();
    private int oil;
    private int ec;



    public Machine(int id, String name) {
        super(id, name);
    }

    public Machine(int condition) {
        this.condition = condition;
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

//    public int getTypeId() {
//        return typeId;
//    }

    public MachineState getState() {
        return state;
    }

    public void setState(MachineState state) {
        this.state = state;
    }

    public int countRepairTime() {
        if (0 < this.getCondition() && this.getCondition() < 30) {
            repairTime = 3;
        } else if (30 < this.getCondition() && this.getCondition() < 60) {
            repairTime = 2;
        } else {
            repairTime = 1;
        }
        return repairTime;
    }

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
                int randomNum = ThreadLocalRandom.current().nextInt(1, 5 + 1);
                condition -= 5 + randomNum;
                if (condition < 10) {
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

    public abstract int getConsumptionPerTick();

    public abstract int getOil();
    public abstract int getEc();
}
