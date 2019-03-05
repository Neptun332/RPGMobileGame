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
import android.widget.Toast;

import com.example.patry.rpgmobilegame.R;
import com.example.patry.rpgmobilegame.player.Character;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

import static com.example.patry.rpgmobilegame.player.Character.DEFAULT_AGL;
import static com.example.patry.rpgmobilegame.player.Character.DEFAULT_STR;
import static com.example.patry.rpgmobilegame.player.Character.DEFAULT_VIT;
import static com.example.patry.rpgmobilegame.player.Character.KEY_AGL;
import static com.example.patry.rpgmobilegame.player.Character.KEY_NAME;
import static com.example.patry.rpgmobilegame.player.Character.KEY_STR;
import static com.example.patry.rpgmobilegame.player.Character.KEY_VIT;
import static java.lang.Math.toIntExact;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegisterActivity";

    private Button registerButton;
    private EditText nicknameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText repeatPasswordEditText;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(this);

        registerButton = findViewById(R.id.registerButton);
        nicknameEditText = findViewById(R.id.nickname);
        emailEditText = findViewById(R.id.email);
        passwordEditText = findViewById(R.id.pass);
        repeatPasswordEditText = findViewById(R.id.repeatpass);

        registerButton.setOnClickListener(this);


    }

    private void registerUser() {
        String nickName = nicknameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String pass = passwordEditText.getText().toString().trim();
        String repeatPass = repeatPasswordEditText.getText().toString().trim();

        if (nickName.isEmpty()) {
            Toast.makeText(this, "Please enter nickname", Toast.LENGTH_SHORT).show();
            return;
        }
        if (email.isEmpty()) {
            Toast.makeText(this, "Please enter e-mail adress", Toast.LENGTH_SHORT).show();
            return;
        }
        if (pass.isEmpty() || !pass.equals(repeatPass)) {
            Toast.makeText(this, "Password must be the same", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pass.length() <= 6) {
            Toast.makeText(this, "Password must be at least 6 characters long", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                            //pobranie usera i update jego display name na taki jak podał przy rejestracji
                            task.getResult()
                                    .getUser()
                                    .updateProfile(new UserProfileChangeRequest
                                            .Builder()
                                            .setDisplayName(nicknameEditText.getText().toString())
                                            .build());
                            AddUserToDatabase();

                            Intent intent = new Intent(RegisterActivity.this, GameActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(RegisterActivity.this, "Error " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, task.getException().toString());
                        }
                    }
                });

    }

    private void AddUserToDatabase( ) {
        Map<String, Object> note = new HashMap<>();

        note.put(KEY_NAME, nicknameEditText.getText().toString());
        note.put(KEY_STR, DEFAULT_STR);
        note.put(KEY_AGL, DEFAULT_AGL);
        note.put(KEY_VIT, DEFAULT_VIT);

        db
                .collection("Users")
                .document(firebaseAuth.getUid())
                .collection("Character")
                .document("Stats")
                .set(note)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(RegisterActivity.this, "Note saved", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                        Log.d(TAG, e.toString());
                    }
                });

    }

    @Override
    public void onClick(View v) {
        registerUser();
    }
}
