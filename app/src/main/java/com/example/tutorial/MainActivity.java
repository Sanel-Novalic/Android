package com.example.tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button widgets = findViewById(R.id.widgets);
        Button temperature = findViewById(R.id.temperature);
        Button lifecycle = findViewById(R.id.lifecycle);
        Button intents = findViewById(R.id.intents);
        Button adapter = findViewById(R.id.adapter);
        Button customAdapter = findViewById(R.id.customAdapter);
        Button fragments = findViewById(R.id.fragments);
        Button dialogs = findViewById(R.id.dialogs);
        widgets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, widgets.class));
            }
        });
        temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TemperatureConverterActivity.class));
            }
        });
        lifecycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LifecycleActivity.class));
            }
        });
        intents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IntentsActivity.class));
            }
        });
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AdaptersActivity.class));
            }
        });
        customAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CustomAdaptersActivity.class));
            }
        });
        fragments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FragmentsActivity.class));
            }
        });
        dialogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, DialogsActivity.class));
            }
        });
    }
}