package com.example.testyourandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.testyourandroid.NotificationBarHandler.NotificationBar;
import com.example.testyourandroid.R;

public class FlashActivity extends AppCompatActivity {
    
    Button mButton, mStopButton;
    
    private Camera mCamera;
    private Camera.Parameters parameters;
    private CameraManager camManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);

        NotificationBar.blackStatusBar(this, R.color.tect_color);
        
        mButton = findViewById(R.id.button_flash);
        
        mStopButton = findViewById(R.id.button_flash_stop);
        
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                
                TurnOffFlash();
                mButton.setVisibility(View.VISIBLE);
                mStopButton.setVisibility(View.INVISIBLE);

            }
        });
        
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                TurnOnFlash();
                mButton.setVisibility(View.INVISIBLE);
                mStopButton.setVisibility(View.VISIBLE);
                
            }
        });
        
    }

    private void TurnOffFlash() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                String cameraId;
                camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                if (camManager != null) {
                    cameraId = camManager.getCameraIdList()[0]; // Usually front camera is at 0 position.
                    camManager.setTorchMode(cameraId, false);
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        } else {
            mCamera = Camera.open();
            parameters = mCamera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            mCamera.setParameters(parameters);
            mCamera.stopPreview();
        }
    }

    private void TurnOnFlash() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                String cameraId = null;
                if (camManager != null) {
                    cameraId = camManager.getCameraIdList()[0];
                    camManager.setTorchMode(cameraId, true);
                }
            } catch (CameraAccessException e) {

            }
        } else {
            mCamera = Camera.open();
            parameters = mCamera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            mCamera.setParameters(parameters);
            mCamera.startPreview();
        }

    }
}