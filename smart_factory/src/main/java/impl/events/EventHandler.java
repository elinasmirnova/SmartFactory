package impl.events;

import impl.repairman.Repairman;
import impl.repairman.RepairStatus;

import java.util.LinkedList;

public class EventHandler {

    private LinkedList<Event> eventQueue = new LinkedList<Event>();
    private RepairStatus repairmen = RepairStatus.getInstance();

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
                BreakdownEvent breakdownEvent = (BreakdownEvent) event;
                Repairman repairman = repairmen.getRepairman();
                repairman.simulateFixing(breakdownEvent.getMachine());
                System.out.println(breakdownEvent.getMachine() + " is under repair");
            }
        }
    }



}
