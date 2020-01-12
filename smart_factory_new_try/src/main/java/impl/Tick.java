package impl;

import impl.enums.ProductEnum;
import impl.lineItems.LineItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    public void run() {
        for (currentTick = 1; currentTick <= 200; currentTick++) {
            if (currentTick == 155) {
                System.out.println("здесь");
            }
            System.out.println("Tick" + currentTick);
            notifyObservers();
        }
//        System.out.println(MachineStateCaretaker.getInstance().getSanderByTick(10));
//        System.out.println(MachineStateCaretaker.getInstance().getSanderByTick(20));
//        System.out.println(MachineStateCaretaker.getInstance().getSanderByTick(30));
//        System.out.println(MachineStateCaretaker.getInstance().getSanderByTick(40));

    }

    public void attach(Observer entity) { observers.add(entity); }

    public void detach(Observer entity) { observers.remove(entity); }

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
