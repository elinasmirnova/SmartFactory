package impl.repairman;

import impl.lineItems.Machine;

import java.util.LinkedList;

public class Queue {

    //should be fifo
    //Probably a LinkedList using add (adds one to the end) --> add machine
    //and removeFirst (removes one from the front and returns it) --> get the oldest(earliest)
    public LinkedList<Machine> machineQueue = new LinkedList<Machine>();

    public static Queue instanceQueue;


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

    /***
     *
     * @return the earliest machine from the queue
     */
    public Machine getMachineFromQueue(){
        System.out.println("Getting the machine for the repairment...");
        return machineQueue.removeFirst();
    }

}
