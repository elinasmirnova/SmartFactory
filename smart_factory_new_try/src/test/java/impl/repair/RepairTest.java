package impl.repair;
import impl.enums.MachineState;
import impl.lineItems.Machine;
import impl.lineItems.Polisher;
import impl.repairman.RepairHandler;
import impl.repairman.RepairmenPool;
import impl.repairman.Repairman;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class RepairTest {
    private Machine machine;
    RepairHandler repairHandler;
    MachineState state = MachineState.WORKING;
    int id = 1;
    String name = "Polisher";
    RepairmenPool repairmenPool = RepairmenPool.getInstance();
    LinkedList<Repairman> repairmen = new LinkedList<>();
    Repairman repairman;



    @Before
    public void before() {
        machine = new Polisher(id, name);
        machine.setCondition(20);
        repairman = new Repairman(1, true);
        repairmen.add(repairman);
        repairmenPool.setAvailableRepairmen(repairmen);
    }

    @Test
    public void repairTest(){
        repairHandler = new RepairHandler();
        System.out.println("Machine condition before repair: " + machine.getCondition());
        repairman = repairmenPool.getRepairman();
        repairman.simulateFixing(machine);
        repairHandler.repair(machine);
        System.out.println("Machine condition after repair: " + machine.getCondition());
        assertEquals(100, machine.getCondition());
    }

    @Test
    public void changeRepairmanStatus(){
        Repairman repairman = repairmenPool.getRepairman();
        repairmenPool.finishRepair(repairman);
        assertTrue(repairmenPool.getWorkingRepairmen().isEmpty());

    }

}
