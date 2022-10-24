package com.example.testyourandroid.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testyourandroid.NotificationBarHandler.NotificationBar;
import com.example.testyourandroid.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class SecretActivity extends AppCompatActivity {

    Button RickRoll;

    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secret);

        NotificationBar.blackStatusBar(this, R.color.tect_color);

        RickRoll = findViewById(R.id.button_rick_roll);


        RickRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                youTubePlayerView = findViewById(R.id.youtube_player_view);
                getLifecycle().addObserver(youTubePlayerView);

                youTubePlayerView.setVisibility(View.VISIBLE );

                youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        String videoId = "dQw4w9WgXcQ";
                        youTubePlayer.loadVideo(videoId, 0);
                        youTubePlayer.play();

                    }
                });

            }
        });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        youTubePlayerView.release();
    }
}