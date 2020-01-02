package impl.repairman;

import java.util.ArrayList;
import java.util.List;

public class RepairStatus {
    public List<Repairman> available = new ArrayList<Repairman>();
    public List<Repairman> working = new ArrayList<Repairman>();

    public static RepairStatus instance;


    //singleton: repairmen pool must be only one
    public static RepairStatus getInstance() {
        if (instance == null) {
            instance = new RepairStatus();
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
            return repairman;
        }

        return null;
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
