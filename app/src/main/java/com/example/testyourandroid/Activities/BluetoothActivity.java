package com.example.testyourandroid.Activities;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testyourandroid.R;

import pub.devrel.easypermissions.EasyPermissions;

public class BluetoothActivity extends AppCompatActivity {

    Button onButton, ofButton;

    BluetoothAdapter mBluetoothAdpater;
    int REQUEST_CODE = 1;

    public final int BLUETOOTH_REQUEST_CODE = 123;

    Intent btEnablingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);

        onButton = findViewById(R.id.button_bluetooth_on);
        ofButton = findViewById(R.id.button_bluetooth_off);


        askAboutBluetooth();


        mBluetoothAdpater = BluetoothAdapter.getDefaultAdapter();
        btEnablingIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

        onButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBluetoothAdpater == null) {
                    Toast.makeText(BluetoothActivity.this, "Bluetooth is not supported on this device", Toast.LENGTH_SHORT).show();
                } else if (mBluetoothAdpater.isEnabled()) {

                    onButton.setVisibility(View.INVISIBLE);
                    ofButton.setVisibility(View.VISIBLE);

                    Toast.makeText(BluetoothActivity.this, "Bluetooth was alredy enabled on device", Toast.LENGTH_SHORT).show();

                } else if (!mBluetoothAdpater.isEnabled()) {
                    startActivityForResult(btEnablingIntent, REQUEST_CODE);
                    //onButton.setVisibility(View.INVISIBLE);
                    //ofButton.setVisibility(View.VISIBLE);

                }

            }

        });

        ofButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mBluetoothAdpater.isEnabled()) {


                    if (ActivityCompat.checkSelfPermission(BluetoothActivity.this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        //Toast.makeText(BluetoothActivity.this, "Bluetooth permissions aren't granted", Toast.LENGTH_SHORT).show();
                        mBluetoothAdpater.disable();
                        ofButton.setVisibility(View.INVISIBLE);
                        onButton.setVisibility(View.VISIBLE);
                        Toast.makeText(BluetoothActivity.this, "Bluetooth turned off", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else
                    {
                        mBluetoothAdpater.disable();
                        ofButton.setVisibility(View.INVISIBLE);
                        onButton.setVisibility(View.VISIBLE);
                        Toast.makeText(BluetoothActivity.this, "Bluetooth turned off", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            onButton.setVisibility(View.INVISIBLE);
            ofButton.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Bluetooth was enabled", Toast.LENGTH_LONG).show();

        } else if (resultCode == RESULT_CANCELED) {

            ofButton.setVisibility(View.INVISIBLE);
            onButton.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Bluetooth enabling canceled", Toast.LENGTH_SHORT).show();
        }
    }

    private void askAboutBluetooth() {


        EasyPermissions.requestPermissions(this, "You need to give camera permission for this feature", BLUETOOTH_REQUEST_CODE, Manifest.permission.BLUETOOTH_CONNECT);


    }

}