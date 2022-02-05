package com.example.tutorial;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class IntentsActivity extends AppCompatActivity {
    public static final String EXTRA_NUMBER = "com.example.tutorial.IntentsActivity.Number";
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intents);
        Button sendMessage = findViewById(R.id.sendMessage);
        Button sendMessageObject = findViewById(R.id.sendMessageObject);
        Button sendOutput1 = findViewById(R.id.sendOutput1);
        Button sendOutput2 = findViewById(R.id.sendOutput2);
        Button makePhoneDial = findViewById(R.id.makePhoneDial);
        Button makePhoneCall = findViewById(R.id.makePhoneCall);
        Button openUrlWeb = findViewById(R.id.openUrlWeb);
        Button openMap = findViewById(R.id.openMap);
        sendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntentsActivity.this, SendMessageActivity.class));
            }
        });
        sendMessageObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntentsActivity.this, SendMessageObjectActivity.class));
            }
        });
        sendOutput1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GetNumberActivity.class);
                outputResult1.launch(i);
            }
        });
        sendOutput2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), GetNumberActivity.class);
                outputResult2.launch(i);
            }
        });
        makePhoneDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri number = Uri.parse("tel:" + getResources().getString(R.string.dialNumber));
                Intent i = new Intent(Intent.ACTION_DIAL, number);
                startActivity(i);
            }
        });
        makePhoneCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPhone();
            }
        });
        openUrlWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse(getResources().getString(R.string.androidWeb));
                Intent i = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(i);
            }
        });
        openMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri geo = Uri.parse("geo:43.7009358,7.2683912");
                Intent i = new Intent(Intent.ACTION_VIEW, geo);
                startActivity(i);
            }
        });
    }

    private void callPhone() {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        }
        else{
            Uri number = Uri.parse("tel:" + getResources().getString(R.string.dialNumber));
            Intent i = new Intent(Intent.ACTION_CALL, number);
            startActivity(i);
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callPhone();
            }
        }
    }
    ActivityResultLauncher<Intent> outputResult1 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    int number = result.getData().getIntExtra(EXTRA_NUMBER, 0);
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.send) + "RQ1: " + number, Toast.LENGTH_SHORT).show();
                }
            });
    ActivityResultLauncher<Intent> outputResult2 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    int number = result.getData().getIntExtra(EXTRA_NUMBER, 0);
                    Toast.makeText(getBaseContext(), getResources().getString(R.string.send) + "RQ2: " + number, Toast.LENGTH_SHORT).show();
                }
            });
}
