package impl.builder;

public class ConfigDirector {


    public ConfigDirector() {
        startConfiguration(new Factory1Builder());
    }

    public ConfigDirector(String filename) {
        startConfiguration(new JSONBuilder(filename));
    }

    public void startConfiguration(Builder builder) {
        builder.createFactory();
        builder.createLines();
        builder.createLineItems();
        builder.setRepairmen();
        builder.startTicking();
    }
}
