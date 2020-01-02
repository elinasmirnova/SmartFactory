package impl.strategy;

import impl.Line;
import impl.enums.MachineType;
import impl.lineItems.*;

import java.util.*;
import java.util.stream.Collectors;

public class ChairStrategy implements ProductStrategy  {

    private List<String> sequence = new ArrayList<>(Arrays.asList("Saw", "Worker", "Lathe", "Sander", "Worker"));

    public ChairStrategy() {
    }

    @Override
    public void setLineItems() {

    }

    @Override
    public List<String> getSequence() {
        return null;
    }
}
