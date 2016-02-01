package hackohio.medqr;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Blaise on 11/14/2015.
 */
public class EmergencyContactData {
    String ecName, ecPhone, ecRelationship,ecAlternatePhone;

    public EmergencyContactData(){}
    public EmergencyContactData(JSONObject jo){
        try{
            ecName = jo.getString("ecName");
            ecPhone = jo.getString("ecPhone");
            ecRelationship = jo.getString("ecRelation");
            ecAlternatePhone = jo.getString("ecAlternatePhone");
        }
        catch(JSONException e){e.printStackTrace();}

    }

    public JSONObject toJSON(){
        JSONObject jo = new JSONObject();
        try {
            jo.put("ecName", ecName);
            jo.put("ecPhone", ecPhone);
            jo.put("ecRelation", ecRelationship);
            jo.put("ecAlternatePhone", ecAlternatePhone);
        }catch(JSONException e){e.printStackTrace();}
        return jo;
    }
}
