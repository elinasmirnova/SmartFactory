package impl;

import impl.strategy.ChairStrategy;
import impl.strategy.ProductStrategy;

public class JsonConfig {

    private static JsonConfig instance = null;

    public static JsonConfig getInstance() {
        if (instance == null) {
            ObjectMapper objectMapper = new ObjectMapper();

        }

        return instance;
    }


}
