package com.example.testyourandroid.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.testyourandroid.NotificationBarHandler.NotificationBar;
import com.example.testyourandroid.R;

import pub.devrel.easypermissions.EasyPermissions;

public class CamerasActivity extends AppCompatActivity {

    private static final int CAMERA_REQUEST_CODE = 100;
    Button CameraButton;
    ImageView DisplayImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_back);

        NotificationBar.blackStatusBar(this, R.color.tect_color);

        CameraButton = findViewById(R.id.button_camera_back);
        DisplayImage = findViewById(R.id.Display_Image_Back);

        askAboutCamera();

        CameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OpenCamera();

            }
        });



    }

    private void OpenCamera() {

        Intent open_camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(open_camera, 300);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK)

        {
            Bitmap load_pic = (Bitmap) data.getExtras().get("data");
            Glide.with(this)
                    .asBitmap()
                    .load(load_pic)
                    .into(DisplayImage);
            DisplayImage.setVisibility(View.VISIBLE);
        }

        else
        {
            Toast.makeText(this, "No Image captured", Toast.LENGTH_SHORT).show();
        }


    }
        //Glide.with(CameraBack.this).asBitmap().load(load_pic).into(DisplayImage);
        //DisplayImage.setImageBitmap(load_pic);



    private void askAboutCamera(){

        EasyPermissions.requestPermissions(this,"You need to give camera permission for this feature", CAMERA_REQUEST_CODE, Manifest.permission.CAMERA );
    }

}