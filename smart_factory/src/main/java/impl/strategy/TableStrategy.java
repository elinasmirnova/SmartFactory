package impl.strategy;

import impl.Line;
import impl.enums.MachineType;
import impl.lineItems.LineItem;
import impl.lineItems.MachineFactory;
import impl.lineItems.Worker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableStrategy implements ProductStrategy  {

    private Line line;
    private List<String> sequence = new ArrayList<>(Arrays.asList("Saw", "Worker", "Lathe", "Sander", "Worker"));
    private List<LineItem> available;

    public TableStrategy(Line line, List<LineItem> available) {
        this.line = line;
        this.available = available;
    }


    @Override
    public void setLineItems() {
        System.out.println("Tables production is in progress");
    }

    @Override
    public List<String> getSequence() {
        return sequence;
    }
}
