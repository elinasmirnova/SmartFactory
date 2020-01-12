package impl.report;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import impl.Factory;
import impl.Line;
import impl.Observer;
import impl.Tick;
import impl.enums.ProductEnum;
import impl.events.Event;
import impl.events.EventHandler;
import impl.lineItems.*;
import impl.product.Product;
import impl.repairman.RepairmenPool;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Report implements Observer {

    private Tick tick = Tick.getInstance();
    private Factory factory;
    private List<LineItem> items;
    private String path = "src/";

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

    /**
     * Generuje report o spotrebe tovarny
     * @param from
     * @param to
     */
    public void generateConsumptionReport(int from, int to) {
        HashMap<Integer, Integer> lineTotal = consumptionHistory.get(from);
        Integer factoryTotal = 0;
        System.out.println("\n\n\nGENERATING REPORT\n\n\n");
        for (int i=from; i<to; i++) {
            consumptionHistory.get(i).forEach(
                    (key, value) -> lineTotal.merge( key, value, (v1, v2) -> v1 + v2)
            );
        }
        for (int value : lineTotal.values()) {
            factoryTotal += value;
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = new JsonObject();
        JsonElement lineJson = gson.toJsonTree(lineTotal);
        JsonElement machinesJson = gson.toJsonTree(machinesConsumptionStats());
        jsonObject.add("lines", lineJson);
        jsonObject.add("machineConsumptionStatsPerTick", machinesJson);
        jsonObject.addProperty("factoryTotal", factoryTotal);

        try {
            Writer writer = new FileWriter(path+"consumption.json");
            gson.toJson(jsonObject, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Generuje report eventu za urcite obdobi
     * @param from
     * @param to
     */
    public void generateEventReport(int from, int to) {
        ArrayList<Event> allEvents = EventHandler.getInstance().getEventHistory();
        ArrayList<Event> intervalEvents = new ArrayList<>();
        HashMap<String, HashMap<String, ArrayList<String>>> eventsSorted = new HashMap<>();
        HashMap<String, ArrayList<String>> eventsBySource = new HashMap<>();
        HashMap<String, ArrayList<String>> eventsByType = new HashMap<>();

        eventsByType.put("Breakdown", new ArrayList<>());
        eventsByType.put("Start Repair", new ArrayList<>());
        eventsByType.put("Finish Repair", new ArrayList<>());

        for (Event event : allEvents) {
            if ( from <= event.getTickStarted() && event.getTickStarted() <= to) {

                // By type
                String type = event.getType();
                switch (type) {
                    case "Breakdown":
                        eventsByType.get(type).add(event.toString());
                    case "Start Repair":
                        eventsByType.get(type).add(event.toString());
                    case "Finish Repair":
                        eventsByType.get(type).add(event.toString());
                }

                // By source
                if (eventsBySource.containsKey(String.valueOf(event.getMachine().getId()))) {
                    eventsBySource.get(String.valueOf(event.getMachine().getId())).add(event.toString());
                } else {
                    ArrayList<String> eventList = new ArrayList<>();
                    eventList.add(event.toString());
                    eventsBySource.put(String.valueOf(event.getMachine().getId()), eventList);
                }
            }
        }

        eventsSorted.put("bySourceMachine", eventsBySource);
        eventsSorted.put("byType", eventsByType);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            Writer writer = new FileWriter(path+"events.json");
            gson.toJson(eventsSorted, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private HashMap<String, HashMap<String, Integer>> machinesConsumptionStats() {
        HashMap<String, HashMap<String, Integer>> machines = new HashMap<>();
        for (Line line : factory.getLines()) {
            for (Machine machine : line.getMachines() ) {
                HashMap<String, Integer> machineStats = new HashMap<>();
                machineStats.put("totalInCZK", machine.getConsumptionPerTick());
                machineStats.put("oil", machine.getOil());
                machineStats.put("electricity", machine.getEc());
                machines.put(machine.getName(), machineStats);
            }
        }
        return machines;
    }

    /**
     * Vygeneruje konfiguraci tovarny
     */
    public void generateFactoryConfiguration() {
        HashMap<Integer, ProductEnum> lines = new HashMap<>();
        HashMap<Integer, String> lineItems = new HashMap<>();


        // Lines
        for (Line line : factory.getLines()) {
            lines.put(line.getId(), line.getProductType());
        }
        // Items
        for (LineItem item : items) {
            lineItems.put(item.getId(), item.getType());
        }
        // Repairmen
        RepairmenPool pool = RepairmenPool.getInstance();
        int repairmen = pool.getAvailableRepairmen().size() + pool.getWorkingRepairmen().size();

        // To JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = new JsonObject();
        JsonElement linesJson = gson.toJsonTree(lines);
        JsonElement itemsJson = gson.toJsonTree(lineItems);
        jsonObject.addProperty("factory", factory.getName());
        jsonObject.add("lines", linesJson);
        jsonObject.add("items", itemsJson);
        jsonObject.addProperty("repairmen", repairmen);

        try {
            Writer writer = new FileWriter(path+"configuration.json");
            gson.toJson(jsonObject, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveItems(List<LineItem> items) {
//        System.out.println("\n\n\n ITEMS: \n"+items.size()+"\n");
        this.items = items;
    }

}
