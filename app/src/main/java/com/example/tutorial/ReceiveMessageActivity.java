package com.example.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ReceiveMessageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receive_message);
        TextView tvMessage = findViewById(R.id.tvMessage);
        Intent i = getIntent();
        if(i.hasExtra(SendMessageObjectActivity.EXTRA_OBJ)){
            Message msgExtra = (Message) i.getSerializableExtra(SendMessageObjectActivity.EXTRA_OBJ);
            tvMessage.setText(msgExtra.getTitle() + " - " + msgExtra.getContent());
        }
        else if (i.hasExtra(SendMessageActivity.EXTRA_MESSAGE)){
            tvMessage.setText(i.getStringExtra(SendMessageActivity.EXTRA_MESSAGE));
        }
    }
}
