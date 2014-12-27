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


public class Relative_humidity extends ActionBarActivity implements SensorEventListener {
    SensorManager mSensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_humidity);

        //アクションバーの設定
        ActionBar ac = getSupportActionBar();
        ac.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME, ActionBar.DISPLAY_SHOW_HOME);
        ac.setDisplayHomeAsUpEnabled(true);
        ac.setHomeButtonEnabled(true);
        ac.setDisplayShowHomeEnabled(true);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> list = mSensorManager.getSensorList(Sensor.TYPE_RELATIVE_HUMIDITY);
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
        TextView textViewName = (TextView)findViewById(R.id.relative_humidity_name);
        TextView textViewUncalibX = (TextView)findViewById(R.id.x_uncalib);
        TextView textViewUncalibY = (TextView)findViewById(R.id.y_uncalib);
        TextView textViewUncalibZ = (TextView)findViewById(R.id.z_uncalib);
        TextView textViewBiasX = (TextView)findViewById(R.id.x_bias);
        TextView textViewBiasY = (TextView)findViewById(R.id.y_bias);
        TextView textViewBiasZ = (TextView)findViewById(R.id.z_bias);
        textViewName.setText("SensorName: " + sensor.getName());
        textViewUncalibX.setText("x_uncalib：" + event.values[0]);
        textViewUncalibY.setText("y_uncalib：" + event.values[1]);
        textViewUncalibZ.setText("z_uncalib：" + event.values[2]);
        textViewBiasX.setText("x_bias：" + event.values[3]);
        textViewBiasY.setText("y_bias" + event.values[4]);
        textViewBiasZ.setText("z_bias" + event.values[5]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_relative_humidity, menu);
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
