package com.example.footballapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editUsername, editPassword;
    private TextView register, forgetPassword;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private MaterialButton login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsername = findViewById(R.id.username);
        editPassword = findViewById(R.id.password);
        register = findViewById(R.id.register);
        register.setOnClickListener(this);

        forgetPassword = findViewById(R.id.forgotpassword);
        forgetPassword.setOnClickListener(this);

        login = findViewById(R.id.login);
        login.setOnClickListener(this);

        progressBar = findViewById(R.id.progessBar);
        mAuth = FirebaseAuth.getInstance();
        getSupportActionBar().hide();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                //Toast.makeText(this, "login", Toast.LENGTH_SHORT).show();
                userLogin();
                break;
            case R.id.register:
                //Toast.makeText(this, "register", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, RegisterUser.class));
                break;
            case R.id.forgotpassword:
                startActivity(new Intent(this, ForgotPassword.class));
                break;
        }
    }

    private void userLogin() {
        String username = editUsername.getText().toString().trim();
        String password = editPassword.getText().toString().trim();

        if(username.isEmpty()){
            editUsername.setError("Email is required");
            editUsername.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(username).matches()){
            editUsername.setError("Please enter invalid email!");
            editUsername.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editPassword.setError("Email is required");
            editPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(username, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    progressBar.setVisibility(View.GONE);
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                    if(user == null){
                        Toast.makeText(LoginActivity.this, "null user", Toast.LENGTH_SHORT).show();
                    }

                    if(user.isEmailVerified()){
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this, "Check your email to verify your account!", Toast.LENGTH_LONG).show();
                    }

                }else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Failed to login! Please check your credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}