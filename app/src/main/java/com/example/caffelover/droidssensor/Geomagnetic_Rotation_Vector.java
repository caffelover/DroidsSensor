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


public class Geomagnetic_Rotation_Vector extends ActionBarActivity implements SensorEventListener {
    SensorManager mSensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geomagnetic__rotation__vector);

        //アクションバーの設定
        ActionBar ac = getSupportActionBar();
        ac.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME, ActionBar.DISPLAY_SHOW_HOME);
        ac.setDisplayHomeAsUpEnabled(true);
        ac.setHomeButtonEnabled(true);
        ac.setDisplayShowHomeEnabled(true);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> list = mSensorManager.getSensorList(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
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
        TextView textViewName = (TextView)findViewById(R.id.geomagnetic_rotation_vector_name);
        TextView textViewRotVectorX = (TextView)findViewById(R.id.x_axis);
        TextView textViewRotVectorY = (TextView)findViewById(R.id.y_axis);
        TextView textViewRotVectorZ = (TextView)findViewById(R.id.z_axis);
        TextView textViewCos = (TextView)findViewById(R.id.cos);
        TextView textViewAccuracy = (TextView)findViewById(R.id.accuracy);
        textViewName.setText("SensorName: " + sensor.getName());
        textViewRotVectorX.setText("x*sin(θ/2): " + event.values[0]);
        textViewRotVectorY.setText("y*sin(θ/2): " + event.values[1]);
        textViewRotVectorZ.setText("z*sin(θ/2): " + event.values[2]);
        textViewCos.setText("cos(θ/2)：" + event.values[3]);
        textViewAccuracy.setText("estimated heading Accuracy (in radians): " + event.values[4]);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_geomagnetic__rotation__vector, menu);
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
