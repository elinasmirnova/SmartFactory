package impl.strategy;

import impl.lineItems.LineItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ChairStrategy implements ProductStrategy  {

    private List<LineItem> sequence = new ArrayList<LineItem>();

    @Override
    public void createProduct() {
        System.out.println("Chairs production is in progress");
    }



}
