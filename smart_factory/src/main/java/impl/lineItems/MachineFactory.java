package impl.lineItems;

import impl.enums.MachineType;
import impl.visitor.Visitor;

public class MachineFactory {

    private static MachineFactory instance = null;

    private int latheIdCounter;
    private int millerIdCounter;
    private int polisherIdCounter;
    private int sanderIdCounter;
    private int sawIdCounter;

    public Machine createMachine(MachineType type) {
        if (type == MachineType.LATHE) {
            return new Lathe("Lathe" + latheIdCounter);
        } else if (type == MachineType.MILLER) {
            return new Miller("Miller" + millerIdCounter);
        } else if (type == MachineType.POLISHER) {
            return new Polisher("Polisher" + polisherIdCounter);
        } else if (type == MachineType.SANDER) {
            return new Sander("Sander" + sanderIdCounter);
        } else if (type == MachineType.SAW) {
            return new Sander("Saw" + sawIdCounter);
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
