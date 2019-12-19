package impl;

public class FactoryConfiguration {

    public void startConfiguration(Builder builder) {
        builder.createFactory();
        builder.createLines();
        builder.createLineItems();
        builder.generateReports();
        builder.startTicking();
        //TODO
    }

}
