package com.example.footballapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.footballapp.ChangePassword;
import com.example.footballapp.LoginActivity;
import com.example.footballapp.MainActivity;
import com.example.footballapp.ProfileActivity;
import com.example.footballapp.R;
import com.example.footballapp.WebViewActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NavigationDrawer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavigationDrawer extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    private static DrawerLayout drawerLayout;
    //private static WebViewActivity webView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NavigationDrawer() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NavigationDrawer.
     */
    // TODO: Rename and change types and number of parameters
    public static NavigationDrawer newInstance(String param1, String param2) {
        NavigationDrawer fragment = new NavigationDrawer();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        drawerLayout = view.findViewById(R.id.drawer_layout);
        Toolbar toolbar = view.findViewById(R.id.toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(((MainActivity)getActivity()), drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(false);

        toggle.syncState();

        NavigationView navigationView = view.findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Temp fix
        //((MainActivity)getActivity()).getSupportActionBar().hide();
        view.findViewById(R.id.webView);
    }

    public static boolean isOpen(){
        return drawerLayout.isDrawerOpen(Gravity.RIGHT);
    }
    public static void OpenDrawer(){
        drawerLayout.openDrawer(Gravity.RIGHT);
    }

    public static void CloseDrawer(){
        drawerLayout.closeDrawer(Gravity.RIGHT);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.nav_profile){
            startActivity(new Intent(getActivity(), ProfileActivity.class));
        }else if (id == R.id.nav_setting){
            startActivity(new Intent(getActivity(), ChangePassword.class));
        }else if (id == R.id.nav_logout){
            // Code for sign out
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getActivity(), LoginActivity.class));
        }else if(id == R.id.nav_ball){
            Intent myIntent = new Intent(getActivity(), WebViewActivity.class);
            myIntent.putExtra("url", "https://he141110-trantuananh.github.io/Android-webview/footballApp.html");
            startActivity(myIntent);
        }else if(id == R.id.nav_policy){
            Intent myIntent = new Intent(getActivity(), WebViewActivity.class);
            myIntent.putExtra("url", "https://he141110-trantuananh.github.io/Android-webview/policy.html");
            startActivity(myIntent);
        }else if(id == R.id.nav_term_of_use){
            Intent myIntent = new Intent(getActivity(), WebViewActivity.class);
            myIntent.putExtra("url", "https://he141110-trantuananh.github.io/Android-webview/termOfUse.html");
            startActivity(myIntent);
        }else{
            Intent myIntent = new Intent(getActivity(), WebViewActivity.class);
            myIntent.putExtra("url", "https://he141110-trantuananh.github.io/Android-webview/contact.html");
            startActivity(myIntent);
        }

        return false;
    }


}