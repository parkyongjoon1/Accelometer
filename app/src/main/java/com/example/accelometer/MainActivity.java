package com.example.accelometer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView tvgXaxis, tvgYaxis, tvgZaxis;
    private SensorManager sensorManager;
    private Sensor accelerSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvgXaxis = findViewById(R.id.tvgXaxis);
        tvgYaxis = findViewById(R.id.tvgYaxis);
        tvgZaxis = findViewById(R.id.tvgZaxis);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == accelerSensor) {
            //include gravity
            float gAccX = event.values[0] * 25.5f;
            float gAccY = event.values[1] * 25.5f;
            float gAccZ = event.values[2];

            tvgXaxis.setText("X 축 : " + String.format("%.2f", gAccX));
            tvgYaxis.setText("Y 축 : " + String.format("%.2f", gAccY));
            tvgZaxis.setText("Z axis : " + String.format("%.2f", gAccZ));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}