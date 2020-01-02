package impl;

public class FactoryConfiguration {

    /***
     * configure factory using the builder pattern
     * @param builder builder
     */
    public void startConfiguration(Builder builder) {
        builder.createFactory();
        builder.createLines();
        builder.createLineItems();
        builder.generateReports();
        builder.startTicking();
        //TODO
    }

}
