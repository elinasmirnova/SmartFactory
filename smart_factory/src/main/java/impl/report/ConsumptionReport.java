package impl.report;

import impl.Factory;
import impl.Line;
import impl.lineItems.LineItem;
import impl.lineItems.Machine;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ConsumptionReport implements Report{

    private Factory factory;

    public ConsumptionReport(Factory factory) {
        this.factory = factory;
    }

    public void generateReport() {
        String filename = "ConsumptionReport.json";
        JSONObject consumption = new JSONObject();
        consumption.put("factory", factory.getName());
        consumption.put("takt", factory.getTick());

        JSONArray lines = new JSONArray();
        JSONArray lineItems = new JSONArray();
        JSONObject jsonLine = new JSONObject();
        JSONObject itemConsumption = new JSONObject();
        for (Line line:factory.getLines()) {

            for (Machine machine:line.getMachines()) {
                itemConsumption.put("oil", machine.getOil());
                itemConsumption.put("electricity", machine.getEC());
                itemConsumption.put("material", machine.getMat());

                lineItems.add(new JSONObject().put(machine.getName(), itemConsumption));
                itemConsumption.clear();
            }
            jsonLine.put("product", line.getProduct().getName());
            jsonLine.put("lineItems", lineItems);
            jsonLine.put("oilTotal", line.getTotalOil());
            jsonLine.put("electricityTotal", line.getTotalEC());
            jsonLine.put("materialTotal", line.getTotalMat());

            lines.add(jsonLine);
            lineItems.clear();
            jsonLine.clear();
        }
        consumption.put("lines", lines);

        try {
            Files.write(Paths.get(filename), consumption.toJSONString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
