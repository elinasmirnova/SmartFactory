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

public class RepairmanTest {

    private Machine machine;
    private Repairman repairman1;
    private Repairman repairman2;
    RepairStatus repairStatus;
    MachineState state = MachineState.WORKING;
    int condition = 5;
    int repairTime = 3;

    @Before
    public void before() {
        machine = new Polisher(state, condition, repairTime);

        repairman1 = new Repairman(1, true);
        repairman2 = new Repairman(2, true);
        repairStatus = RepairStatus.getInstance();

        repairStatus.available.add(repairman1);
        repairStatus.available.add(repairman2);
    }


    @Test
    public void changeRepairmanStatusTest(){
        Repairman repairmanA = repairStatus.getRepairman();
        assertNotNull(repairmanA);
        assertEquals(repairmanA, repairStatus.working.get(0));

        Repairman repairmanB = repairStatus.getRepairman();
        assertNotNull(repairmanB);

        assertEquals(repairmanB, repairStatus.working.get(1));


//        assertEquals(repairmanA, repairmanB);

    }

        @Test
    public void getRepairmanTest(){
        Repairman rep1 = repairStatus.getRepairman();
        assertEquals(repairman1, rep1);
        repairStatus.finishRepair(rep1);

    }
}
