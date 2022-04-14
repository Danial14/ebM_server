import com.sap.conn.jco.JCoException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class test_servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        SapConnect sapConnect = SapConnect.getInstance();
        if(sapConnect.startConnection()){
            JSONObject data = handleConnection();
            if(data != null){
                resp.getWriter().write(data.toString());
            }
            else{
                resp.getWriter().write("{\"error\" : \"soMething went wrong\"");
            }
        }
    }
    private JSONObject handleConnection(){
        SapManager sapManager = SapConnect.getInstance().getSapManager();
        try {
            SapFunction sapFunction = sapManager.getFunction("ZBAPI_GET_PENDING_TRANSFER_ORD");
            sapFunction.getImportParameterList().setValue("BATCH_NO", "E300113443");
            sapFunction.getImportParameterList().setValue("WAREHOUSE_NO", "KH6");
            SapFunctionResult sapFunctionResult = sapFunction.execute();
            List<Map<String, Object>> resultTable = sapFunctionResult.getTable("ITAB");

            // print result table
            System.out.println("print SAP_RESULT_TABLE_NAME");
            JsonConverter jsonConverter = JsonConverter.getInstance();
            JSONObject data = jsonConverter.convertToJson(resultTable);
            if(data != null){
                return data;
            }
        } catch (JCoException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
