package com.example.testyourandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testyourandroid.NotificationBarHandler.NotificationBar;
import com.example.testyourandroid.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DeviceInfoActivity extends AppCompatActivity {

    Button device_info;
    TextView device_info_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);

        NotificationBar.blackStatusBar(this, R.color.tect_color);

        device_info = findViewById(R.id.button_device_info);
        device_info_text = findViewById(R.id.device_info_text);



        device_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String CurrentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String CurrentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

                device_info_text.setText(

                        "Brand:  " + Build.BRAND + "\n"
                                + "Device:  " + Build.DEVICE + "\n"
                                + "Board:  " + Build.BOARD + "\n"
                                + "Display:  " + Build.DISPLAY + "\n"
                                + "Fingerprint:  " + Build.FINGERPRINT + "\n"
                                + "Hardware:  " + Build.HARDWARE + "\n"
                                + "Manufacturer:  " + Build.MANUFACTURER + "\n"
                                + "Model:  " + Build.MODEL + "\n"
                                + "User:  " + Build.USER + "\n"
                                + "Bootloader:  " + Build.BOOTLOADER + "\n"
                                + "Type:  " + Build.TYPE + "\n"
                                + "ID:  " + Build.ID + "\n"
                                + "OS:  " + "Android "+ Build.VERSION.RELEASE_OR_CODENAME + "\n"
                                + "Host:  " + Build.HOST + "\n"
                                + "Tags:  " + Build.TAGS + "\n"
                                + "Time:  " + CurrentTime + "\n"
                                + "Date:  " + CurrentDate

                );

            }
        });

    }
}