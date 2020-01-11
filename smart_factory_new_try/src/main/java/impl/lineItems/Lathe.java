package impl.lineItems;

public class Lathe extends Machine {
    private final int oil = 2;
    private final int ec = 4;
    private final int repairTime = 1;

    public Lathe(int id, String name) {
        super(id, name);
    }

    @Override
    public int getRepairTime() {
        return repairTime;
    }

}
