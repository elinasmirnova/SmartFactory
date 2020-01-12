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
    int id = 1;
    String name = "Polisher";



    @Before
    public void before() {
        machine = new Polisher(id, name);
    }

    @Test
    public void createMachineTest() {

        assertEquals(state, machine.getState());
        assertEquals(id, machine.getId());
        assertEquals(name, machine.getName());

    }

}
