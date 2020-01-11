package impl.events;

import impl.Observer;
import impl.Tick;
import impl.repairman.Queue;
import impl.repairman.RepairHandler;
import impl.repairman.RepairmenPool;

import java.util.LinkedList;

public class EventHandler implements Observer {

    private LinkedList<Event> eventQueue = new LinkedList<Event>();
    private RepairmenPool repairmen = RepairmenPool.getInstance();
    private Queue queue = Queue.getInstance();
    private RepairHandler repairHandler = new RepairHandler();
    private Tick tick = Tick.getInstance();

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
        //TODO: event history
    }

    public void handle(){
        if (eventQueue.isEmpty()){
            System.out.println("There are no events to handle");
        }
        else{
            int i = 0;
            while(i < eventQueue.size()){
                if (eventQueue.get(i).getType().equals("Breakdown")){
                    System.out.println("Got breakdown event");
                    BreakdownEvent breakdownEvent = (BreakdownEvent) eventQueue.get(i);
                    queue.getMachineQueue().add(breakdownEvent.getMachine());
                    if (i == eventQueue.size()-1) {
                        repairHandler.startRepair();
                    }

                }
                else if (eventQueue.get(i).getType().equals("Start Repair")){
                    System.out.println("Got start repair event");
                    StartRepairEvent startRepairEvent = (StartRepairEvent) eventQueue.get(i);
                    //increases every tickhttps://softdroid.net/kak-vosstanovit-udalennye-fayly-iz-korziny-rukovodstvo
                    timeToFix += 1;
                    //when equals to time needed to fix the machine --> stop
                    System.out.println("THE MACHINE " + startRepairEvent.getMachine().getName() + " NEEDS " + startRepairEvent.getMachine().getRepairTime() + " TICKS TO BE FIXED");
                    if (startRepairEvent.getMachine().getRepairTime() == timeToFix) {
                        repairHandler.repair(startRepairEvent.getMachine());
                        timeToFix = 0;
                    }

                }
                i+=1;

            }
            eventQueue.clear();
            System.out.println("AFTER EVERY TICK EVENT QUEUE MUST BE EMPTY:" + eventQueue);
        }
    }

    public void update() {
        this.handle();
    }



}
