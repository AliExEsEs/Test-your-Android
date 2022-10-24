package com.example.testyourandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testyourandroid.NotificationBarHandler.NotificationBar;
import com.example.testyourandroid.R;

public class GyroScopeActivity extends AppCompatActivity implements SensorEventListener {

    TextView GyroResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        NotificationBar.blackStatusBar(this, R.color.tect_color);

        GyroResults = findViewById(R.id.Sensor_Values);

        SensorManager sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        if (sensorManager != null)
        {
            Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

            if(accelerometerSensor !=null)
            {
                sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
            }

            else
            {
                Toast.makeText(this, "Can't access the device sensor", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "No Accelerometer sensor in device", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {

            GyroResults.setText("X: " +event.values[0] + "\nY: " + event.values[1] + "\nZ: " + event.values[2]);

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}