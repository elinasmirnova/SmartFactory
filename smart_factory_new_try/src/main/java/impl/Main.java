package impl;

import impl.builder.ConfigDirector;
import impl.builder.Factory1Builder;

public class Main {
    public static void main(String[] args) {
        ConfigDirector configDirector = new ConfigDirector("/Users/valta/School/OMO/smart_factory/smart_factory_new_try/src/configuration.json");
//        ConfigDirector configDirector = new ConfigDirector();
//        configDirector.startConfiguration(new Factory1Builder());
    }
}
