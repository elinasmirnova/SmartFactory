package impl.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import impl.Factory;
import impl.Line;
import impl.Observer;
import impl.Tick;
import impl.events.Event;
import impl.lineItems.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Report implements Observer {

    private Tick tick = Tick.getInstance();
    private Factory factory;

    private HashMap<Integer, HashMap<Integer, Integer>> consumptionHistory = new HashMap<>();

    public Report(Factory factory) {
        this.factory = factory;
        tick.attach(this);
    }

    public void update() {

        saveConsumption();
    }

    private void saveConsumption() {
//        System.out.println("\n\n\nSAVE " + tick.getCurrentTick() + " - " + calculateConsumption() +"\n\n\n");
        consumptionHistory.put(tick.getCurrentTick(), calculateConsumption());
    }

    private HashMap<Integer, Integer> calculateConsumption() {
        HashMap<Integer, Integer> lineConsumption = new HashMap<>();
        int total = 0;
//        System.out.println("\n\n\nSAVE " + factory.getLines().size() + " - " + factory.getLines().get(0).getMachines().size() +"\n\n\n");

        for(Line line : factory.getLines()) {
            //TODO: Add product material
            if (line.isWorking()) {
                for(Machine machine : line.getMachines()) {
                    total += machine.getConsumptionPerTick();
                }
                lineConsumption.put(line.getId(), total);
                total = 0;
            }
        }
        return lineConsumption;
    }

    public void generateConsumptionReport(int from, int to) {
        HashMap<Integer, Integer> lineTotal = consumptionHistory.get(from);
        System.out.println("\n\n\nGENERATING REPORT\n\n\n");
        for (int i=from; i<to; i++) {
            consumptionHistory.get(i).forEach(
                    (key, value) -> lineTotal.merge( key, value, (v1, v2) -> v1 + v2)
            );
        }


        try {
            Writer writer = new FileWriter("/Users/valta/School/OMO/smart_factory/smart_factory_new_try/src/consumption.json");
            Gson gson = new GsonBuilder().create();
            gson.toJson(lineTotal, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

//    private HashMap<String, HashMap<String, Integer>> machinesConsumptionStats() {
//        HashMap<String, HashMap<String, Integer>> machines = new HashMap<>();
//        ArrayList<Machine> machineTypes = new ArrayList<Machine>(Arrays.asList(new Lathe( ), new Miller()));
//        Lathe lathe;
//        Miller miller;
//        Polisher polisher;
//        Sander sander;
//        Saw saw;
//
//        machines.
//    }

    public void generateFactoryConfiguration() {

    }

//    public void generateOutagesReport() {
//        HashMap<Integer, Event> eventHistory = new HashMap<>();
//        ArrayList<Integer> outages = new ArrayList<>();
//        ArrayList<Integer>
//    }
}
