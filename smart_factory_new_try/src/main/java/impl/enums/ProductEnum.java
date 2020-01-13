package impl.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Types of products producing in the factory. Each type of the product contains the sequence of line items.
 * All the working line items must be ordered according to this sequence.
 */
public enum ProductEnum {
    CHAIR(new ArrayList<>(Arrays.asList("Saw", "Operator", "Lathe", "Sander", "Operator"))),
    TABLE(new ArrayList<>(Arrays.asList("Saw", "Operator", "Miller", "Lathe", "Sander", "Polisher", "Operator"))),
    WARDROBE(new ArrayList<>(Arrays.asList("Saw", "Operator", "Miller", "Operator", "Lathe",  "Sander", "Polisher", "Operator")));

   private List<String> lineItemSequence;

    ProductEnum(List<String> sequence) {
        this.lineItemSequence = sequence;
    }

    public List<String> getLineItemSequence() {
        return lineItemSequence;
    }
}
