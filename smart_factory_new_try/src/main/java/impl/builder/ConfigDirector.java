package impl.builder;

public class ConfigDirector {
    public void startConfiguration(Builder builder) {
        builder.createFactory();
        builder.createLines();
        builder.createLineItems();
        builder.setRepairmen();
        builder.startTicking();
    }
}
