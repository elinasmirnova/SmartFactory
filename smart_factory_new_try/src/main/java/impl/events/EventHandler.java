package impl.events;

import impl.Observer;
import impl.Tick;
import impl.enums.MachineState;
import impl.repairman.Queue;
import impl.repairman.RepairHandler;
import impl.repairman.RepairmenPool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class EventHandler implements Observer {

    private LinkedList<Event> eventQueue = new LinkedList<Event>();
    private RepairmenPool repairmen = RepairmenPool.getInstance();
    private Queue queue = Queue.getInstance();
    private RepairHandler repairHandler = new RepairHandler();
    private Tick tick = Tick.getInstance();
    private ArrayList<Event> eventHistory = new ArrayList<>();

    //time spent on fixing the machine
    private int timeToFix = 0;
    public static EventHandler instance;

    public EventHandler() {
        tick.attach(this);
    }

    //singleton
    public static EventHandler getInstance() {
        if (instance == null) {
            instance = new EventHandler();
        }
        return instance;
    }

    public void addEvent(Event event) {
        eventQueue.add(event);
        eventHistory.add(event); //TODO: event history
    }

    public void handle(){
        if (eventQueue.isEmpty()){
            System.out.println("There are no events to handle");
            if (!queue.getMachineQueue().isEmpty()){
                System.out.println("we still have broken machines in the queue...");
                repairHandler.startRepair();
            }
        }
        else{
            int i = 0;
            while(i < eventQueue.size()){
                if (eventQueue.get(i).getType().equals("Breakdown")){
                    BreakdownEvent breakdownEvent = (BreakdownEvent) eventQueue.get(i);
                    System.out.println("Got breakdown event for machine " + breakdownEvent.getMachine().getName());
                    queue.getMachineQueue().add(breakdownEvent.getMachine());
                }
                else if (eventQueue.get(i).getType().equals("Start Repair")){
                    StartRepairEvent startRepairEvent = (StartRepairEvent) eventQueue.get(i);
                    //increases every tick
                    timeToFix += 1;
                    //when equals to time needed to fix the machine --> stop
                    int countedTimeToFix = startRepairEvent.getMachine().countRepairTime();
                    System.out.println("THE MACHINE " + startRepairEvent.getMachine().getName() + " NEEDS " + countedTimeToFix + " TICKS TO BE FIXED");
                    System.out.println("IT'S THE TICK NUMBER " + timeToFix);
                    if (countedTimeToFix <= timeToFix) {
                        repairHandler.repair(startRepairEvent.getMachine());
                        timeToFix = 0;
                    }

                } else if(eventQueue.get(i).getType().equals("Finish Repair")) {
                    FinishRepairEvent finisRepairEvent = (FinishRepairEvent) eventQueue.get(i);
                    System.out.println("Got finish repair event for machine " + finisRepairEvent.getMachine().getName());
                    finisRepairEvent.getMachine().setState(MachineState.WORKING);
                }
                if (i == eventQueue.size()-1 && !queue.getMachineQueue().isEmpty()) {
                    repairHandler.startRepair();
                }
                i+=1;
            }
            eventQueue.clear();
           // System.out.println("AFTER EVERY TICK EVENT QUEUE MUST BE EMPTY:" + eventQueue);

        }
    }

    public void update() {
        this.handle();
    }

    public LinkedList<Event> getEventQueue() {
        return eventQueue;
    }

    public ArrayList<Event> getEventHistory() {
        return eventHistory;
    }

    public RepairmenPool getRepairmen() {
        return repairmen;
    }
}
