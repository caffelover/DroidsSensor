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


public class Gyroscope extends Activity implements SensorEventListener {
    SensorManager mSensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> list = mSensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);
        sensor = list.get(0);
    }

    @Override
    public void onResume(){
        super.onResume();
        if(sensor != null){
            mSensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI);
        }
    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    public void onSensorChanged(SensorEvent event) {
        TextView textViewName = (TextView) findViewById(R.id.gyroscope_name);
        TextView textViewX = (TextView) findViewById(R.id.x_axis);
        TextView textViewY = (TextView) findViewById(R.id.y_axis);
        TextView textViewZ = (TextView) findViewById(R.id.z_axis);
        textViewName.setText("センサー名：" + sensor.getName());
        textViewX.setText("角速度-X軸：" + event.values[0]);
        textViewY.setText("角速度-Y軸：" + event.values[1]);
        textViewZ.setText("角速度-Z軸：" + event.values[2]);
    }
    public void onClick(View v){
        //Orientation終了
        finish();
    }
}