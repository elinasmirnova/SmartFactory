package impl.repairman;

import impl.Factory;
import impl.lineItems.Machine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepairmenPool {
    private Factory factory;
    private List<Repairman> available = new ArrayList<Repairman>();
    private List<Repairman> working = new ArrayList<Repairman>();

    public static RepairmenPool instance;

    //singleton: repairmen pool must be only one
    public static RepairmenPool getInstance() {
        if (instance == null) {
            instance = new RepairmenPool();
        }
        return instance;
    }

    //get the first repairman from the available list
    public Repairman getRepairman(){
        if (available.isEmpty()){
            System.out.println("There are no available repairmen now, try again later");
        }
        else{
            Repairman repairman = available.get(0);
            available.remove(0);
            working.add(repairman);
            System.out.println("REPAIRMAN " + repairman.getId() + " WAS TAKEN FROM AVAILABLE POOL");
            return repairman;
        }

        return null;
    }

    public Repairman getRepairmanByMachine(Machine machine) {
        return working.stream().filter(repairman -> repairman.getRepairedMachine().equals(machine)).collect(Collectors.toList()).get(0);
    }

    //when the machine is fixed return the repairman to the available list
    public void finishRepair(Repairman repairman){
        available.add(repairman);
        working.remove(repairman);

    }

    public List<Repairman> getAvailableRepairmen() {
        return available;
    }

    public List<Repairman> getWorkingRepairmen() {
        return working;
    }

    public void setAvailableRepairmen(List<Repairman> availableRepairmen) {
        this.available = availableRepairmen;
    }

    public void setWorkingRepairmen(List<Repairman> workingRepairmen) {
        this.working = workingRepairmen;
    }


}
