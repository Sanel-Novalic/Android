package com.example.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        Intent i = getIntent();
        if(i.hasExtra(CustomAdaptersActivity.EXTRA_LOGIN)){
            TextView tvLogin = findViewById(R.id.tvLogin);
            tvLogin.setText(i.getStringExtra(CustomAdaptersActivity.EXTRA_LOGIN));
        }
        if(i.hasExtra(CustomAdaptersActivity.EXTRA_NAME)){
            TextView tvUsername = findViewById(R.id.tvUsername);
            tvUsername.setText(i.getStringExtra(CustomAdaptersActivity.EXTRA_NAME));
        }
        if(i.hasExtra(CustomAdaptersActivity.EXTRA_IMAGE)){
            ImageView iv = findViewById(R.id.iv);
            iv.setImageResource(i.getIntExtra(CustomAdaptersActivity.EXTRA_IMAGE,0));
        }
    }
}
