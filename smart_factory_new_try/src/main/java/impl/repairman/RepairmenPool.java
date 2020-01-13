package impl.repairman;

import impl.Factory;
import impl.lineItems.Machine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class RepairmenPool {
    private Factory factory;
    private LinkedList<Repairman> available = new LinkedList<Repairman>();
    private LinkedList<Repairman> working = new LinkedList<Repairman>();

    public static RepairmenPool instance;

    //singleton: repairmen pool must be only one
    /***
     * get one and the same instance of RepairStatus, using the Singleton pattern
     * @return  the instance of RepairStatus
     */

    public static RepairmenPool getInstance() {
        if (instance == null) {
            instance = new RepairmenPool();
        }
        return instance;
    }

    //get the first repairman from the available list
    /***
     * get the the first repairman from the available list if it's not empty
     * @return the first repairman from the available list
     */

    public Repairman getRepairman(){
        if (available.isEmpty()){
            System.out.println("There are no available repairmen now, try again later");
        }
        else{
            Repairman repairman = available.removeFirst();
//            available.remove(0);
            working.add(repairman);
            System.out.println("REPAIRMAN " + repairman.getId() + " WAS TAKEN FROM AVAILABLE POOL");
            return repairman;
        }

        return null;
    }

    /**
     * find the repairman by machine he is repairing
     * @param machine broken machine
     * @return repairman
     */
    public Repairman getRepairmanByMachine(Machine machine) {
        return working.stream().filter(repairman -> repairman.getRepairedMachine().equals(machine)).collect(Collectors.toList()).get(0);
    }

    //when the machine is fixed return the repairman to the available list
    /***
     * return the repairman to the available list when the machine is fixed
     * @param repairman a working repairman
     */

    public void finishRepair(Repairman repairman){
        available.addFirst(repairman);
        working.remove(repairman);

    }

    public List<Repairman> getAvailableRepairmen() {
        return available;
    }

    public List<Repairman> getWorkingRepairmen() {
        return working;
    }

    public void setAvailableRepairmen(LinkedList<Repairman> availableRepairmen) {
        this.available = availableRepairmen;
    }

    public void setWorkingRepairmen(LinkedList<Repairman> workingRepairmen) {
        this.working = workingRepairmen;
    }


}
