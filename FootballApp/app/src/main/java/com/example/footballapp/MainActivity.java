package com.example.footballapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomappbar.BottomAppBar;

public class MainActivity extends AppCompatActivity {
    Button btn_leftNav;
    BottomAppBar bar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        goToLeft();
    }

    public void setup(){
        bar = (BottomAppBar) findViewById(R.id.bottomAppBar);
    }
    public void goToLeft(){
        bar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.leftNav:
                        // Left nav
                        Toast.makeText(MainActivity.this,"Click left", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(MainActivity.this, LeftNews.class);
                        startActivity(intent);
                        return true;
                    case R.id.rightNav:
                        Toast.makeText(MainActivity.this,"Click right", Toast.LENGTH_LONG).show();
                        return true;
                    case R.id.home:
                        Toast.makeText(MainActivity.this,"Click home", Toast.LENGTH_LONG).show();
                        return true;
                }
                return true;
            }
        });
    }
}