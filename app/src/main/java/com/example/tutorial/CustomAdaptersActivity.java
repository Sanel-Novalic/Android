package com.example.tutorial;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdaptersActivity extends AppCompatActivity {
    public static final String EXTRA_LOGIN = "login";
    public static final String EXTRA_NAME = "username";
    public static final String EXTRA_IMAGE = "image";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_adapters_activity);

        ArrayList<User> usersArray = new ArrayList<>();

        for(int i=1;i<11;i++){
            usersArray.add(new User("User " + i, "User " + i, R.drawable.ic_launcher_foreground));
        }

        UserAdapter userAdapter = new UserAdapter(usersArray);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userAdapter);

        userAdapter.setItemClickListener(new UserAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                User userClicked = usersArray.get(position);
                
                Intent i = new Intent(getApplicationContext(), UserActivity.class);
                i.putExtra(EXTRA_LOGIN, userClicked.getUserLogin());
                i.putExtra(EXTRA_NAME, userClicked.getUserName());
                i.putExtra(EXTRA_IMAGE, userClicked.getImageResourceId());
                startActivity(i);
            }
        });
    }
}
