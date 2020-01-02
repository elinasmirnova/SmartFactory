package impl.repairman;

import java.util.ArrayList;
import java.util.List;

public class RepairStatus {
    private List<Repairman> available = new ArrayList<Repairman>();
    private List<Repairman> working = new ArrayList<Repairman>();

    public static RepairStatus instance;

    /***
     * get one and the same instance of RepairStatus, using the Singleton pattern
     * @return  the instance of RepairStatus
     */
    public static RepairStatus getInstance() {
        if (instance == null) {
            instance = new RepairStatus();
        }
        return instance;
    }

    /***
     * get the the first repairman from the available list if it's not empty
     * @return the first repairman from the available list
     */
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

    /***
     * return the repairman to the available list when the machine is fixed
     * @param repairman a working repairman
     */
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
