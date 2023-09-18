package com.suhun.sensoraccelerometer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String tag = MainActivity.class.getSimpleName();
    private TextView xCoordinate, yCoordinate, zCoordinate;
    private SensorManager sensorManager;
    private Sensor sensor;
    private MySensorEventListener mySensorEventListener;

    private class MySensorEventListener implements SensorEventListener{

        @Override
        public void onSensorChanged(SensorEvent event) {
            String x = ""+event.values[0];
            String y = ""+event.values[1];
            String z = ""+event.values[2];
            xCoordinate.setText(x);
            yCoordinate.setText(y);
            zCoordinate.setText(z);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initSensorAccelerometer();
    }
    private void initView(){
        xCoordinate = findViewById(R.id.lid_xCoordinate);
        yCoordinate = findViewById(R.id.lid_yCoordinate);
        zCoordinate = findViewById(R.id.lid_zCoordinate);
    }

    private void initSensorAccelerometer(){
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mySensorEventListener = new MySensorEventListener();
        sensorManager.registerListener(mySensorEventListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mySensorEventListener!=null){
            sensorManager.unregisterListener(mySensorEventListener);
        }
    }
}