package impl.repairman;

import impl.lineItems.Machine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Queue {

    private List<Machine> machineQueue = new ArrayList<Machine>();

    public static Queue instanceQueue;

    //singleton: queue must be only one
    /***
     * get one and the same instance of Queue, using the Singleton pattern
     * @return  the instance of Queue
     */

    public static Queue getInstance() {
        if (instanceQueue == null) {
            instanceQueue = new Queue();
        }
        return instanceQueue;
    }


    public List<Machine> getMachineQueue() {
        return machineQueue;
    }
}
