package hackohio.medqr;

import org.json.JSONObject;
import org.json.JSONException;

/**
 * Created by Blaise on 11/14/2015.
 */
public class BasicData {
    String Fname,Lname,MI,address,addressln2,city,state,zip,SSN,DOB,phone,email;
    JSONObject basicJSON;
    public BasicData(){};
    public BasicData(JSONObject jo){
        try {
            Fname = jo.getString("Fname");
            Lname = jo.getString("Lname");
            MI = jo.getString("MI");
            address = jo.getString("adrs");
            addressln2 = jo.getString("adrsln2");
            city = jo.getString("city");
            state = jo.getString("state");
            zip = jo.getString("zip");
            DOB = jo.getString("DOB");
            SSN = jo.getString("SSN");
            phone = jo.getString("phone");
            email = jo.getString("email");
        }
        catch(JSONException e){e.printStackTrace();}

    }


    public void setFname(String input){
        this.Fname = input;
    }
    public String getFname(){
        return Fname;
    }

    public void setLname(String input){
        this.Lname = input;
    }
    public String getLname(){
        return Lname;
    }

    public void setMI(String input){
        this.MI = input;
    }
    public String getMI() {
        return MI;
    }

    public void setAddress(String input){
        this.address = input;
    }
    public String getAddress(){
        return address;
    }

    public void setAddressln2(String input){
        this.addressln2 = input;
    }
    public String getAddressln2(){
        return addressln2;
    }

    public void setCity(String input){
        this.city = input;
    }
    public String getCity(){
        return city;
    }

    public void setState(String input){
        this.state = input;
    }
    public String getState(){
        return state;
    }

    public void setZip(String input){
        this.zip = input;
    }
    public String getZip(){
        return zip;
    }

    public void setDOB(String input){
        this.DOB = input;
    }
    public String getDOB(){
        return DOB;
    }

    public void setSSN(String input){this.SSN = input;}
    public String getSSN(){return SSN;}

    public void setPhone(String input){
        this.phone = input;
    }
    public String getPhone(){
        return phone;
    }

    public void setEmail(String input){
        this.email = input;
    }
    public String getEmail(){return email;}

    public JSONObject toJSON(){
        try {
            basicJSON = new JSONObject();
            basicJSON.put("Fname", Fname);
            basicJSON.put("Lname", Lname);
            basicJSON.put("MI", MI);
            basicJSON.put("adrs", address);
            basicJSON.put("adrsln2", addressln2);
            basicJSON.put("city", city);
            basicJSON.put("state", state);
            basicJSON.put("zip", zip);
            basicJSON.put("DOB", DOB);
            basicJSON.put("SSN", SSN);
            basicJSON.put("phone", phone);
            basicJSON.put("email", email);
        }catch(JSONException e){e.printStackTrace();}

        return basicJSON;

    }




}
