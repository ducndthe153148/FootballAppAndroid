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

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate menu
//        getMenuInflater().inflate(R.menu.bottom_nav_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.leftNav:
//                // Left nav
//                Toast.makeText(this,"Click left",Toast.LENGTH_LONG).show();
//                return true;
//            case R.id.rightNav:
//                return true;
//            case R.id.home:
//                Toast.makeText(this,"Click home", Toast.LENGTH_LONG).show();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

    public void setup(){
        btn_leftNav = findViewById(R.id.btn_leftNAv);
        BottomAppBar bar = (BottomAppBar) findViewById(R.id.bottomAppBar);
        bar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.leftNav:
                        // Left nav
                        Toast.makeText(MainActivity.this,"Click left", Toast.LENGTH_LONG).show();
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
    public void goToLeft(){
        btn_leftNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LeftNews.class);
                startActivity(intent);
            }
        });
    }
}