package com.example.tutorial;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class LifecycleActivity extends AppCompatActivity {
    private static final String LIFECYCLE_STATE = "LifecycleState";
    private TextView myView = null;
    @Override
    protected void onCreate(Bundle savedBundleInstance) {
        super.onCreate(savedBundleInstance);
        setContentView(R.layout.lifecycle);

        myView = findViewById(R.id.myView);
        if(savedBundleInstance == null){
            if(myView!=null){
                myView.append(getResources().getString(R.string.onCreateCallback));
            }
        }
    }
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        if(myView!=null)
            myView.append(getResources().getString(R.string.onPostCreateCallback));
    }
    @Override
    public void onStart() {
        super.onStart();

        if(myView!=null)
            myView.append(getResources().getString(R.string.onStartCallback));
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        if(myView!=null)
            savedInstanceState.putCharSequence(LIFECYCLE_STATE, myView.getText());
    }
    @Override
    public void onResume() {
        super.onResume();

        if(myView!=null)
            myView.append(getResources().getString(R.string.onResumeCallback));
    }
    @Override
    public void onPause() {
        super.onPause();

        if(myView!=null)
            myView.append(getResources().getString(R.string.onPauseCallback));
    }
    @Override
    public void onStop() {
        super.onStop();

        if(myView!=null)
            myView.append(getResources().getString(R.string.onStopCallback));
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        if(myView!=null)
            myView.append(getResources().getString(R.string.onDestroyCallback));
    }
}
