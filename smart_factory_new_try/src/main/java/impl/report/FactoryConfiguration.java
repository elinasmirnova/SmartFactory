//package impl.report;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import impl.Factory;
//import impl.Line;
//import impl.Tick;
//import impl.lineItems.LineItem;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.Writer;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//
//public class FactoryConfiguration {
//
//    private Factory factory;
//
//    /**
//     * Generates json configuration of the current state of the factory
//     * @param factory
//     */
//    public FactoryConfiguration(Factory factory) {
//        this.factory = factory;
//    }
//
//    public void generateConfiguration(String filename) {
//        JSONObject factoryConfig = new JSONObject();
//        factoryConfig.put("factory", factory.getName());
////        factoryConfig.put("takt", Tick.getInstance().getCurrentTick());
//        factoryConfig.put("repairmen",);
//
//        JSONArray lines = new JSONArray();
//        JSONArray lineItems = new JSONArray();
//        for (Line line:factory.getLines()) {
//
//            for (LineItem lineItem:line.getWorkingItems()) {
//                lineItems.add(new JSONObject().put(lineItem.getName(), lineItem.getName()));
//            }
//          JSONObject jsonLine = new JSONObject();
//          jsonLine.put("product", line.getProductType().toString());
//            jsonLine.put("lineItems", lineItems);
//          lines.add(jsonLine);
//            lineItems.clear();
//        }
//        factoryConfig.put("lines", lines);
//
//
//        try {
//            Files.write(Paths.get(filename), factoryConfig.toJSONString().getBytes());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            Writer writer = new FileWriter("/Users/valta/School/OMO/smart_factory/smart_factory_new_try/src/configuration.json");
//            Gson gson = new GsonBuilder().setPrettyPrinting().create();
//            gson.toJson(factory, writer);
//            writer.flush();
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
