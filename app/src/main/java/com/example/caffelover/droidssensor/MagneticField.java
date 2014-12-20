package com.example.caffelover.droidssensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import java.util.List;


public class MagneticField extends Activity implements SensorEventListener {
    SensorManager mSensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magnetic_field);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> list = mSensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
        sensor = list.get(0);
        TextView textViewZ = (TextView)findViewById(R.id.z_tesla);
    }

    @Override
    public void onResume(){
        super.onResume();
        if(sensor != null){
            mSensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI);
        }
    }

    public void onAccuracyChanged(Sensor arg0,int arg1){}

    public void onSensorChanged(SensorEvent event){
        TextView textViewX = (TextView)findViewById(R.id.x_tesla);
        TextView textViewY = (TextView)findViewById(R.id.y_tesla);
        TextView textViewZ = (TextView)findViewById(R.id.z_tesla);
        textViewX.setText("x軸" + event.values[0]);
        textViewY.setText("y軸" + event.values[1]);
        textViewZ.setText("z軸" + event.values.length);
    }
    public void onClick(View v){
        //Magnetic終了
        finish();
    }

}

