package com.sara.Sensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.sara.newconcepts.R;

import java.util.List;

public class sensorActivity extends AppCompatActivity implements
        SensorEventListener {

    TextView tv_sensor;
    SensorManager sensorManager;
    Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        tv_sensor = (TextView) findViewById(R.id.tv_sensor);

        // GetAllSensors();
        UseSensor();
    }

    private void UseSensor() {

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (sensor != null) {
            tv_sensor.setText("exist ; " + sensor.getName()
                    + " - Vendor : " + sensor.getVendor() +
                    " version : " + sensor.getVersion());
        } else {
            tv_sensor.setText("not found !!!");
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // to use SensorEventListener
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void Steps() {

        // SensorManager, Sensor , SensorEventListener
        /*
         1- create SensorManager obj by getSystemService
         2- create Sensor obj = sensorManager.getDefaultSensor(sensor type)
         3- if sensor not found return null else return raw data of sensor
         4- user SensorEventListener to listen any change in sensor or accuracy change
            by using sensorManager.registerListener(listener,sensor,period)
         5- be sure to unregister sensor onPause cycle to save battery

        --------------------------------------
         there is 3 types of Sensors
          1- Motion Sensor : like gravity , gyroscope
          2- Environment Sensor : like Temperature and Light
          3- Position Sensor : like orientation

        */
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

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
