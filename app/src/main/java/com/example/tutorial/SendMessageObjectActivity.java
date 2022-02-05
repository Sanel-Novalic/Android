package com.example.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SendMessageObjectActivity extends AppCompatActivity {
    static final String EXTRA_OBJ = "com.example.tutorial.OBJ";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_message_object);

        Button send = findViewById(R.id.sendMessage);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = ((EditText) findViewById(R.id.title)).getText().toString();
                String message = ((EditText) findViewById(R.id.content)).getText().toString();

                Message msg = new Message(title,message);

                Intent i = new Intent(getApplicationContext(), ReceiveMessageActivity.class);
                i.putExtra(EXTRA_OBJ, msg);
                startActivity(i);
            }
        });

    }
}
