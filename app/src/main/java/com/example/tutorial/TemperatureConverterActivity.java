package com.example.tutorial;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TemperatureConverterActivity extends AppCompatActivity {
    private EditText text;
    private Button calculate;
    @Override
    protected void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.temperature_converter);
        text = findViewById(R.id.editTemperature);
        calculate = findViewById(R.id.calculate);
        text.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                    return false;
                if(keyCode == KeyEvent.KEYCODE_ENTER){
                    doConversion();
                    return true;
                }
                else
                    return false;
            }
        });
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doConversion();
            }
        });
    }
    private void doConversion() {
        RadioButton celcius = findViewById(R.id.celciusButton);
        RadioButton fahrenheit = findViewById(R.id.fahrenheitButton);

        if(text.getText().length() == 0){
            Toast.makeText(this,"Please a valid", Toast.LENGTH_SHORT).show();
            return;
        }
        float inputValue = Float.parseFloat(text.getText().toString());
        if(celcius.isChecked()){
            text.setText(String.valueOf(convertC_F(inputValue)));
            celcius.setChecked(false);
            fahrenheit.setChecked(true);
        }
        else{
            text.setText(String.valueOf(convertF_C(inputValue)));
            celcius.setChecked(true);
            fahrenheit.setChecked(false);
        }
    }

    private float convertC_F(float celcius) { return ((celcius* 9 / 5) + 32); }
    private float convertF_C(float fahrenheit) { return ((fahrenheit - 32) * 5 / 9); }
}

