package com.example.tutorial;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AdaptersActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adapters);
        ArrayList<String> arrayNumbers = new ArrayList<>();
        for (int i=1; i<11;i++){
            arrayNumbers.add("Number " + i);
        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                arrayNumbers);
        Spinner spinner = findViewById(R.id.spinner);
        ListView listView = findViewById(R.id.listView);
        GridView gridView = findViewById(R.id.gridView);

        myAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(myAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show());
        gridView.setAdapter(myAdapter);
        gridView.setOnItemClickListener((parent, view, position, id) -> Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show());
    }
}
