package com.example.tutorial;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class widgets extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widgets);
        final EditText editText = findViewById(R.id.editText);
        final CheckBox checkbox = findViewById(R.id.checkBox);
        final ToggleButton toggleButton = findViewById(R.id.toggleButton);
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    Toast.makeText(widgets.this, editText.getText(), Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(((CheckBox) v).isChecked()){
                    Toast.makeText(widgets.this, "Selected", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(widgets.this, "Not selected", Toast.LENGTH_SHORT).show();
                }
            }
        });
        final RadioButton radio_red = findViewById(R.id.radio_red);
        final RadioButton radio_blue = findViewById(R.id.radio_blue);
        radio_red.setOnClickListener(radio_listener);
        radio_blue.setOnClickListener(radio_listener);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(toggleButton.isChecked()){
                    Toast.makeText(widgets.this, "Checked", Toast.LENGTH_SHORT).show();;
                }
                else{
                    Toast.makeText(widgets.this, "Not checked", Toast.LENGTH_SHORT).show();;
                }
            }
        });
    }
    private final View.OnClickListener radio_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RadioButton rb = (RadioButton) v;
            Toast.makeText(widgets.this, rb.getText(), Toast.LENGTH_SHORT).show();
        }
    };
}
