package com.example.testyourandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.testyourandroid.ClickInterface.Onclick_Interface;
import com.example.testyourandroid.NotificationBarHandler.NotificationBar;
import com.example.testyourandroid.R;
import com.example.testyourandroid.adapter.RecAdapter;
import com.example.testyourandroid.models.MyModel;

import java.util.ArrayList;

import eightbitlab.com.blurview.BlurView;

public class HomePageActivity extends AppCompatActivity implements Onclick_Interface {

    BlurView mBlur;

    RecyclerView mRec;

    ArrayList<MyModel> arrayContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        NotificationBar.blackStatusBar(HomePageActivity.this, R.color.tect_color);

        mRec = findViewById(R.id.RecView);

        GridLayoutManager mGrid = new GridLayoutManager(this, 2);
        mRec.setLayoutManager(mGrid);


        arrayContacts.add(new MyModel("Biometrics", "First Description will go here", R.drawable.finger_print));
        arrayContacts.add(new MyModel("Speaker", "First Description will go here", R.drawable.speaker));
        arrayContacts.add(new MyModel("Flash", "First Description will go here", R.drawable.flash));
        arrayContacts.add(new MyModel("Compass", "First Description will go here", R.drawable.compass));
        arrayContacts.add(new MyModel("Vibrate", "First Description will go here", R.drawable.vibrate));
        //arrayContacts.add(new MyModel("Camera front", "First Description will go here", R.drawable.camera_back));
        arrayContacts.add(new MyModel("Cameras", "First Description will go here", R.drawable.camera_front));
        arrayContacts.add(new MyModel("Device info", "First Description will go here", R.drawable.device_information));
        arrayContacts.add(new MyModel("Gyroscope", "First Description will go here", R.drawable.gyro));
        arrayContacts.add(new MyModel("Secret", "First Description will go here", R.drawable.secret));
        arrayContacts.add(new MyModel("Gps", "First Description will go here", R.drawable.gps));
        arrayContacts.add(new MyModel("Bluetooth", "First Description will go here", R.drawable.bluetooth));
        arrayContacts.add(new MyModel("Mic", "First Description will go here", R.drawable.mic));
        //arrayContacts.add(new MyModel("Ear-piece", "First Description will go here", R.drawable.ear_piece));


        RecAdapter myAdapter = new RecAdapter(this, arrayContacts, this);
        mRec.setAdapter(myAdapter);

    }


    @Override
    public void ItemCLicked(int position, MyModel MyModel) {

        switch (position) {

            case 0:
                Intent intent0 = new Intent(HomePageActivity.this, BiometricsActivity.class);
                startActivity(intent0);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case 1:
                Intent intent1 = new Intent(HomePageActivity.this, SpeakerActivity.class);
                startActivity(intent1);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case 2:
                Intent intent2 = new Intent(HomePageActivity.this, FlashActivity.class);
                startActivity(intent2);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case 3:
                Intent intent3 = new Intent(HomePageActivity.this, CompassActivity.class);
                startActivity(intent3);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case 4:
                Intent intent4 = new Intent(HomePageActivity.this, VibrateActivity.class);
                startActivity(intent4);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;


            case 5:
                Intent intent6 = new Intent(HomePageActivity.this, CamerasActivity.class);
                startActivity(intent6);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case 6:
                Intent intent7 = new Intent(HomePageActivity.this, DeviceInfoActivity.class);
                startActivity(intent7);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case 7:
                Intent intent8 = new Intent(HomePageActivity.this, GyroScopeActivity.class);
                startActivity(intent8);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case 8:
                Intent intent9 = new Intent(HomePageActivity.this, SecretActivity.class);
                startActivity(intent9);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case 9:
                Intent intent10 = new Intent(HomePageActivity.this, GpsActivity.class);
                startActivity(intent10);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case 10:
                Intent intent11 = new Intent(HomePageActivity.this, BluetoothActivity.class);
                startActivity(intent11);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;

            case 11:
                Intent intent12 = new Intent(HomePageActivity.this, MicActivity.class);
                startActivity(intent12);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                break;





            //Toast.makeText(this, "The Values are " + MyModel.getTitle() +" "+ (position+1), Toast.LENGTH_SHORT).show();
        }
    }

}