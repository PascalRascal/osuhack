package hackohio.medqr;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import org.json.JSONObject;
import java.io.FileNotFoundException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import android.content.Intent;

public class newMedData extends AppCompatActivity {
    ArrayList<dataType> dataList = new ArrayList<dataType>();
    String basicdata;
    dataTypeAdapter dta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_med_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeData();

        ExpandableListView explist = (ExpandableListView) findViewById(R.id.expandableListView);
        dta = new dataTypeAdapter(dataList, this);
        explist.setAdapter(dta);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                basicdata = dta.createBasic().toJSON().toString();
                System.out.println(basicdata);
                Intent myIntent = new Intent(newMedData.this, MainActivity.class); //Writes data xd ^_^

                try {
                    FileOutputStream outputStream = getApplicationContext().openFileOutput("basicData", Context.MODE_PRIVATE);
                    outputStream.write(dta.createBasic().toJSON().toString().getBytes());
                    outputStream.close();
                }catch(FileNotFoundException e){e.printStackTrace();} catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    FileOutputStream outputStream = getApplicationContext().openFileOutput("insuranceData", Context.MODE_PRIVATE);
                    outputStream.write(dta.createInsurance().toJSON().toString().getBytes());  //FUCK I COULD HAVE JUST GONE STRAIGHT TO JSON GODDAMN IT FUCK EVERYTHING E V E R Y T H I N G
                    outputStream.close();
                }catch(FileNotFoundException e){e.printStackTrace();} catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    FileOutputStream outputStream = getApplicationContext().openFileOutput("currentmedData", Context.MODE_PRIVATE);
                    outputStream.write(dta.createCurrentDrug().toString().getBytes());
                    outputStream.close();
                }catch(FileNotFoundException e){e.printStackTrace();} catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    FileOutputStream outputStream = getApplicationContext().openFileOutput("emergencycontactData", Context.MODE_PRIVATE);
                    outputStream.write(dta.createEmergencyContact().toString().getBytes());
                    outputStream.close();
                }catch(FileNotFoundException e){e.printStackTrace();} catch (IOException e) {
                    e.printStackTrace();
                }
                newMedData.this.startActivity(myIntent);

            }
        });


    }
    public void initializeData(){
        dataType personal = new dataType("Basic Data");
        personal.setBasic();
        dataType insurance = new dataType("Insurance Info");
        insurance.setInsurance();
        dataType currentmed = new dataType("Current Medication");
        currentmed.setCurrentMed();
        dataType emergencycontact = new dataType("Emergency Contact");
        emergencycontact.setEmergencyContact();
        dataList.add(personal);
        dataList.add(insurance);
        dataList.add(currentmed);
        dataList.add(emergencycontact);
    }

}
