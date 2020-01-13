package impl.line;

import impl.Factory;
import impl.Line;
import impl.Observer;
import impl.Tick;
import impl.enums.MachineType;
import impl.lineItems.LineItem;
import impl.lineItems.Machine;
import impl.lineItems.MachineFactory;
import impl.lineItems.Worker;
import impl.product.Chair;
import impl.product.Table;
import impl.product.Wardrobe;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.Mac;
import java.util.ArrayList;
import java.util.List;

public class LineTest {
    private Factory factory;
    private Line line;
    private List<LineItem> list;
    private Machine machine1,
            machine2,
            machine3,
            machine4,
            machine5;
    private Worker  worker1,
            worker2,
            worker3;

    @Before
    public void setUp() {
        factory = new Factory("test1");
        line = new Line(factory, 1, new Chair());
        list = new ArrayList<>();
        machine1 = MachineFactory.getInstance().createMachine(1, MachineType.SAW);
        machine2 = MachineFactory.getInstance().createMachine(2, MachineType.LATHE);
        machine3 = MachineFactory.getInstance().createMachine(3, MachineType.SANDER);
        machine4 =  MachineFactory.getInstance().createMachine(4, MachineType.MILLER);
        machine5 =  MachineFactory.getInstance().createMachine(5, MachineType.POLISHER);
        worker1 = new Worker(1,"Operator");
        worker2 = new Worker(2,"Operator");
        worker3 = new Worker(3,"Operator");
        list.add(machine1);
        list.add(machine2);
        list.add(machine3);
        list.add(machine4);
        list.add(machine5);
        list.add(worker1);
        list.add(worker2);
        list.add(worker3);

    }

//    @Test(expected = Exception.class)
//    public void setLineItemsTest_ThrowsException() {
//        // arrange
//        Machine machine1 = MachineFactory.getInstance().createMachine(1, MachineType.SAW);
//        Machine machine2 = MachineFactory.getInstance().createMachine(2, MachineType.LATHE);
//        Machine machine3 = MachineFactory.getInstance().createMachine(3, MachineType.SANDER);
//        List<LineItem> list = new ArrayList<>();
//        list.add(machine1);
//        list.add(machine2);
//        list.add(machine3);
//        factory.setAvailableLineItems(list);
//        // act
//        line.setLineItems(new Wardrobe());
//    }

    @Test
    public void setLineItemsTest_Success() {
        //arrange
        factory.setAvailableLineItems(list);

        //act
        line.setLineItems(new Chair());

        //assert
        Assert.assertEquals(line.getWorkingItems().get(0), machine1);
        Assert.assertEquals(line.getWorkingItems().get(1), worker1);
        Assert.assertEquals(line.getWorkingItems().get(3), machine3);
        Assert.assertEquals(line.getWorkingItems().get(4), worker2);
    }

    @Test
    public void reorderLineItemsTest_Success() {
        //arrange
        factory.setAvailableLineItems(list);
        line.setLineItems(new Chair());

        //act
        line.reorderLineItems(new Table());

        //assert
        Assert.assertEquals(line.getWorkingItems().get(0), machine1);
        Assert.assertEquals(line.getWorkingItems().get(1), worker3);
        Assert.assertEquals(line.getWorkingItems().get(2), machine4);
        Assert.assertEquals(line.getWorkingItems().get(3), machine2);
        Assert.assertEquals(line.getWorkingItems().get(4), machine3);
        Assert.assertEquals(line.getWorkingItems().get(5), machine5);
        Assert.assertEquals(line.getWorkingItems().get(6), worker1);
    }

    @Test
    public void attachObserversTest_LineItemWasAttachedToTick() {
        //arrange
        factory.setAvailableLineItems(list);
        Tick tick = Tick.getInstance();

        //act
        line.setLineItems(new Chair());

        //assert
            Observer observer = tick.getObservers().stream()
                    .filter(o -> o.equals(line.getWorkingItems().get(0)))
                    .findAny()
                    .orElse(null);
        Assert.assertNotNull(observer);
    }

    @Test
    public void chainOfResponsibilityTest_OrderIsRight() {
        //arrange
        LineItem expected2inLine = worker1;
        LineItem expected4inLine= machine3;
        factory.setAvailableLineItems(list);

        //act
        line.setLineItems(new Chair());

        //act
        Assert.assertEquals(line.getWorkingItems().get(0).getNextLineItem(), expected2inLine);
        Assert.assertEquals(line.getWorkingItems().get(2).getNextLineItem(), expected4inLine);
    }

}
