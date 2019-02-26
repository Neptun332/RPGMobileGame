package com.example.patry.rpgmobilegame;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Loging extends AppCompatActivity implements View.OnClickListener {

    private Button registerButton;
    private EditText nicknameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText repeatPasswordEditText;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loging);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        registerButton = (Button) findViewById(R.id.registerButton);
        nicknameEditText = (EditText) findViewById(R.id.nickname);
        emailEditText = (EditText) findViewById(R.id.email);
        passwordEditText = (EditText) findViewById(R.id.pass);
        repeatPasswordEditText = (EditText) findViewById(R.id.repeatpass);

        registerButton.setOnClickListener(this);
    }

    private void registerUser(){
        String nickName = nicknameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String pass = passwordEditText.getText().toString().trim();
        String repeatPass = repeatPasswordEditText.getText().toString().trim();

        if (nickName.isEmpty()){
            Toast.makeText(this,"Please enter nickname",Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()){
            Toast.makeText(this,"Please enter e-mail adress",Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.isEmpty() || !pass.equals(repeatPass)){
            Toast.makeText(this,"Password must be the same",Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass.length()<=6){
            Toast.makeText(this,"Password must be at least 6 characters long",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Loging.this, "User Registered", Toast.LENGTH_SHORT).show();
                    }else{
                            Toast.makeText(Loging.this,"Error" + task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    @Override
    public void onClick(View v) {
        registerUser();
    }
}
