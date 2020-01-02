package impl.strategy;

import impl.lineItems.LineItem;

import java.util.List;

public interface ProductStrategy {

    void setLineItems();

    List<String> getSequence();

}
