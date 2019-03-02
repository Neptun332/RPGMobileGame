package com.example.patry.rpgmobilegame.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.patry.rpgmobilegame.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private Button loginButton;
    private Button registerButton;

    private EditText emialEditText;
    private EditText passwordEditText;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        loginButton = findViewById(R.id.loginButtton);
        registerButton = findViewById(R.id.registerButton);
        emialEditText = findViewById(R.id.email_login);
        passwordEditText = findViewById(R.id.password_login);

        loginButton.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onClick(View v) {

        progressDialog.setMessage("Logging...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(emialEditText.toString(), passwordEditText.toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent intent = new Intent(LoginActivity.this, GameActivity.class);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(LoginActivity.this, "Error " + e.getMessage(), Toast.LENGTH_SHORT);
                Log.d(TAG,e.toString());
            }
        });

        Intent intent = new Intent(LoginActivity.this, GameActivity.class);
        startActivity(intent);


    }
}
