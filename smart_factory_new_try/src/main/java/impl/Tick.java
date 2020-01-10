package impl;

import java.util.ArrayList;
import java.util.List;

public class Tick {
    private static Tick instance = null;
    private List<Observer> observers = new ArrayList<Observer>();
    private int currentTick = 1;


    public static Tick getInstance() {
        if (instance == null) {
            instance = new Tick();
        }
        return instance;
    }

    public void run() {
        for (currentTick = 1; currentTick <= 200; currentTick++) {
            System.out.println("Tick" + currentTick);
            notifyObservers();
        }
    }

    public void attach(Observer entity) {observers.add(entity);}

    public void detach() {}

    public void notifyObservers() { //notify(eventType, data)
        for (Observer observer : observers) {
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
