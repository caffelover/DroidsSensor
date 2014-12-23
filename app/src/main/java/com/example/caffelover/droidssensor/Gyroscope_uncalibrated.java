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
import android.widget.TextView;

import java.util.List;


public class Gyroscope_uncalibrated extends ActionBarActivity implements SensorEventListener {
    SensorManager mSensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope_uncalibrated);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> list = mSensorManager.getSensorList(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);
        sensor = list.get(0);
    }

    @Override
    public void onResume(){
        super.onResume();
        if(sensor != null){
            mSensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_UI);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gyroscope_uncalibrated, menu);
        return true;
    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    public void onSensorChanged(SensorEvent event) {
        TextView textViewName = (TextView) findViewById(R.id.gyroscope_uncalibrated_name);
        TextView textViewX = (TextView) findViewById(R.id.x_axis);
        TextView textViewY = (TextView) findViewById(R.id.y_axis);
        TextView textViewZ = (TextView) findViewById(R.id.z_axis);
        TextView textViewX_est = (TextView) findViewById(R.id.x_axis_estimated);
        TextView textViewY_est = (TextView) findViewById(R.id.y_axis_estimated);
        TextView textViewZ_est = (TextView) findViewById(R.id.z_axis_estimated);
        textViewName.setText("センサー名：" + sensor.getName());
        textViewX.setText("angular speed around the X axis(rad/s)：" + event.values[0]);
        textViewY.setText("angular speed around the Y axis(rad/s)：" + event.values[1]);
        textViewZ.setText("angular speed around the Z axis(rad/s)：" + event.values[2]);
        textViewX_est.setText("estimated drift around X axis(rad/s)：" + event.values[3]);
        textViewY_est.setText("estimated drift around Y axis(rad/s)：" + event.values[4]);
        textViewZ_est.setText("estimated drift around Z axis(rad/s)：" + event.values[5]);
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
}
