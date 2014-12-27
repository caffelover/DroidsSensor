package com.example.caffelover.droidssensor;

import android.app.ActionBar;
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


public class Orientation extends ActionBarActivity implements SensorEventListener {
    SensorManager mSensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation);

        //アクションバーの設定
        android.support.v7.app.ActionBar ac = getSupportActionBar();
        ac.setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_HOME, android.support.v7.app.ActionBar.DISPLAY_SHOW_HOME);
        ac.setDisplayHomeAsUpEnabled(true);
        ac.setHomeButtonEnabled(true);
        ac.setDisplayShowHomeEnabled(true);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> list = mSensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
        sensor = list.get(0);
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
        TextView textViewName = (TextView)findViewById(R.id.orientation_name);
        TextView textViewPitch = (TextView)findViewById(R.id.pitch);
        TextView textViewRoll = (TextView)findViewById(R.id.roll);
        textViewName.setText("SensorName: " + sensor.getName());
        textViewPitch.setText("Pitch: " + event.values[0]);
        textViewRoll.setText("Roll: " + event.values[1]);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
