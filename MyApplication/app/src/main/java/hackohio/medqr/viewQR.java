package hackohio.medqr;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.content.Intent;
import android.os.CountDownTimer;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class viewQR extends AppCompatActivity {
    qrcode qr = new qrcode();
    JSONObject content;
    ImageView iv;
    ArrayList<Bitmap> list;
    int[] cdarray = {R.drawable.ic_cd_5,R.drawable.ic_cd_4,R.drawable.ic_cd_3,R.drawable.ic_cd_2,R.drawable.ic_cd_1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_qr);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        String qrContent = intent.getStringExtra("qrstring");
        try {
             content = new JSONObject(qrContent);
        }catch(JSONException e){e.printStackTrace();}
        System.out.println(qrContent);


        iv = (ImageView)findViewById(R.id.qrView);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                q3qr(list.size() + 2, 2);
            }
        });
    }
    @Override
    protected void onStart(){
        initializeQR();
        System.out.println("QR ready!");
        super.onStart();
    }

    public void initializeQR(){
        list = new ArrayList<>();
        list.add(qr.qrBitmap(getBasic(content),900,900));
        list.add(qr.qrBitmap(getInsurance(content),900,900));
        list.add(qr.qrBitmap(getCurrentDrug(content),900,900));
        list.add(qr.qrBitmap(getEC(content),900,900));
    }

    public String getBasic(JSONObject jo){
        JSONObject temp = null;
        try{
            temp = jo.getJSONObject("BasicData");
        }  catch(JSONException e){e.printStackTrace();}
        return temp.toString();
    }
    public String getInsurance(JSONObject jo){
        JSONObject temp = null;
        try{
            temp = jo.getJSONObject("InsuranceData");
        }  catch(JSONException e){e.printStackTrace();}
        return temp.toString();
    }
    public String getCurrentDrug(JSONObject jo){
        JSONObject temp = null;
        try{
            temp = jo.getJSONObject("CurrentMedData");
        }  catch(JSONException e){e.printStackTrace();}
        return temp.toString();
    }
    public String getEC(JSONObject jo){
        JSONObject temp = null;
        try{
            temp = jo.getJSONObject("EmergencyContactData");
        }  catch(JSONException e){e.printStackTrace();}
        return temp.toString();
    }

    public void q3qr(int size, int delay2) {
        final int finalsize = size;
        final int delay3 = delay2;
        JSONObject q3qrheader = new JSONObject();
        try {
            q3qrheader.put("size", size);
            q3qrheader.put("delay", delay2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        list.add(0, qr.qrBitmap(q3qrheader.toString(), 900, 900));
        new CountDownTimer(6000, 1000) {
            int pos2 = 0;

            public void onTick(long time) {
                iv.setImageDrawable(getDrawable(cdarray[pos2]));
                pos2 += 1;
            }

            public void onFinish() {


                new CountDownTimer(delay3 * 1000 * finalsize, delay3 * 1000) {
                    int pos = 0;

                    public void onTick(long time) {
                        if (pos < list.size() + 1) { //deus vult! I have no idea why this works
                            iv.setImageBitmap(list.get(pos));
                            System.out.println(pos);
                            pos += 1;
                        }
                    }

                    public void onFinish() {
                        iv.setImageBitmap(list.get(list.size() - 1));
                    }
                }.start();
            }

        }.start();
    }}





