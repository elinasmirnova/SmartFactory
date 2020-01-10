package impl.lineItems;

import impl.enums.MachineType;

public class MachineFactory {

    private static MachineFactory instance = null;

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

    public static MachineFactory getInstance() {
        if (instance == null) {
            instance = new MachineFactory();
        }
        return instance;
    }
}
