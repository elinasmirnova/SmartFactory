package impl.machine;

import impl.enums.MachineState;
import impl.enums.MachineType;
import impl.lineItems.Lathe;
import impl.lineItems.Machine;
import impl.lineItems.MachineFactory;
import impl.lineItems.Polisher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MachineTest {

    @Test
    public void createMachineWithFactoryMethodTest_success() {
        //ARRANGE
        int expectedId = 1;
        int expecedCondition = 100;
        MachineType type = MachineType.LATHE;

        //ACT
        Machine lathe = MachineFactory.getInstance().createMachine(expectedId, type);

        //ASSERT
        Assert.assertEquals(lathe.getId(), expectedId);
        Assert.assertEquals(lathe.getCondition(), expecedCondition);

    }

}
