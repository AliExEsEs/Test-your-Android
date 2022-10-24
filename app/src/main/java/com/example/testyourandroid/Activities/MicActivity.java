package com.example.testyourandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testyourandroid.R;

import java.io.File;

import pub.devrel.easypermissions.EasyPermissions;

public class MicActivity extends AppCompatActivity {

    Button Record, Stop, Play;
    public final int MIC_REQUEST_CODE = 1002;

    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mic);

        GetPermission();

        Record = findViewById(R.id.button_mic);
        Stop = findViewById(R.id.button_mic_stop);
        Play = findViewById(R.id.button_mic_audioplay);

        mediaRecorder = new MediaRecorder();
        mediaPlayer = new MediaPlayer();

        if (!IsMicAvailable())
        {
            Toast.makeText(this, "The device doesn't have a mic", Toast.LENGTH_SHORT).show();
        }



        Record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetPermission();

                try {

                    if (mediaPlayer.isPlaying())
                    {
                        Toast.makeText(MicActivity.this, "Please with while the audio is playing", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        mediaRecorder = new MediaRecorder();
                        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                        mediaRecorder.setOutputFile(GetRecordingFilePath());
                        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                        Toast.makeText(MicActivity.this, "Recording started succesfully", Toast.LENGTH_SHORT).show();

                        Record.setVisibility(View.INVISIBLE);
                        Stop.setVisibility(View.VISIBLE);
                        Play.setVisibility(View.INVISIBLE);
                    }


                } catch (Exception e) {
                    Toast.makeText(MicActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        Stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;

                Toast.makeText(MicActivity.this, "Audio recording stopped", Toast.LENGTH_SHORT).show();

                Record.setVisibility(View.INVISIBLE);
                Stop.setVisibility(View.INVISIBLE);
                Play.setVisibility(View.VISIBLE);

            }
        });

        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(GetRecordingFilePath());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Toast.makeText(MicActivity.this, "Recording is playing", Toast.LENGTH_SHORT).show();

                    Record.setVisibility(View.VISIBLE);
                    Stop.setVisibility(View.INVISIBLE);
                    Play.setVisibility(View.INVISIBLE);

                } catch (Exception e) {
                    Toast.makeText(MicActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void GetPermission() {

        //if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
          //  ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, MIC_REQUEST_CODE);
        //}

        EasyPermissions.requestPermissions(this,"You need to give mic permission for this feature", MIC_REQUEST_CODE, Manifest.permission.RECORD_AUDIO);
    }

    private boolean IsMicAvailable() {
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
            return true;
        } else {
            return false;
        }
    }

    private String GetRecordingFilePath() {

        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File music_folder = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(music_folder, "Test your Android" + ".mp3");
        return file.getPath();
    }

}