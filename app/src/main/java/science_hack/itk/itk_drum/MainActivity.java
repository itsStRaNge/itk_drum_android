package science_hack.itk.itk_drum;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.os.SystemClock.sleep;
import static java.lang.Math.sqrt;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {
    SoundPlayer sp;
    MediaPlayer mp;
    ArrayList<Float> database;
    ArrayList<Float> data;
    Algorithms tool;
    private boolean lock = false;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;

    private static int DATA_SIZE = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button one = (Button) this.findViewById(R.id.button_main);
        sp = new SoundPlayer(this);
        mp = MediaPlayer.create(this, R.raw.kick);
        data = new ArrayList<Float>();
        database = new ArrayList<Float>();
        tool= new Algorithms(this);
        read();
        //timeline();
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void read(){
        float a = 0,b = 0,x = 0,y = 0,z = 0;
         try {
             InputStream fin = getResources().openRawResource(R.raw.accelerometer);
             DataInputStream din = new DataInputStream(fin);

             String thisLine;
             java.io.BufferedInputStream s = new java.io.BufferedInputStream(fin);
             java.io.BufferedReader myInput = new java.io.BufferedReader(new java.io.InputStreamReader(s));
             int i = 0;
             while ((thisLine = myInput.readLine()) != null) {
                 // scan it line by line
                 java.util.StringTokenizer st = new java.util.StringTokenizer(thisLine, " ");
                 while (st.hasMoreElements()) {
                     a = Float.valueOf(st.nextToken());
                     b = Float.valueOf(st.nextToken());
                     x = Float.valueOf(st.nextToken());
                     y = Float.valueOf(st.nextToken());
                     z = Float.valueOf(st.nextToken());
                 }
                 double n = sqrt(x * x + y * y + z * z);
                 float norm = (float) n;
                 database.add(norm);
                 Log.d("", "x: " + Float.valueOf(x).toString() + " y: " + Float.valueOf(y).toString() + " z: " + Float.valueOf(z).toString() + " / " +Float.valueOf(norm).toString());
                 //initialize data array
                 if (i < 50) {
                     data.add(norm);
                     ++i;
                 }

                 Log.d("Main", String.valueOf(database.size()));
             }

             /*int i=0;
             while(din.available()>0) {
                 float a = din.readFloat();
                 float b = din.readFloat();
                 float x = din.readFloat();
                 float y = din.readFloat();
                 float z = din.readFloat();
                 Float norm = new Float(sqrt(x*x+y*y+z*z));
                 database.add(norm);
                 Log.d("","x: " + new Float(x).toString() +" y: " + new Float(y).toString() + " z: " + new Float(z).toString() + " / " +norm.toString());
                 //initialize data array
                 if(i<50){
                     data.add(norm);
                     ++i;
                 }
             }
             Log.d("Main",String.valueOf(database.size()));
             din.close();
         */
         }
         catch (Exception e){
            Log.d("Main",e.getMessage());
         }
    }

    public void buttonClick(View view) {
        Log.d("MainActivitz","Button Click");
        //sp.playDrum();
        int i = 0;
        while (i < 50) {
            data.set(i,database.get(i));
            ++i;
        }
        timeline();
    }

    public void timeline(){
        int i = DATA_SIZE;
        int last_hit = 0;
        while(i<database.size()){
            int current_index = i%DATA_SIZE;
            if(i-last_hit>Algorithms.FREEZE_CYCLES){
                if(tool.DrumHit(data,database.get(i))){
                    last_hit = i;
                }
            }
            /*if(!tool.LOCK){
                Runnable r = new RunAlg(tool,mp,data,database.get(i));
                new Thread(r).start();
            }*/
            data.set(current_index, database.get(i));
            sleep(100);
            ++i;
        }
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            Log.d("TAG", String.valueOf(x));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}