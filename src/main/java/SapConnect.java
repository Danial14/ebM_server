import com.sap.conn.jco.JCoException;

import java.io.IOException;

public class SapConnect {
    private static SapConnect sapConnect;
    private SapManager sapManager;
    private SapConnect(){

    }
    public static SapConnect getInstance(){
        if(sapConnect == null){
            sapConnect = new SapConnect();
        }
        return sapConnect;
    }
    public boolean startConnection(){
        if(sapManager == null) {
            try {

                SapManagerBuilder sapManagerBuilder = SapManager
                        .builder()
                        .set(SapManagerBuilderOption.ASHOST, "192.168.0.137") // AS host
                        .set(SapManagerBuilderOption.SYSNR, "00") // system number
                        .set(SapManagerBuilderOption.LANG, "en") // language code
                        .set(SapManagerBuilderOption.CLIENT, "100") // client number
                        .set(SapManagerBuilderOption.USER, "emergtech") // user
                        .set(SapManagerBuilderOption.PASSWD, "Sooper007");

                sapManager = sapManagerBuilder.build();

            } catch (JCoException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sapManager != null;
    }
    public SapManager getSapManager(){
        return sapManager;
    }
}
