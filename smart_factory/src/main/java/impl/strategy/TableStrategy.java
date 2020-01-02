package impl.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TableStrategy implements ProductStrategy  {

    private List<String> sequence = new ArrayList<>(Arrays.asList("machine2", "worker2"));

    @Override
    public void createProduct() {
        System.out.println("Tables production is in progress");
    }

    @Override
    public List<String> getSequence() {
        return sequence;
    }


}
