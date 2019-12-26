package impl.events;

import impl.repairman.Queue;
import impl.repairman.RepairHandler;
import impl.repairman.Repairman;
import impl.repairman.RepairStatus;

import java.util.LinkedList;

public class EventHandler {

    private LinkedList<Event> eventQueue = new LinkedList<Event>();
    private RepairStatus repairmen = RepairStatus.getInstance();
    private Queue queue = Queue.getInstance();
    private RepairHandler repairHandler = new RepairHandler();

    //time spent on fixing the machine
    private int timeToFix = 0;



    public static EventHandler instance;


    //singleton
    public static EventHandler getInstance() {
        if (instance == null) {
            instance = new EventHandler();
        }
        return instance;
    }

    public void addEvent(Event event) {
        eventQueue.add(event);
    }

    public void handle(){
        if (eventQueue.isEmpty()){
            System.out.println("There are no events to handle");
        }
        else{
            //get the oldest event from the list
            Event event = eventQueue.removeFirst();
            if (event.getType().equals("Breakdown")){
                System.out.println("Got breakdown event");
                BreakdownEvent breakdownEvent = (BreakdownEvent) event;
                queue.machineQueue.add(breakdownEvent.getMachine());
                repairHandler.startRepair();

            }
            else if (event.getType().equals("Start repair")){
                System.out.println("Got start repair event ");
                StartRepairEvent startRepairEvent = (StartRepairEvent) event;
                //increases every tick
                timeToFix += 1;
                //when equals to time needed to fix the machine --> stop
                if (startRepairEvent.getMachine().getRepairTime() == timeToFix) {
                    repairHandler.repair(startRepairEvent.getMachine());
                    timeToFix = 0;
                }


            }
        }
    }



}
