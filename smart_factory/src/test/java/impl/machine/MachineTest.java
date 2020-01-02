package impl.machine;

import impl.enums.MachineState;
import impl.lineItems.Machine;
import impl.lineItems.Polisher;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MachineTest {
    private Machine machine;
    MachineState state = MachineState.WORKING;
    int condition = 100;
    int repairTime = 3;



    @Before
    public void before() {
        machine = new Polisher(state, condition, repairTime);
    }

    @Test
    public void createMachineTest() {

        assertEquals(state, machine.getState());
        assertEquals(condition, machine.getCondition());
        assertEquals(repairTime, machine.getRepairTime());

    }

}
