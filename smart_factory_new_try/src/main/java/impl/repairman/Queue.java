package impl.repairman;

import impl.lineItems.Machine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Queue {

    //should be fifo
    //Probably a LinkedList using add (adds one to the end) --> add machine
    //and removeFirst (removes one from the front and returns it) --> get the oldest(earliest)
    private List<Machine> machineQueue = new ArrayList<Machine>();

    public static Queue instanceQueue;

    //singleton: queue must be only one
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
