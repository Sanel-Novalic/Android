package com.example.tutorial;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class InternalActivity extends AppCompatActivity {
    EditText edFileName, edContent;
    Button btnSave;
    ListView listSavedFiles;
    String[] SavedFiles;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);

        edFileName = findViewById(R.id.name);
        edContent = findViewById(R.id.content);
        btnSave = findViewById(R.id.save);
        listSavedFiles = findViewById(R.id.list);
        listSavedFiles.setOnItemClickListener(listSavedFilesOnitemClickListener);

        ShowSavedFiles();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileName = edFileName.getText().toString();
                String content = edContent.getText().toString();
                FileOutputStream fos;
                try {
                    fos = openFileOutput (fileName, Context.MODE_PRIVATE);
                    fos.write(content.getBytes());
                    fos.close();
                    Toast.makeText(
                            getApplicationContext(),
                            fileName + " saved", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace(); }
                ShowSavedFiles();
            }
        });
    }

    private void ShowSavedFiles() {
        SavedFiles = getApplicationContext().fileList();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,SavedFiles);
        listSavedFiles.setAdapter(adapter);
    }
    AdapterView.OnItemClickListener listSavedFilesOnitemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String clickedFile = (String) parent.getItemAtPosition(position);
            OpenFileDialog(clickedFile);
        }
    };

    private void OpenFileDialog(String file) {
        FileInputStream fis;
        String content = "";
        try {
            fis = openFileInput(file);
            byte[] input = new byte[fis.available()];
            while(fis.read(input) != -1) {}
            content += new String(input);
        }
        catch (FileNotFoundException e){ e.printStackTrace();}
        catch (IOException e) {e.printStackTrace(); }
        Toast.makeText(getApplicationContext(),file + " : " + content, Toast.LENGTH_SHORT).show();
    }
}
