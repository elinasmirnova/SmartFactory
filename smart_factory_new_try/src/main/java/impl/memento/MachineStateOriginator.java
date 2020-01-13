package impl.memento;
import impl.Tick;
import impl.lineItems.*;

import java.util.List;

/**
 * Implementation of memento design pattern.
 * Gets and stores condition of the machine using MachineStateCaretaker.
 */
public class MachineStateOriginator {

    private static MachineStateOriginator instance;
     MachineStateCaretaker caretaker = MachineStateCaretaker.getInstance();
     MachineStateOriginator originator;
     private Tick tick = Tick.getInstance();


     public static MachineStateOriginator getInstance() {
        if (instance == null) {
            instance = new MachineStateOriginator();
        }
        return instance;
    }

     public void saveMachineStates(List<Machine> machines) {
         for (Machine machine : machines) {
             if (machine instanceof Lathe) {
                caretaker.addLathe(tick.getCurrentTick(), machine.getCondition());
             } else if (machine instanceof Miller) {
                 caretaker.addMiller(tick.getCurrentTick(), machine.getCondition());
             } else if (machine instanceof Polisher) {
                 caretaker.addPolisher(tick.getCurrentTick(), machine.getCondition());
             } else if (machine instanceof Sander) {
                 caretaker.addSander(tick.getCurrentTick(), machine.getCondition());
             } else if (machine instanceof Saw) {
                caretaker.addSaw(tick.getCurrentTick(), machine.getCondition());
             }

         }
     }

     public void rewindLineItems(int tick) {
         if (caretaker.getLathes().get(tick) != null) {
             System.out.println("Lathe condition: " + caretaker.getLathes().get(tick));
         } else {
             System.out.println("Lathe didn't work at this tick");
         }
         if (caretaker.getMillers().get(tick) != null) {
             System.out.println("Miller condition: " + caretaker.getMillers().get(tick));
         } else {
             System.out.println("Miller didn't work at this tick");
         }
         if (caretaker.getPolishers().get(tick) != null) {
             System.out.println("Polisher condition: " + caretaker.getPolishers().get(tick));
         } else {
             System.out.println("Polisher didn't work at this tick");
         }
         if (caretaker.getSanders().get(tick) != null) {
             System.out.println("Sander condition: " + caretaker.getSanders().get(tick));
         } else {
             System.out.println("Sander didn't work at this tick");
         }
         if (caretaker.getSaws().get(tick) != null) {
             System.out.println("Saw condition: " + caretaker.getSaws().get(tick));
         } else {
             System.out.println("Saw didn't work at this tick");
         }

     }

}
