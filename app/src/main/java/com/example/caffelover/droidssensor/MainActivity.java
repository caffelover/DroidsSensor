package com.example.caffelover.droidssensor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.sax.StartElementListener;
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
import android.widget.AdapterView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

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
            subTextPre.add(sensor.getVendor() + "社製：" + sensor.getName());
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

        //ListView用のアダプターを作成
        SimpleAdapter adapter = new SimpleAdapter(this, lvList, R.layout.list_item
                , new String[]{"main", "sub"}, new int[]{R.id.main, R.id.sub});

        lv = (ListView) findViewById(R.id.list_1);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ListView listView = (ListView) parent;
                // クリックされたアイテムを取得します

                HashMap item = (HashMap) listView.getItemAtPosition(position);
                String itemType = (String) item.get("main");
                //Toast.makeText(MainActivity.this,itemType, Toast.LENGTH_LONG).show();
                Intent intent = new Intent();
                switch (Integer.valueOf(itemType)) {
                    case Sensor.TYPE_ACCELEROMETER:
                        String accClassName = Accelerater.class.getName();
                        intent.setClassName("com.example.caffelover.droidssensor",accClassName);
                        startActivity(intent);
                        break;
                    case Sensor.TYPE_MAGNETIC_FIELD:
                        String magneticFieldClassName = MagneticField.class.getName();
                        intent.setClassName("com.example.caffelover.droidssensor",magneticFieldClassName);
                        startActivity(intent);
                        break;
                    case Sensor.TYPE_ORIENTATION:
                        String orientationClassName = Orientation.class.getName();
                        intent.setClassName("com.example.caffelover.droidssensor",orientationClassName);
                        startActivity(intent);
                        break;
                    case Sensor.TYPE_GYROSCOPE:
                        String gyroscopeClassName = Gyroscope.class.getName();
                        intent.setClassName("com.example.caffelover.droidssensor",gyroscopeClassName);
                        startActivity(intent);
                        break;
                    case Sensor.TYPE_LIGHT:
                        String lightClassName = Light.class.getName();
                        intent.setClassName("com.example.caffelover.droidssensor",lightClassName);
                        startActivity(intent);
                        break;
                    case Sensor.TYPE_PRESSURE:
                        String pressureClassName = Pressure.class.getName();
                        intent.setClassName("com.example.caffelover.droidssensor",pressureClassName);
                        startActivity(intent);
                        break;
                    case Sensor.TYPE_TEMPERATURE:
                        String temperatureClassName = Temperature.class.getName();
                        intent.setClassName("com.example.caffelover.droidssensor",temperatureClassName);
                        startActivity(intent);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, "その他", Toast.LENGTH_LONG).show();
                        break;
                }
            }

        });

    }

    public void onAccuracyChanged(Sensor arg0, int arg1) {
        //
    }

    public void onSensorChanged(SensorEvent event) {
        //
    }

}
