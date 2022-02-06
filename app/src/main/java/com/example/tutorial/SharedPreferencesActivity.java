package com.example.tutorial;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SharedPreferencesActivity extends AppCompatActivity {
    private SharedPreferences Prefs;
    private SharedPreferences.Editor Editor;
    EditText name,surname,age;
    Button save,reset;
    public static final String NAME = "NAME";
    public static final String SURNAME = "SURNAME";
    public static final String AGE = "AGE";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preferences_activity);
        init();
        save = findViewById(R.id.save);
        reset = findViewById(R.id.reset);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString();
                String surnameText = surname.getText().toString();
                String ageText = age.getText().toString();
                Editor.putString(NAME,nameText).commit();
                Editor.putString(SURNAME,surnameText).commit();
                if(!ageText.equals(""))
                    Editor.putInt(AGE, Integer.parseInt(ageText)).commit();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Editor.remove(NAME).commit();
                Editor.remove(SURNAME).commit();
                Editor.remove(AGE).commit();
                readPerson();
            }
        });
    }
    private void init(){
        Prefs = getSharedPreferences("PEOPLE_PREFENCES", Context.MODE_PRIVATE);
        Editor = Prefs.edit();
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        age = findViewById(R.id.age);
        age.setInputType(InputType.TYPE_CLASS_NUMBER);
        readPerson();
    }

    private void readPerson() {
        name.setText(Prefs.getString(NAME,null));
        surname.setText(Prefs.getString(SURNAME,null));
        String agePref = "" + Prefs.getInt(AGE, 0);
        age.setText((agePref.equals("0")) ? null : agePref);
    }
}
