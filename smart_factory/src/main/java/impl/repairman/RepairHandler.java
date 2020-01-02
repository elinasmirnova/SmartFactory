package impl.repairman;

import impl.enums.MachineState;
import impl.lineItems.Machine;

public class RepairHandler {
    private RepairStatus repairmen = RepairStatus.getInstance();
    private Queue queue = Queue.getInstance();
    private Repairman repairman = repairmen.getRepairman();

    public void startRepair(){
        if (queue.machineQueue == null){
            System.out.println("There are no broken machines in queue");
        }
        else {
            //get the oldest machine from queue
            Machine brokenMachine = queue.machineQueue.removeFirst();

            if (repairman != null){
                repairman.simulateFixing(brokenMachine);
                System.out.println(brokenMachine.getId() + " is ready to be fixed");
                brokenMachine.setState(MachineState.UNDER_REPAIR);
            }

        }

    }

    public void repair(Machine machine){
        System.out.println("Start fixing the machine number " + machine.getId());
        machine.setCondition(100);
        System.out.println("Machine "+ machine.getId() + " fixed");
        machine.setState(MachineState.WORKING);
        repairmen.finishRepair(repairman);
    }
}
