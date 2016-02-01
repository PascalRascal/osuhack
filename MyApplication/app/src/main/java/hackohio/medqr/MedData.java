package hackohio.medqr;

import org.json.JSONException;
import org.json.JSONObject;

public class MedData {
    String name;
    BasicData basicData;
    InsuranceData insData;
    CurrentDrugData cdData;
    EmergencyContactData ecData;
    String content;

    public MedData(BasicData bd, InsuranceData id, CurrentDrugData cd, EmergencyContactData ec){
        this.basicData = bd;
        this.insData = id;
        this.cdData = cd;
        this.ecData = ec;
        name = bd.Fname + " " +  bd.Lname;
    }

    public String JSONstringContent(){
        JSONObject content = new JSONObject();
        try{

        content.put("BasicData", basicData.toJSON());
        content.put("InsuranceData",insData.toJSON());
            content.put("CurrentMedData",cdData.toJSON() );
            content.put("EmergencyContactData", ecData.toJSON());
        }
        catch(JSONException e){e.printStackTrace();}
        return content.toString();
    }
    public String getName(){
        return name;
    }
}
