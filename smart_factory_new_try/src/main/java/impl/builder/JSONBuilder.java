package impl.builder;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import impl.Factory;
import impl.Line;
import impl.Tick;
import impl.enums.MachineType;
import impl.lineItems.LineItem;
import impl.lineItems.MachineFactory;
import impl.lineItems.Worker;
import impl.product.Chair;
import impl.product.Product;
import impl.product.Table;
import impl.product.Wardrobe;
import impl.repairman.Repairman;
import impl.repairman.RepairmenPool;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSONBuilder implements Builder {

    private String factoryName;
    private HashMap<String, String> linesConfig;
    private HashMap<String, String> items;
    private Long repairmenConfig;

    private Factory factory;
    private MachineFactory machineFactory;
    private List<LineItem> availableLineItems = new ArrayList<>();
    private List<Line> lines = new ArrayList<>();
    private static int idCounter = 0;


    /**
     * Postavi tovarnu podle konfigurace v souboru
     * @param filename
     */
    public JSONBuilder(String filename) {
        // JSON file to Java object
        Object obj = null;
        try {
            obj = new JSONParser().parse(new FileReader(filename));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject jo = (JSONObject) obj;
        factoryName = (String) jo.get("factory");
        linesConfig = (HashMap<String, String>) jo.get("lines");
        items = (HashMap<String, String>) jo.get("items");
        repairmenConfig = (Long) jo.get("repairmen");
    }

    @Override
    public void createFactory() {
        factory = new Factory(factoryName);
    }

    @Override
    public void createLines() {
        linesConfig.forEach((k, v) -> lines.add(new Line(factory, Integer.parseInt(k), stringToProduct(v))));
        factory.setLines(lines);
    }

    @Override
    public void createLineItems() {
        items.forEach((k, v) -> createItem(Integer.parseInt(k), v));
        factory.setAvailableLineItems(availableLineItems);
    }

    @Override
    public void setRepairmen() {
        LinkedList<Repairman> repairmen = new LinkedList<>();
        for (int i = 0; i < Math.toIntExact(repairmenConfig); i++) {
            repairmen.add(new Repairman(++idCounter, true));
        }
        RepairmenPool.getInstance().setAvailableRepairmen(repairmen);
    }

    @Override
    public void startTicking() {
        factory.startProduction();
        Tick.getInstance().run();
    }

    private Product stringToProduct(String string) {
        Product product;
        switch (string) {
            case "CHAIR": product = new Chair();
                break;
            case "TABLE": product = new Table();
                break;
            case "WARDROBE": product = new Wardrobe();
                break;
            default: product = new Chair();
        }
        return product;
    }

    private MachineType stringToType(String string) {
        MachineType type;
        switch (string) {
            case "MILLER": type = MachineType.MILLER;
                break;
            case "POLISHER": type = MachineType.POLISHER;
                break;
            case "SANDER": type = MachineType.SANDER;
                break;
            case "SAW": type = MachineType.SAW;
                break;
            default: type = MachineType.LATHE;
        }
        return type;
    }

    private void createItem(int key, String value) {
        if (value.equals("WORKER")) {
            availableLineItems.add(new Worker(key,"Operator"));
        } else {
            availableLineItems.add(MachineFactory.getInstance().createMachine(key, stringToType(value)));
        }
    }
}
