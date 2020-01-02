package impl.report;

import impl.Factory;
import impl.Line;
import impl.lineItems.LineItem;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;

public class FactoryConfigurationReport {

    private Factory factory;

    public FactoryConfigurationReport(Factory factory) {
        this.factory = factory;
    }

    public void generateReport() throws Exception {
        String filename = "FactoryConfigurationReport.json";
        JSONObject factoryConfig = new JSONObject();
        factoryConfig.put("factory", factory.getName());
        factoryConfig.put("takt", factory.getTick());

        JSONArray lines = new JSONArray();
        JSONArray lineItems = new JSONArray();
        for (Line line:factory.getLines()) {

            for (LineItem lineItem:line.getSequence()) {
                lineItems.add(new JSONObject().put(lineItem.getTypeId(), lineItem.getName()));
            }
            JSONObject jsonLine = new JSONObject();
            jsonLine.put("product", line.getProduct().getName());
            jsonLine.put("lineItems", lineItems);
            lines.add(jsonLine);
            lineItems.clear();
        }
        factoryConfig.put("lines", lines);

        Files.write(Paths.get(filename), factoryConfig.toJSONString().getBytes());
    }
}
