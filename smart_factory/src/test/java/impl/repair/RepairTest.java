package impl.repair;
import impl.enums.MachineState;
import impl.lineItems.Machine;
import impl.lineItems.Polisher;
import impl.repairman.RepairHandler;
import impl.repairman.RepairStatus;
import impl.repairman.Repairman;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RepairTest {
    private Machine machine;
    RepairHandler repairHandler;
    MachineState state = MachineState.WORKING;
    int condition = 5;
    int repairTime = 3;

    @Before
    public void before() {
        machine = new Polisher(state, condition, repairTime);

    }

    @Test
    public void repairTest(){
        repairHandler = new RepairHandler();
        System.out.println("Machine condition before repair: " + machine.getCondition());
        repairHandler.repair(machine);
        System.out.println("Machine condition after repair: " + machine.getCondition());
        assertEquals(100, machine.getCondition());
    }

}
