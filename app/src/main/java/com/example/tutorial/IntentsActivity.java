package com.example.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IntentsActivity extends AppCompatActivity {
    public static final String EXTRA_NUMBER = "com.example.tutorial.IntentsActivity.Number";

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
