package com.example.testyourandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;

import com.example.testyourandroid.NotificationBarHandler.NotificationBar;
import com.example.testyourandroid.R;

public class VibrateActivity extends AppCompatActivity {

    Button VibrateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibrate);

        NotificationBar.blackStatusBar(this, R.color.tect_color);

        VibrateButton = findViewById(R.id.button_vibrate);

        VibrateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VibrateDevice();

            }
        });
    }

    private void VibrateDevice() {

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
// Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           // v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));

            long[] pattern = {0, 240, 200, 240};
            v.vibrate(pattern, -1);

        } else {
            //deprecated in API 26
           // v.vibrate(500);
            long[] pattern = {0, 100, 0, 100};
            v.vibrate(pattern, -1);
        }

    }
}