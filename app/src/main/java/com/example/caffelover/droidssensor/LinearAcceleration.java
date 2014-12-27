package com.example.caffelover.droidssensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.List;


public class LinearAcceleration extends ActionBarActivity implements SensorEventListener {
    SensorManager mSensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_acceleration);

        //アクションバーの設定
        ActionBar ac = getSupportActionBar();
        ac.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME, ActionBar.DISPLAY_SHOW_HOME);
        ac.setDisplayHomeAsUpEnabled(true);
        ac.setHomeButtonEnabled(true);
        ac.setDisplayShowHomeEnabled(true);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> list = mSensorManager.getSensorList(Sensor.TYPE_LINEAR_ACCELERATION);
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
        TextView textViewName = (TextView)findViewById(R.id.linear_acceleration_name);
        TextView textViewAccelX = (TextView)findViewById(R.id.ms2_x);
        TextView textViewAccelY = (TextView)findViewById(R.id.ms2_y);
        TextView textViewAccelZ = (TextView)findViewById(R.id.ms2_z);
        textViewName.setText("SensorName: " + sensor.getName());
        textViewAccelX.setText("acceleration x-axis(m/s^2): " + event.values[0]);
        textViewAccelY.setText("acceleration y-axis(m/s^2): ：" + event.values[1]);
        textViewAccelZ.setText("acceleration z-axis(m/s^2): " + event.values[2]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_linear_acceleration, menu);
        return true;
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
