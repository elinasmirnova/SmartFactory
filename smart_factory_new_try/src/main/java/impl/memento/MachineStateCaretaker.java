package impl.memento;
import java.util.HashMap;

/**
 * Implementation of memento design pattern.
 * MachineStateCaretaker is responsible for restore condition of the machine from Machine.
 */
public class MachineStateCaretaker {
    private static MachineStateCaretaker instance;
    private HashMap<Integer, Integer> lathes = new HashMap<>();
    private HashMap<Integer, Integer> millers = new HashMap<>();
    private HashMap<Integer, Integer> polishers = new HashMap<>();
    private HashMap<Integer, Integer> sanders = new HashMap<>();
    private HashMap<Integer, Integer> saws = new HashMap<>();

    public static MachineStateCaretaker getInstance() {
        if (instance == null) {
            instance = new MachineStateCaretaker();
        }
        return instance;
    }

    public void addLathe(int tick, Integer condition) { lathes.put(tick, condition); }

    public void addMiller(int tick, Integer condition) { millers.put(tick, condition); }

    public void addPolisher(int tick, Integer condition) { polishers.put(tick, condition); }

    public void addSander(int tick, Integer condition) { sanders.put(tick, condition); }

    public void addSaw(int tick, Integer condition) { saws.put(tick, condition); }


    public Integer getLatheByTick(int tick) { return lathes.get(tick); }

    public Integer getMillerByTick(int tick) {
        return millers.get(tick);
    }

    public Integer getPolisherByTick(int tick) {
        return polishers.get(tick);
    }

    public Integer getSanderByTick(int tick) {
        return sanders.get(tick);
    }

    public Integer getSawByTick(int tick) {
        return saws.get(tick);
    }

    public HashMap<Integer, Integer> getLathes() {
        return lathes;
    }

    public HashMap<Integer, Integer> getMillers() {
        return millers;
    }

    public HashMap<Integer, Integer> getPolishers() {
        return polishers;
    }

    public HashMap<Integer, Integer> getSanders() {
        return sanders;
    }

    public HashMap<Integer, Integer> getSaws() {
        return saws;
    }
}
