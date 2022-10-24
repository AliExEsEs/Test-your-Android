package com.example.testyourandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testyourandroid.NotificationBarHandler.NotificationBar;
import com.example.testyourandroid.R;

public class CompassActivity extends AppCompatActivity implements SensorEventListener {

    ImageView CompassPointer;
    Button FindButton;
    TextView DegreeText;

    SensorManager mySensorManager;
    Sensor accelerometerSensor, magnetometerSensor;

    float[] lastAccelerometer = new float[3];
    float[] lastMagnetometer = new float[3];
    float[] rotationMtrix = new float[9];
    float[] orientation = new float[3];

    boolean isLastAccelerometerCopied = false;
    boolean isLastMagnetometerCopied = false;

    long LastUpdatedTime = 0;
    float currentDegree = 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        NotificationBar.blackStatusBar(this, R.color.tect_color);

        CompassPointer = findViewById(R.id.imageView3);
        FindButton = findViewById(R.id.button_compass);
        DegreeText = findViewById(R.id.compass_degreee_text);

        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometerSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnetometerSensor = mySensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);


    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor == accelerometerSensor) {

            System.arraycopy(event.values, 0, lastAccelerometer, 0, event.values.length);
            isLastAccelerometerCopied = true;

        } else if (event.sensor == magnetometerSensor) {

            System.arraycopy(event.values, 0, lastMagnetometer, 0, event.values.length);
            isLastMagnetometerCopied = true;

        }

        if (isLastAccelerometerCopied && isLastMagnetometerCopied && System.currentTimeMillis() - LastUpdatedTime > 250) {

            SensorManager.getRotationMatrix(rotationMtrix, null, lastAccelerometer, lastMagnetometer);
            SensorManager.getOrientation(rotationMtrix, orientation);

            float azimuthInRadians = orientation[0];

            float azimuthInDegree = (float) Math.toDegrees(azimuthInRadians);

            RotateAnimation rotateAnimation = new RotateAnimation(currentDegree, -azimuthInDegree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

            rotateAnimation.setDuration(250);
            rotateAnimation.setFillAfter(true);

            CompassPointer.startAnimation(rotateAnimation);

            currentDegree = -azimuthInDegree;
            LastUpdatedTime = System.currentTimeMillis();

            int x = (int) azimuthInDegree;
            DegreeText.setText(x + "°");

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        mySensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mySensorManager.registerListener(this, magnetometerSensor, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();

        mySensorManager.unregisterListener(this, accelerometerSensor);
        mySensorManager.unregisterListener(this, magnetometerSensor);

    }

}

