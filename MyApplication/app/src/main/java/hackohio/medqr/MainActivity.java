package hackohio.medqr;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.ListView;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    ArrayList<MedData> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initializedata();

        ListView lv = (ListView)findViewById(R.id.listView);
        MedDataAdapter mda = new MedDataAdapter(this, list);
        lv.setAdapter(mda);
        lv.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent myIntent = new Intent(MainActivity.this, viewQR.class);
                myIntent.putExtra("qrstring", list.get(position).JSONstringContent());
                MainActivity.this.startActivity(myIntent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, newMedData.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void initializedata(){
        MedData md;
        BasicData bd = new BasicData();
        InsuranceData id = new InsuranceData();
        CurrentDrugData cd = new CurrentDrugData();
        EmergencyContactData ec = new EmergencyContactData();
        bd.setFname("Brutus");
        bd.setLname("Buckeye");
        md = new MedData(bd,id,cd,ec);
        try{
            InputStream inputStream = getApplicationContext().openFileInput("basicData");
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while((line =r.readLine()) !=null){
                total.append(line);
            }
            inputStream.close();
            JSONObject joBasic = new JSONObject(total.toString());

            InputStream inputStreamInsurance = getApplicationContext().openFileInput("insuranceData");
            BufferedReader rInsurance = new BufferedReader(new InputStreamReader(inputStreamInsurance));
            StringBuilder totalInsurance = new StringBuilder();
            String lineInsurance;
            while((lineInsurance =rInsurance.readLine()) !=null){
                totalInsurance.append(lineInsurance);
            }
            inputStreamInsurance.close();
            JSONObject joInsurance = new JSONObject(totalInsurance.toString());

            InputStream inputStreamCM = getApplicationContext().openFileInput("currentmedData");
            BufferedReader rCM = new BufferedReader(new InputStreamReader(inputStreamCM));
            StringBuilder totalCM = new StringBuilder();
            String lineCM;
            while((lineCM =rCM.readLine()) !=null){
                totalCM.append(lineCM);
            }
            inputStreamCM.close();
            JSONObject joCM = new JSONObject(totalCM.toString());

            InputStream inputStreamEC = getApplicationContext().openFileInput("emergencycontactData");
            BufferedReader rEC = new BufferedReader(new InputStreamReader(inputStreamEC));
            StringBuilder totalEC = new StringBuilder();
            String lineEC;
            while((lineEC =rEC.readLine()) !=null){
                totalEC.append(lineEC);
            }
            inputStreamEC.close();

            JSONObject joEC = new JSONObject(totalEC.toString());

            md = new MedData(new BasicData(joBasic),new InsuranceData(joInsurance),new CurrentDrugData(joCM), new EmergencyContactData(joEC));

        }catch(FileNotFoundException e){e.printStackTrace();
        }catch(JSONException e){e.printStackTrace();}
        catch(IOException e){e.printStackTrace();}




        list.add(md);


    }
}
