package impl.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public enum ProductEnum {
    CHAIR(new ArrayList<>(Arrays.asList("Saw", "Saw operator", "Lathe", "Sander", "Operator"))),
    TABLE(new ArrayList<>(Arrays.asList("Saw", "Saw operator", "Lathe", "Sander", "Operator"))),
    WARDROBE(new ArrayList<>(Arrays.asList("Saw", "Saw operator", "Lathe", "Sander", "Operator")));

   private List<String> lineItemSequence;

    ProductEnum(List<String> sequence) {
        this.lineItemSequence = sequence;
    }

    public List<String> getLineItemSequence() {
        return lineItemSequence;
    }
}
