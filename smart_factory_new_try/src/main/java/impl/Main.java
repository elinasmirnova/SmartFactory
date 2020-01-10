package impl;

import impl.builder.ConfigDirector;
import impl.builder.Factory1Builder;

public class Main {
    public static void main(String[] args) {
        System.out.println("-----------The first configuration of the factory-----------");
        ConfigDirector configDirector = new ConfigDirector();
        configDirector.startConfiguration(new Factory1Builder());
    }
}
