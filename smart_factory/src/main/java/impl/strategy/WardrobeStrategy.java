package impl.strategy;

import impl.Line;
import impl.enums.MachineType;
import impl.lineItems.LineItem;
import impl.lineItems.MachineFactory;
import impl.lineItems.Worker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WardrobeStrategy implements ProductStrategy {

    private Line line;
    private List<String> sequence = new ArrayList<>(Arrays.asList("Saw", "Worker", "Lathe", "Sander", "Worker"));
    private List<LineItem> available;

    public WardrobeStrategy(Line line, List<LineItem> available) {
        this.line = line;
        this.available = available;
    }

    @Override
    public void setLineItems() {
        System.out.println("Wardrobe production is in progress");
    }

    @Override
    public List<String> getSequence() {
        return sequence;
    }
}
