package com.sara.Sensors;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sara.newconcepts.R;

import java.util.List;

public class sensorActivity extends AppCompatActivity {

    TextView tv_sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        tv_sensor = (TextView) findViewById(R.id.tv_sensor);

        // GetAllSensors();
        UseSensor();
    }

    private void Steps()
    {
        // 1- create SensorManager obj by getSystemService
        // 2- create Sensor obj = sensorManager.getDefaultSensor(sensor type)
        // 3- if sensor not found return null else return raw data of sensor
    }

    private void UseSensor() {

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor != null) {
            tv_sensor.setText("exist ; " + sensor.getName());
        } else {
            tv_sensor.setText("not found !!!");
        }


    }

    private void GetAllSensors() {

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        // to get all Sensor.TYPE_ALL
        List<Sensor> lst_sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        String txt = "";
        for (Sensor sensor : lst_sensors) {
            txt += sensor.getName()
                    + "\n";
        }
        tv_sensor.setText(txt);
    }
}
