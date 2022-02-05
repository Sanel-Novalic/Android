package com.example.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SendMessageActivity extends AppCompatActivity {
    static final String EXTRA_MESSAGE = "com.example.tutorial.MESSAGE";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_message);
        Button send = findViewById(R.id.sendMessage);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = ((EditText) findViewById(R.id.content)).getText().toString();

                Intent i = new Intent(getApplicationContext(), ReceiveMessageActivity.class);
                i.putExtra(EXTRA_MESSAGE, message);
                startActivity(i);
            }
        });
    }
}
