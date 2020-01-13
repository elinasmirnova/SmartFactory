package impl;

import impl.builder.ConfigDirector;
import impl.memento.MachineStateOriginator;

public class Main {
    public static void main(String[] args) {
       // ConfigDirector configDirector = new ConfigDirector("src/configuration.json");
        ConfigDirector configDirector = new ConfigDirector();
        System.out.println("Conditions of machines at the tick 103");
        MachineStateOriginator.getInstance().rewindLineItems(103);
    }
}
