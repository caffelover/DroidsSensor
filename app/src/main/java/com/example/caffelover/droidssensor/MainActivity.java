package com.example.caffelover.droidssensor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListActivity;
import android.support.v4.app.FragmentActivity;
import android.content.Context;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.SimpleAdapter;

//import java.util.List;


public class MainActivity extends Activity implements SensorEventListener {
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SensorManager mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> list = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        //センサーのタイプをメインに格納(後で名前に変換する)
        //String[] mainText;
        ArrayList<String> mainTextPre = new ArrayList<String>();
        ArrayList<String> subTextPre = new ArrayList<String>();
        for (Sensor sensor : list) {
            mainTextPre.add(String.valueOf(sensor.getType()));
            subTextPre.add(sensor.getName() + sensor.getVendor());
        }

        //配列に変換
        String[] mainText = (String[]) mainTextPre.toArray(new String[0]);
        String[] subText = (String[]) subTextPre.toArray(new String[0]);

        final List<Map<String, String>> lvList = new ArrayList<Map<String, String>>();
        for (int i = 0; i < mainText.length; i++) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("main", mainText[i]);
            map.put("sub", subText[i]);
            lvList.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, lvList, android.R.layout.simple_list_item_2
                , new String[]{"main", "sub"}, new int[]{android.R.id.text1, android.R.id.text2});

        lv = (ListView) findViewById(R.id.list_1);
        lv.setAdapter(adapter);

        TextView txtView = (TextView) findViewById(R.id.sensorResult);

    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {
        //
    }

    public void onSensorChanged(SensorEvent event) {
        //
    }

}
