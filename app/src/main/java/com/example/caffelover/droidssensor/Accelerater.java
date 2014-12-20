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
import android.widget.Toast;
import android.app.Activity;
import android.view.View;

import org.w3c.dom.Text;

import java.util.List;

import static android.hardware.SensorManager.AXIS_X;


public class Accelerater extends Activity implements SensorEventListener{

    SensorManager mSensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerater);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> list = mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        sensor = list.get(0);

        TextView textView = (TextView)findViewById(R.id.x_axis);
        textView.setText(sensor.getName() + " " +sensor.getType());
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
        //Toast.makeText(this, String.valueOf(SensorManager.AXIS_Z), Toast.LENGTH_LONG).show();
        TextView textViewX = (TextView)findViewById(R.id.x_axis);
        TextView textViewY = (TextView)findViewById(R.id.y_axis);
        TextView textViewZ = (TextView)findViewById(R.id.z_axis);
        textViewX.setText("x軸" + event.values[(SensorManager.AXIS_X - 1)]);
        textViewY.setText("y軸" + event.values[(SensorManager.AXIS_Y - 1)]);
        textViewZ.setText("z軸" + event.values[(SensorManager.AXIS_Z - 1)]);
    }
}
