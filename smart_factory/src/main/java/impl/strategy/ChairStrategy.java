package impl.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChairStrategy implements ProductStrategy  {

    private List<String> sequence = new ArrayList<>(Arrays.asList("machine1", "worker1"));

    @Override
    public void createProduct() {
        System.out.println("Chairs production is in progress");
    }

    @Override
    public List<String> getSequence() {
        return sequence;
    }

}
