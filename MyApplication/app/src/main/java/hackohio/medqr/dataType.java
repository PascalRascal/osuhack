package hackohio.medqr;

/**
 * Created by Blaise on 11/14/2015.
 */
public class dataType {
    boolean isBasic;
    boolean isInsurance;
    boolean isCurrentMed;
    boolean isEmergencyContact;
    String name;

    public dataType(String name){
        this.name = name;
    };

    public String getName(){
        return name;
    }
    public void setBasic(){
        isBasic = true;
    }
    public boolean isBasic(){
        return isBasic;
    }

    public void setInsurance(){
        isInsurance = true;
    }
    public boolean isInsurance(){
        return isInsurance;
    }

    public void setCurrentMed(){isCurrentMed = true;}
    public boolean isCurrentMed(){
        return isCurrentMed;
    }
    public void setEmergencyContact(){isEmergencyContact = true;}
    public boolean isEmergencyContact(){return isEmergencyContact;}
}
