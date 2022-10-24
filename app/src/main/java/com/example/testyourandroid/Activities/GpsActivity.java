package com.example.testyourandroid.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testyourandroid.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GpsActivity extends AppCompatActivity {

    TextView address, city, country, lattitude, longitude;
    Button get_location;

    private final static  int REQUEST_CODE = 100;

    FusedLocationProviderClient fusedLocationProviderClient;

    ProgressDialog mProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        mProcess = new ProgressDialog(this);

        address = findViewById(R.id.address);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);
        lattitude = findViewById(R.id.lattitude);
        longitude = findViewById(R.id.longitude);

        get_location = findViewById(R.id.button_gps);

        get_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetLastLocation();
                mProcess.setTitle("Fetching Location");
                mProcess.show();

            }
        });

    }

    private void GetLastLocation() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
        {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if (location != null)
                    {
                        Geocoder geocoder = new Geocoder(GpsActivity.this, Locale.getDefault());

                        List<Address> addresses = null;

                        try {
                            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 100);

                            lattitude.setText("Lattitude: " + addresses.get(0).getLatitude());
                            longitude.setText("Longitude: " + addresses.get(0).getLongitude());
                            address.setText("Address: " + addresses.get(0).getAddressLine(0));
                            city.setText("City: " + addresses.get(0).getLocality());
                            country.setText("Country: " + addresses.get(0).getCountryName());

                            mProcess.dismiss();

                        }
                        catch (IOException e) {
                            Toast.makeText(GpsActivity.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            mProcess.dismiss();
                        }

                    }

                }
            });
        }

        else
        {
            AskPermissions();
        }

    }

    private void AskPermissions() {

        ActivityCompat.requestPermissions(GpsActivity.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE)
        {

            if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                GetLastLocation();
            }
            else
            {
                Toast.makeText(this, "Required permission denied", Toast.LENGTH_SHORT).show();
            }

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}