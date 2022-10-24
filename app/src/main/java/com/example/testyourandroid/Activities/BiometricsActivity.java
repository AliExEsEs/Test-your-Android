package com.example.testyourandroid.Activities;

import static androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG;
import static androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.testyourandroid.NotificationBarHandler.NotificationBar;
import com.example.testyourandroid.R;

import java.util.concurrent.Executor;

public class BiometricsActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 10101;
    Button BiometricButton;

    private Executor executor;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biometrics);

        NotificationBar.blackStatusBar(this, R.color.tect_color);

        BiometricButton = findViewById(R.id.button_biometric);

        BiometricButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BiometricManager biometricManager = BiometricManager.from(BiometricsActivity.this);
                switch (biometricManager.canAuthenticate(BIOMETRIC_STRONG | DEVICE_CREDENTIAL)) {
                    case BiometricManager.BIOMETRIC_SUCCESS:
                        Toast.makeText(BiometricsActivity.this, "MyText", Toast.LENGTH_SHORT).show();
                        //Log.d("MY_APP_TAG", "App can authenticate using biometrics.");
                        break;
                    case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                        Toast.makeText(BiometricsActivity.this, "No Hardware", Toast.LENGTH_SHORT).show();
                        // Log.e("MY_APP_TAG", "No biometric features available on this device.");
                        break;
                    case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                        Toast.makeText(BiometricsActivity.this, "Biometric Sensor Unavailable", Toast.LENGTH_SHORT).show();
                        //Log.e("MY_APP_TAG", "Biometric features are currently unavailable.");
                        break;
                    case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                        Toast.makeText(BiometricsActivity.this, "No Biometric record, please set up finger-print", Toast.LENGTH_SHORT).show();
                        // Prompts the user to create credentials that your app accepts.
                        final Intent enrollIntent = new Intent(Settings.ACTION_BIOMETRIC_ENROLL);
                        enrollIntent.putExtra(Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                                BIOMETRIC_STRONG | DEVICE_CREDENTIAL);
                        startActivityForResult(enrollIntent, REQUEST_CODE);
                        break;
                }

            }
        });


        executor = ContextCompat.getMainExecutor(this);
        biometricPrompt = new BiometricPrompt(BiometricsActivity.this,
                executor, new BiometricPrompt.AuthenticationCallback() {

            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {

                super.onAuthenticationError(errorCode, errString);

                Toast.makeText(getApplicationContext(),"Authentication error: " + errString, Toast.LENGTH_SHORT).show();

                Intent HomeIntent = new Intent(BiometricsActivity.this, HomePageActivity.class);
                startActivity(HomeIntent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                Toast.makeText(getApplicationContext(), "Authentication succeeded!", Toast.LENGTH_SHORT).show();

                Intent HomeIntent = new Intent(BiometricsActivity.this, HomePageActivity.class);
                startActivity(HomeIntent);
                finish();
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getApplicationContext(), "Authentication failed", Toast.LENGTH_SHORT).show();

            }
        });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login for my app")
                .setSubtitle("Log in using your biometric credential")
                .setNegativeButtonText("Use account password")
                .build();

        // Prompt appears when user clicks "Log in".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
        BiometricButton.setOnClickListener(view -> {
            biometricPrompt.authenticate(promptInfo);
        });
    }


}