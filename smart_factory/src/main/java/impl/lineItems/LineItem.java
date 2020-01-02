package impl.lineItems;


import impl.Entity;
import impl.Observer;
import impl.products.Product;

public abstract class LineItem implements Entity, Observer{

    private int typeId;
    private String name;
    private String id;

    private LineItem nextLineItem;

    public LineItem(String id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LineItem getNextLineItem() {
        return nextLineItem;
    }

    public void setNextLineItem(LineItem nextLineItem) {
        this.nextLineItem = nextLineItem;
    }

    public void work(Product product) {
        if (getNextLineItem() == null) {
            System.out.println("The product is done");
        }
        getNextLineItem().work(product);
    }

    public String getName() {
        return name;
    }


}
