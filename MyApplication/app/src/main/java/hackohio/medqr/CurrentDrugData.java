package hackohio.medqr;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Blaise on 11/14/2015.
 */
public class CurrentDrugData {
    String med1,med2,med3,med4;
    String dose1,dose2,dose3,dose4;
    String rate1,rate2,rate3,rate4;
    public CurrentDrugData(){}
    public CurrentDrugData(JSONObject jo){
        try{
            med1 = jo.getString("med1");
            med2 = jo.getString("med2");
            med3 = jo.getString("med3");
            med4 = jo.getString("med4");
            dose1 = jo.getString("dose1");
            dose2 = jo.getString("dose2");
            dose3 = jo.getString("dose3");
            dose4 = jo.getString("dose4");
            rate1 = jo.getString("rate1");
            rate2 = jo.getString("rate2");
            rate3 = jo.getString("rate3");
            rate4 = jo.getString("rate4");

        }catch(JSONException e){e.printStackTrace();}
    }

    public JSONObject toJSON(){
        JSONObject jo = new JSONObject();
        try {
            jo.put("med1", med1);
            jo.put("med2", med2);
            jo.put("med3", med3);
            jo.put("med4'", med4);
            jo.put("dose1", dose1);
            jo.put("dose2",dose2);
            jo.put("dose3",dose3);
            jo.put("dose4",dose4);
            jo.put("rate1",rate1);
            jo.put("rate2",rate2);
            jo.put("rate3",rate3);
            jo.put("rate4",rate4);
        }catch(JSONException e){e.printStackTrace();}
        return jo;
    }
}
