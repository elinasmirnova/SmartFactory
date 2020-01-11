package impl.repairman;

import impl.lineItems.Machine;

import java.util.ArrayList;
import java.util.LinkedList;

public class Queue {

    //should be fifo
    //Probably a LinkedList using add (adds one to the end) --> add machine
    //and removeFirst (removes one from the front and returns it) --> get the oldest(earliest)
    private ArrayList<Machine> machineQueue = new ArrayList<>();

    public static Queue instanceQueue;

    //singleton: queue must be only one
    public static Queue getInstance() {
        if (instanceQueue == null) {
            instanceQueue = new Queue();
        }
        return instanceQueue;
    }

    //get the earliest machine from the queue
//    public Machine getMachineFromQueue(){
//        System.out.println("Getting the machine for the repairment...");
//        return machineQueue.removeFirst();
//    }

//    public void removeMachineFromQueue(Machine machine){
//        for (Machine machine1: machineQueue){
//            if (machine1 == machine){
//                machineQueue.remove(machine);
//            }
//        }
//    }
    public ArrayList<Machine> getMachineQueue() {
        return machineQueue;
    }
}
