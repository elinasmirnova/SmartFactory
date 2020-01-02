package impl.repairman;

import impl.enums.MachineState;
import impl.lineItems.Machine;

public class RepairHandler {
    public RepairStatus repairmen = RepairStatus.getInstance();
    public Queue queue = Queue.getInstance();
    public Repairman repairman = repairmen.getRepairman();

    /***
     * start the repair of a broken machine from the queue if it's not empty
     */
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
//                brokenMachine.setState(MachineState.UNDER_REPAIR);
            }

        }

    }

    /***
     * repairs the broken machine and returns the repairman to available list
     * @param machine a broken machine needs to be repaired
     */
    public void repair(Machine machine){
        System.out.println("Start fixing the machine number " + machine.getId());
        machine.setCondition(100);
        System.out.println("Machine "+ machine.getId() + " fixed");
        machine.setState(MachineState.WORKING);
        repairmen.finishRepair(repairman);
    }
}
