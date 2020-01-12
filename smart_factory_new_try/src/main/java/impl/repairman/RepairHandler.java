package impl.repairman;

import impl.enums.MachineState;
import impl.lineItems.Machine;

public class RepairHandler {
    private RepairmenPool repairmen = RepairmenPool.getInstance();
    private Queue queue = Queue.getInstance();

    public void startRepair() {
        System.out.println("OUR QUEUE: " + queue.getMachineQueue());
        int count = queue.getMachineQueue().size();
        int i = 0;
        while(i < count) {
            Repairman repairman = repairmen.getRepairman();
            if (repairman != null) {
                System.out.println("REPAIRMAN " + repairman.getId() + " ARE GOING TO FIX THE MACHINE " + queue.getMachineQueue().get(0).getName().toUpperCase());
                repairman.simulateFixing(queue.getMachineQueue().get(0));
                queue.getMachineQueue().remove(queue.getMachineQueue().get(0));
            } else {
                System.out.println("THERE ARE NO AVAILABLE REPAIRMEN, MACHINE WILL BE FIXED NEXT TICK");
            }
            i++;
        }
    }

    public void repair(Machine machine){
        machine.setCondition(100);
        System.out.println("Machine "+ machine.getName() + " fixed");
        machine.setState(MachineState.AFTER_REPAIR);
        Repairman repairman = repairmen.getRepairmanByMachine(machine);
        repairman.setRepairedMachine(null);
        System.out.println("REPAIRMAN "+ repairman.getId() + " FINISHED HIS WORK");
        repairmen.finishRepair(repairman);
    }
}
