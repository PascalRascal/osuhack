package hackohio.medqr;

import java.util.ArrayList;
import android.widget.BaseExpandableListAdapter;
import android.view.LayoutInflater;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class dataTypeAdapter extends BaseExpandableListAdapter{

    private ArrayList<dataType> placesList;
    private LayoutInflater mInflater;
    EditText firstName,lastName,addressLine1,addressLine2,city,state,zip,email,DOB, SSN, phonenum, MI;
    EditText insurName, insurDOB, insurRelation, insurAddress, insurState, insurZip, insurEmployer, insurProvider, insurCity, insurNum;
    EditText med1,med2,med3,med4,dose1,dose2,dose3,dose4,rate1,rate2,rate3,rate4;
    EditText ecName, ecPhone, ecAlternatePhone, ecRelation;

    View basictemp;
    View insuranceTemp;
    View currentmedTemp;
    View ecTemp;


    public dataTypeAdapter(ArrayList<dataType> placesList,Context context) {
        this.placesList=placesList;
        mInflater=LayoutInflater.from(context);
        basictemp = this.basicView();
        insuranceTemp = this.insuranceView();
        currentmedTemp =this.currentDrugView();
        ecTemp = this.emergencycontactView();
    }
    //return child at specific position.
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return placesList.get(groupPosition);
    }

    //return child id for specific position.
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    //return child view at specific position.
    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        if(placesList.get(groupPosition).isBasic()) {
            convertView = basictemp;
        }
        if(placesList.get(groupPosition).isInsurance()){
            convertView = insuranceTemp;

        }
        if (placesList.get(groupPosition).isCurrentMed()) {
            convertView = currentmedTemp;
        }
        if (placesList.get(groupPosition).isEmergencyContact()){
            convertView = ecTemp;
        }


        return convertView;
    }

    //return number of child for specific parent.
    @Override
    public int getChildrenCount(int groupPosition) {
        return 1; //Thi smight not work im working
    }

    //get parent at specific position.
    @Override
    public Object getGroup(int groupPosition) {
        return placesList.get(groupPosition);
    }

    //return number of parent items.
    @Override
    public int getGroupCount() {
        return placesList.size();
    }

    //return parent id for specific position
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //return parent view for specific position
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {

        ParentHolder parentHolder;

        if(convertView==null) {

            parentHolder=new ParentHolder();

            convertView=mInflater.inflate(R.layout.parent_text,null);

            parentHolder.parentTv=(TextView) convertView.findViewById(R.id.parent_text_title);

            convertView.setTag(parentHolder);

        }else{
            parentHolder=(ParentHolder) convertView.getTag();
        }
        parentHolder.parentTv.setText(placesList.get(groupPosition).getName());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    private class ParentHolder {
        TextView parentTv;
    }


    public View basicView(){
        View databasicView = mInflater.inflate(R.layout.input_basic, null);
        firstName = (EditText) databasicView.findViewById(R.id.basic_Fname);
        lastName = (EditText) databasicView.findViewById(R.id.basic_Lname);
        addressLine1 = (EditText) databasicView.findViewById(R.id.basic_AddressLine1);
        addressLine2 = (EditText) databasicView.findViewById(R.id.basic_AddressLine2);
        city = (EditText) databasicView.findViewById(R.id.basic_City);
        state = (EditText) databasicView.findViewById(R.id.basic_State);
        zip = (EditText) databasicView.findViewById(R.id.basic_Zip);
        phonenum = (EditText) databasicView.findViewById(R.id.basic_Phone);
        email = (EditText) databasicView.findViewById(R.id.basic_Email);
        SSN = (EditText)databasicView.findViewById(R.id.basic_SSN);
        MI = (EditText)databasicView.findViewById(R.id.basic_MI);
        DOB = (EditText)databasicView.findViewById(R.id.basic_DOB);


        return databasicView;
    }
    public BasicData createBasic(){
        BasicData bd = new BasicData();
        bd.setFname(firstName.getText().toString());
        bd.setLname(lastName.getText().toString());
        bd.setAddress(addressLine1.getText().toString());
        bd.setAddressln2(addressLine2.getText().toString());
        bd.setCity(city.getText().toString());
        bd.setState(state.getText().toString());
        bd.setZip(zip.getText().toString());
        bd.setDOB(DOB.getText().toString());
        bd.setSSN(SSN.getText().toString());
        bd.setMI(MI.getText().toString());
        bd.setPhone(phonenum.getText().toString());
        bd.setEmail(email.getText().toString());
        return bd;
    }

    public View insuranceView(){
        View datainsuranceView = mInflater.inflate(R.layout.input_insurance,null);
        insurName = (EditText) datainsuranceView.findViewById(R.id.insur_name);
        insurAddress=(EditText)datainsuranceView.findViewById(R.id.insur_address);
        insurDOB = (EditText)datainsuranceView.findViewById(R.id.insur_dob);
        insurCity = (EditText)datainsuranceView.findViewById(R.id.insur_city);
        insurRelation = (EditText)datainsuranceView.findViewById(R.id.insur_relation);
        insurEmployer = (EditText)datainsuranceView.findViewById(R.id.insur_employer);
        insurProvider = (EditText)datainsuranceView.findViewById(R.id.insur_provider);
        insurState=(EditText)datainsuranceView.findViewById(R.id.insur_state);
        insurZip=(EditText)datainsuranceView.findViewById(R.id.insur_zip);
        insurNum = (EditText)datainsuranceView.findViewById(R.id.insur_number);

        return datainsuranceView;


   }
    public InsuranceData createInsurance(){
        JSONObject jo = new JSONObject();
        try {
            jo.put("name", insurName.getText().toString());
            jo.put("adrs", insurAddress.getText().toString());
            jo.put("city", insurCity.getText().toString());
            jo.put("state",insurState.getText().toString());
            jo.put("zip",insurZip.getText().toString());
            jo.put("DOB",insurDOB.getText().toString());
            jo.put("employer",insurEmployer.getText().toString());
            jo.put("provider",insurProvider.getText().toString());
            jo.put("number",insurNum.getText().toString());
        }
        catch(JSONException e){e.printStackTrace();}
        InsuranceData insd = new InsuranceData(jo);
        return insd;


    }

    public View currentDrugView(){
        View datadrugView = mInflater.inflate(R.layout.input_currentdrug, null);
        med1=(EditText)  datadrugView.findViewById(R.id.current_med1);
        med2=(EditText)datadrugView.findViewById(R.id.current_med2);
        med3=(EditText)datadrugView.findViewById(R.id.current_med3);
        med4=(EditText)datadrugView.findViewById(R.id.current_med4);
        dose1=(EditText)datadrugView.findViewById(R.id.current_dose1);
        dose2=(EditText)datadrugView.findViewById(R.id.current_dose2);
        dose3=(EditText)datadrugView.findViewById(R.id.current_dose3);
        dose4=(EditText)datadrugView.findViewById(R.id.current_dose4);
        rate1=(EditText)datadrugView.findViewById(R.id.current_rate1);
        rate2=(EditText)datadrugView.findViewById(R.id.current_rate2);
        rate3=(EditText)datadrugView.findViewById(R.id.current_rate3);
        rate4=(EditText)datadrugView.findViewById(R.id.current_rate4);

        return datadrugView;
    }
    public JSONObject createCurrentDrug(){
        JSONObject jo = new JSONObject();
        try {
            jo.put("med1", med1.getText().toString());
            jo.put("med2", med2.getText().toString());
            jo.put("med3", med3.getText().toString());
            jo.put("med4", med4.getText().toString());
            jo.put("dose1",dose1.getText().toString());
            jo.put("dose2",dose2.getText().toString());
            jo.put("dose3",dose3.getText().toString());
            jo.put("dose4",dose4.getText().toString());
            jo.put("rate1",rate1.getText().toString());
            jo.put("rate2",rate2.getText().toString());
            jo.put("rate3",rate3.getText().toString());
            jo.put("rate4",rate4.getText().toString());
        }
        catch(JSONException e){e.printStackTrace();}
        return jo;
    }
    public View emergencycontactView(){
        View ecView = mInflater.inflate(R.layout.input_emergencycontact, null);
        ecName = (EditText)ecView.findViewById(R.id.ecname);
        ecPhone = (EditText)ecView.findViewById(R.id.ecphonenumber);
        ecAlternatePhone = (EditText) ecView.findViewById(R.id.ecalternatephonenumber);
        ecRelation = (EditText) ecView.findViewById(R.id.ecrelation);

        return ecView;
    }
    public JSONObject createEmergencyContact(){
        JSONObject jo = new JSONObject();
        try{
            jo.put("ecPhone", ecPhone.getText().toString());
            jo.put("ecRelation", ecRelation.getText().toString());
            jo.put("ecName", ecName.getText().toString());
            jo.put("ecAlternatePhone", ecAlternatePhone.getText().toString());
        }catch(JSONException e){e.printStackTrace();}
        return jo;
    }

}
