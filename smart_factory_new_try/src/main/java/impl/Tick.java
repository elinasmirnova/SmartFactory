package impl;

import impl.memento.MachineStateCaretaker;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages tick in the factory.
 */
public class Tick {
    private static Tick instance = null;
    private List<Observer> observers = new ArrayList<>();
    private int currentTick = 1;

    public static Tick getInstance() {
        if (instance == null) {
            instance = new Tick();
        }
        return instance;
    }

    /**
     * Run real simulation and calls update on each entity, which was attached to this tick.
     */
    public void run() {
        for (currentTick = 1; currentTick <= 200; currentTick++) {
            if (currentTick == 3) {
                System.out.println("здесь");
            }
            System.out.println("Tick" + currentTick);
            notifyObservers();
        }
    }

    public void attach(Observer entity) { observers.add(entity); }

    public void detach(Observer entity) { observers.remove(entity); }

    /**
     * Calls update on each observer, which follows this tick.
     */
    public void notifyObservers() {
            for (Observer observer : (new ArrayList<>(observers))) {
                observer.update();
            }
    }

    public List<Observer> getObservers() {
        return observers;
    }

    public int getCurrentTick() {
        return currentTick;
    }
}
