package com.example.footballapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePassword extends AppCompatActivity {
    EditText editOldPass, editNewPass, editConfirmPass;
    MaterialButton change;
    FirebaseUser user;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        editOldPass = findViewById(R.id.oldPassword);
        editNewPass = findViewById(R.id.newPassword);
        editConfirmPass = findViewById(R.id.confirmPassword);

        user = FirebaseAuth.getInstance().getCurrentUser();
        progressBar = findViewById(R.id.progessBar);
        change = findViewById(R.id.change);

//        ActionBar actionBar = getSupportActionBar();
//
//        // showing the back button in action bar
//        // Temp
//        actionBar.setDisplayHomeAsUpEnabled(true);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword();
            }
        });
    }

    public void changePassword(){
        String oldPassword = editOldPass.getText().toString().trim();
        String newPassword = editNewPass.getText().toString().trim();
        String confirmPassword = editConfirmPass.getText().toString().trim();

        if(oldPassword.isEmpty()){
            editOldPass.setError("Hãy nhập mật khẩu cũ");
            editOldPass.requestFocus();
            return;
        }

        if(newPassword.isEmpty()){
            editNewPass.setError("Hãy nhập mật khẩu mới");
            editNewPass.requestFocus();
            return;
        }

        if(confirmPassword.isEmpty()){
            editConfirmPass.setError("Hãy xác nhận mật khẩu");
            editConfirmPass.requestFocus();
            return;
        }

        if(!newPassword.equals(confirmPassword)){
            editConfirmPass.setError("Mật khẩu xác nhận sai");
            editConfirmPass.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(), oldPassword);
        user.reauthenticate(authCredential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        user.updatePassword(newPassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(ChangePassword.this, "Thay đổi mật khẩu thành công", Toast.LENGTH_LONG).show();

                                }else{
                                    Toast.makeText(ChangePassword.this, "Nhập lại mật khẩu cũ", Toast.LENGTH_LONG).show();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        });
                    }
                });
    }
}