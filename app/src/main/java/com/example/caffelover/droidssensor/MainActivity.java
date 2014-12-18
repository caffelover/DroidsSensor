package com.example.caffelover.droidssensor;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.List;


public class MainActivity extends Activity implements SensorEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtView = (TextView)findViewById(R.id.sensorResult);

        SensorManager mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> list = mSensorManager.getSensorList(1);
        for(Sensor sensor : list){
            if(mSensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI)){
                //txtView.append(String.valueOf(sensor.getType()));
                txtView.append("加速度センサー：\n");
                txtView.append(sensor.getName());
                txtView.append(" ");
                txtView.append(sensor.getVendor());
                txtView.append(" ");
                txtView.append(String.valueOf(sensor.getResolution()));
                txtView.append(" ");
                txtView.append(String.valueOf(sensor.getPower()));
                txtView.append("\n");

            }
        }
    }

    public void onAccuracyChanged(Sensor arg0, int arg1){
        //
    }

    public void onSensorChanged(SensorEvent event){
        //
    }

}
