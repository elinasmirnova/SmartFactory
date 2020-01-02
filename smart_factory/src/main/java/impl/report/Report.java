package impl.report;

import impl.Factory;

public class Report {

    private Factory factory;

    private FactoryConfigurationReport factoryConfigurationReport;


    public Report(Factory factory) {
        this.factory = factory;
        this.factoryConfigurationReport = new FactoryConfigurationReport(factory);

    }

    public Factory getFactory() {
        return factory;
    }

    public void setFactory(Factory factory) {
        this.factory = factory;
    }

    public void generateReport() {

    }
}
