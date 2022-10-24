package com.example.testyourandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testyourandroid.NotificationBarHandler.NotificationBar;
import com.example.testyourandroid.R;

public class SpeakerActivity extends AppCompatActivity {

    Button mButton, mStopButton;
    int count = 1;

    Ringtone r;
    Uri notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speaker);

        NotificationBar.blackStatusBar(this, R.color.tect_color);

        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        r = RingtoneManager.getRingtone(getApplicationContext(), notification);

        mButton = findViewById(R.id.button_speaker);
        mStopButton = findViewById(R.id.button_speaker_stop);


        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StopRingtone();
                mStopButton.setVisibility(View.INVISIBLE);
                mButton.setVisibility(View.VISIBLE);

            }
        });
        
        mButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                    PlayRingtone();
                    mButton.setVisibility(View.INVISIBLE);
                    mStopButton.setVisibility(View.VISIBLE);

                Toast.makeText(SpeakerActivity.this, "Don't forget to check your Notification Volumes", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void StopRingtone() {

        r.stop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        r.stop();

        Intent i = new Intent(SpeakerActivity.this, HomePageActivity.class);
        startActivity(i);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }

    private void PlayRingtone() {


        r.play();
        r.setLooping(true);


    }
}