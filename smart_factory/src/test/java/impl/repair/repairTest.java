package impl.repair;
import impl.enums.MachineState;
import impl.lineItems.Machine;
import impl.lineItems.Polisher;
import impl.repairman.RepairHandler;
import impl.repairman.RepairStatus;
import impl.repairman.Repairman;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class repairTest {
    private Machine machine;
    private Repairman repairman1;
    private Repairman repairman2;
    private List<Repairman> repairmen;
    RepairStatus repairStatus;
    RepairHandler repairHandler;
    MachineState state = MachineState.WORKING;
    int condition = 5;
    int repairTime = 3;

    @Before
    public void before() {
        machine = new Polisher(state, condition, repairTime);
        repairman1 = new Repairman(1, true);
        repairman2 = new Repairman(2, true);
        repairStatus = new RepairStatus();
//
        //INSTANCE
//        repairmen = repairStatus.available;
        repairStatus.available.add(repairman1);
        repairStatus.available.add(repairman2);

        repairHandler = new RepairHandler();

    }

//    @Test
//    public void addRepairmanTest(){
//        repairStatus.setAvailableRepairmen(repairmen);
//        assertEquals(repairmen, repairStatus.getAvailableRepairmen());
//        System.out.println(repairmen);
//    }

    @Test
    public void getRepairmanTest(){
//        assertEquals(repairman1,  repairStatus.getRepairman());
//        repairman1 was removed from available

//        assertEquals(repairman2, repairStatus.getRepairman());
    }

//    @Test
//    public void repairTest(){
//        System.out.println(repairStatus.available);
//        repairHandler.repair(machine);
//        assertEquals(100, machine.getCondition());
//    }

}
