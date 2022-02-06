package com.example.tutorial;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        setTheme(R.style.Theme_MaterialComponents_DayNight_DarkActionBar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_settings){
            Intent i = new Intent(getApplicationContext(), SettingsActivity.class);
            i.putExtra("settings", "Settings");
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.search),Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }
}
