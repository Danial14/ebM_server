import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonConverter {
    private static JsonConverter jsonConverter;
    private JsonConverter(){

    }
    public static JsonConverter getInstance(){
        if(jsonConverter == null){
            jsonConverter = new JsonConverter();
        }
        return jsonConverter;
    }
    public JSONObject convertToJson(List<Map<String, Object>> resultTable){
        JSONObject MainJsonObject = new JSONObject();
        JSONArray data = new JSONArray();
        resultTable.forEach(row -> {
            JSONObject object = new JSONObject();
            row.forEach( (key, value) -> {
                System.out.println(key + " : " + value);
                object.put(key, value);
            });
            data.put(object);
        });
        MainJsonObject.put("array", data);
        return MainJsonObject;
    }
}
