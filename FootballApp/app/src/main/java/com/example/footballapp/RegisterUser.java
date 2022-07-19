package com.example.footballapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.footballapp.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterUser extends AppCompatActivity implements View.OnClickListener {
    private EditText editUsername, editEmail, editPass, editConfirm;
    private RadioButton male;
    private FirebaseAuth mAuth;
    private MaterialButton register;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance();

        editUsername = findViewById(R.id.username);
        male = findViewById(R.id.radio_male);
        editEmail = findViewById(R.id.email);
        editPass = findViewById(R.id.password);
        editConfirm = findViewById(R.id.confirm_password);

        register = findViewById(R.id.register);
        register.setOnClickListener(this);

        progressBar = findViewById(R.id.progessBar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String username = editUsername.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPass.getText().toString().trim();
        String confirmPass = editConfirm.getText().toString().trim();
        String gender = "";
        if(male.isChecked() == true){
            gender = "male";
        }else{
            gender = "female";
        }

        if(username.isEmpty()){
            editUsername.setError("Username is required");
            editUsername.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editEmail.setError("Email is required");
            editEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editEmail.setError("Please provide valid email");
            editEmail.requestFocus();
        }

        if(password.isEmpty()){
            editPass.setError("Password is required");
            editPass.requestFocus();
            return;
        }

        if(!password.equals(confirmPass)){
            editConfirm.setError("Password must match confirm password");
            editConfirm.requestFocus();
            return;
        }

        String finalGender = gender;
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user = new User(username, finalGender, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterUser.this, "User has been registerd successfully!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);


                                    }else{
                                        Toast.makeText(RegisterUser.this, "Fail to register! Try again!", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(RegisterUser.this, "Fail to register 1! Try again!", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });

    }
}