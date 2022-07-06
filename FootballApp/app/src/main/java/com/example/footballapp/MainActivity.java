package com.example.footballapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.footballapp.fragment.NavigationDrawer;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    Button btn_leftNav;
    BottomAppBar bar;
    BottomNavigationView bottomNavigationView;
    View view;
    NavigationDrawer navigationDrawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        replaceFragment(new NavigationDrawer());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.leftNav:
                Intent intent = new Intent(MainActivity.this, LeftNews.class);
                startActivity(intent);
                return true;
            case R.id.rightNav:
                if(NavigationDrawer.isOpen()){
                    NavigationDrawer.CloseDrawer();
                } else{
                    NavigationDrawer.OpenDrawer();
                }

                return true;
            case R.id.home:
                return true;
        }
        return false;
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(NavigationDrawer.isOpen()){
            NavigationDrawer.CloseDrawer();
        } else{
            super.onBackPressed();
        }
    }
}