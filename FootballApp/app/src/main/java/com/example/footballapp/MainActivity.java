package com.example.footballapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballapp.adapter.MatchAdapter;
import com.example.footballapp.api.ApiClient;
import com.example.footballapp.api.ApiInterface;
import com.example.footballapp.fragment.NavigationDrawer;
import com.example.footballapp.models.ArticlesItem;
import com.example.footballapp.models.MatchItem;
import com.example.footballapp.models.MatchResponses;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private List<MatchItem> matchs = new ArrayList<>();
    private MatchAdapter matchAdapter;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    BottomAppBar bar;
    BottomNavigationView bottomNavigationView;
    View view;
    NavigationDrawer navigationDrawer;
    TextView leagueName, fr_teamName, second_teamName, time_value2, detail_button;
    ImageView fr_teamImage, second_teamImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preCreate();
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        replaceFragment(new NavigationDrawer());
        LoadAllMatchJSon();
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
                // Don't need to use this
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

    private void preCreate(){
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        leagueName = findViewById(R.id.leagueName);
        fr_teamName = findViewById(R.id.fr_teamName);
        second_teamName = findViewById(R.id.second_teamName);
        time_value2 = findViewById(R.id.time_value2);
        detail_button = findViewById(R.id.detail_button);
        fr_teamImage = findViewById(R.id.fr_teamImage);
        second_teamImage = findViewById(R.id.second_teamImage);

        recyclerView = findViewById(R.id.recViewMatch);
        layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
    }

    public void LoadFirstMatchJSon(){
        ApiInterface apiInterface = ApiClient.getApiMatch().create(ApiInterface.class);
        Call<MatchItem> call;
        call = apiInterface.getLatestMatch();
        call.enqueue(new Callback<MatchItem>() {
            @Override
            public void onResponse(Call<MatchItem> call, Response<MatchItem> response) {

            }

            @Override
            public void onFailure(Call<MatchItem> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void LoadAllMatchJSon(){
        ApiInterface apiInterface = ApiClient.getApiMatch().create(ApiInterface.class);
        Call<MatchResponses> call;
        call = apiInterface.getFullMatch();
        call.enqueue(new Callback<MatchResponses>() {
            @Override
            public void onResponse(Call<MatchResponses> call, Response<MatchResponses> response) {
                if(response.isSuccessful()){
                    matchs = response.body().getMatches();
                    matchAdapter = new MatchAdapter(matchs,MainActivity.this);
                    recyclerView.setAdapter(matchAdapter);
                    //Toast.makeText(MainActivity.this,"Get data: " +matchs.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<MatchResponses> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}