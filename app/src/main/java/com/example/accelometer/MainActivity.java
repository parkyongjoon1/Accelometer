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
    TextView tvX, tvY, tvZ;
    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvX = findViewById(R.id.tvgXaxis);
        tvY = findViewById(R.id.tvgYaxis);
        tvZ = findViewById(R.id.tvgZaxis);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == sensor) {
            //include gravity
            float gAccX = event.values[0] * 25.5f;
            float gAccY = event.values[1] * 25.5f;
            float gAccZ = event.values[2];

            tvX.setText("X 축 : " + String.format("%.2f", gAccX));
            tvY.setText("Y 축 : " + String.format("%.2f", gAccY));
            tvZ.setText("Z axis : " + String.format("%.2f", gAccZ));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}