package application.builders;

import java.util.HashMap;
import java.util.Map;

public class ParamsBuilder {

    protected final String AMP = "&";
    private Map<String, String> builder = new HashMap<>();


    public void addParams(String key, String value){
        this.builder.put(key, value);
    }

    @Override
    public String toString() {
        StringBuilder params = new StringBuilder();
        for (Map.Entry<String, String> item : this.builder.entrySet()) {
            params.append(item.getKey()).append(item.getValue()).append(AMP);
        }
        return params.toString();
    }
}
