package impl.lineItems;

import impl.enums.MachineType;

public class MachineFactory {

    private static MachineFactory instance = null;

    /**
     * Create new machine instance with Factory Method design patter
     * @param id - new machine id
     * @param type - type of the required machine. which we want to produce
     * @return return new machine
     */
    public Machine createMachine(int id, MachineType type) {
        if (type == MachineType.LATHE) {
            return new Lathe(id, "Lathe");
        } else if (type == MachineType.MILLER) {
            return new Miller(id, "Miller");
        } else if (type == MachineType.POLISHER) {
            return new Polisher(id, "Polisher");
        } else if (type == MachineType.SANDER) {
            return new Sander(id, "Sander");
        } else if (type == MachineType.SAW) {
            return new Saw(id, "Saw");
        }
        return null;
    }

    /**
     * Get an instance of MachineFactory class, which is a Singleton
     * @return existing MachineFactory class
     */
    public static MachineFactory getInstance() {
        if (instance == null) {
            instance = new MachineFactory();
        }
        return instance;
    }
}
