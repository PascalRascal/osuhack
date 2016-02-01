package hackohio.medqr;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Blaise on 11/14/2015.
 */
public class InsuranceData {
    String name,address,DOB,city,employer,provider,state,zip,number;
    public InsuranceData(){}
    public InsuranceData(JSONObject jo){
        try {
            name = jo.getString("name");
            address = jo.getString("adrs");
            city = jo.getString("city");
            state = jo.getString("state");
            zip = jo.getString("zip");
            DOB = jo.getString("DOB");
            employer = jo.getString("employer");
            provider = jo.getString("provider");
            number  = jo.getString("number");
        }
        catch(JSONException e){e.printStackTrace();}

    }
    public JSONObject toJSON(){
        JSONObject jo = new JSONObject();
        try{
            jo.put("name",name);
            jo.put("adrs",address);
            jo.put("city",city);
            jo.put("state",state);
            jo.put("zip",zip);
            jo.put("DOB",DOB);
            jo.put("employer", employer);
            jo.put("provider",provider);
            jo.put("number",number);

        }catch(JSONException e){e.printStackTrace();}
        return jo;

    }

}
