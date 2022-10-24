package com.example.testyourandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.testyourandroid.NotificationBarHandler.NotificationBar;
import com.example.testyourandroid.R;

import eightbitlab.com.blurview.BlurView;

public class SplashActivity extends AppCompatActivity {

    Animation logo_anim, name_anim, desc_anim;
    ImageView Android, bandage;
    TextView name, desc;

    BlurView mBlur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        NotificationBar.blackStatusBar(SplashActivity.this, R.color.tect_color);

        Android = findViewById(R.id.imageView);
        bandage = findViewById(R.id.imageView2);
        name = findViewById(R.id.android_health);
        desc = findViewById(R.id.splash_desc);


        //StartBlur();

        name_anim = AnimationUtils.loadAnimation(this, R.anim.name_anim);
        logo_anim = AnimationUtils.loadAnimation(this, R.anim.logo_anims);
        desc_anim = AnimationUtils.loadAnimation(this, R.anim.desc_logo);

        desc.startAnimation(desc_anim);
        name.startAnimation(name_anim);
        Android.startAnimation(logo_anim);
        bandage.startAnimation(logo_anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent m = new Intent(SplashActivity.this, HomePageActivity.class);
                startActivity(m);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();

            }
        }, 2100);
    }

   /* private void StartBlur() {

        float radius = 10f;

        View decorView = getWindow().getDecorView();
        // ViewGroup you want to start blur from. Choose root as close to BlurView in hierarchy as possible.
        ViewGroup rootView = (ViewGroup) decorView.findViewById(android.R.id.content);

        // Optional:
        // Set drawable to draw in the beginning of each blurred frame.
        // Can be used in case your layout has a lot of transparent space and your content
        // gets a too low alpha value after blur is applied.
        Drawable windowBackground = decorView.getBackground();

        mBlur.setupWith(rootView, new RenderScriptBlur(this)) // or RenderEffectBlur
                .setFrameClearDrawable(windowBackground) // Optional
                .setBlurRadius(radius);
        */
    }
