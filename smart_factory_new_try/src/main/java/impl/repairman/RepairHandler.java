package impl.repairman;

import impl.enums.MachineState;
import impl.lineItems.Machine;

public class RepairHandler {
    private RepairmenPool repairmen = RepairmenPool.getInstance();
    private Queue queue = Queue.getInstance();
//    private Repairman repairman;

    public void startRepair(){
        System.out.println("OUR QUEUE: " + queue.getMachineQueue());
        if (queue.getMachineQueue().size() < repairmen.getAvailableRepairmen().size()){
            System.out.println("WE HAVE ENOUGH REPAIRMEN TO FIX ALL THE MACHINES IN ONE TICK");
            for (Machine machine: queue.getMachineQueue()){
                Repairman repairman = repairmen.getRepairman();
                System.out.println("REPAIRMAN " + repairman.getId() + " ARE GOING TO FIX THE MACHINE " + machine.getName().toUpperCase());
                repairman.simulateFixing(machine);
            }
        }
    }

    public void repair(Machine machine){
        System.out.println("Start fixing the machine number " + machine.getName());
        machine.setCondition(100);
        System.out.println("Machine "+ machine.getId() + " fixed");
        machine.setState(MachineState.AFTER_REPAIR);
        Repairman repairman = repairmen.getRepairmanByMachine(machine);
        repairman.setRepairedMachine(null);
        System.out.println("REPAIRMAN "+ repairman.getId() + " FINISHED HIS WORK");
        repairmen.finishRepair(repairman);
    }
}
