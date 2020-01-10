package impl.lineItems;

public abstract class Machine extends LineItem {

    private int condition = 100;
    private int repairTime;
    private final int typeId = 0; //хуйня

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

    @Override
    public void work() {
        if (getNextLineItem() == null) {
            System.out.println("The batch is done");
        } else {
            condition -= 15;
            if (condition < 30) {
                System.out.println(this.getName() + " with id " + this.getId() + " is broken :(");
                //setState(MachineState.BROKEN);
                //stop production on the line
//            eventHandler.addEvent(new BreakdownEvent("Breakdown", this));
//            brokenMachines.getMachineQueue().add(this);
            } else {
                System.out.println(this.getName() + " with id " + this.getId() + " is working");
            }
            getNextLineItem().work();
        }
    }


}
