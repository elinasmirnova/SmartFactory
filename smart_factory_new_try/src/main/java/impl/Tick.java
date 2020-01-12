package impl;

import impl.memento.MachineStateCaretaker;
import impl.memento.MachineStateHistory;

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
            if (currentTick == 155) {
                System.out.println("Здесь");
            }
            System.out.println("Tick" + currentTick);
            notifyObservers();
        }
        System.out.println(MachineStateCaretaker.getInstance().getSanderByTick(10));
        System.out.println(MachineStateCaretaker.getInstance().getSanderByTick(20));
        System.out.println(MachineStateCaretaker.getInstance().getSanderByTick(30));
        System.out.println(MachineStateCaretaker.getInstance().getSanderByTick(40));

    }

    public void attach(Observer entity) {observers.add(entity);}

    public void detach(Observer entity) {observers.remove(entity);}

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
