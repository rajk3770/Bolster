package com.example.farhan.myspecialist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * Created by raj on 12/3/18.
 */


public class LoginActivity extends AppCompatActivity{

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;

    private DatabaseReference mDatabase;

    @Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

       /* if (auth.getCurrentUser() != null) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
        }*/

        // set the view now
        setContentView(R.layout.activity_login);
/*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        inputEmail = (EditText) findViewById(R.id.email);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
        });

        /*btnReset.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        startActivity(new Intent(LoginActivity.this, ResetPasswordActivity.class));
        }
        });*/

        btnLogin.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        String email = inputEmail.getText().toString();
final String password = inputPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
        Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
        return;
        }

        if (TextUtils.isEmpty(password)) {
        Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
        return;
        }

        progressBar.setVisibility(View.VISIBLE);

        //authenticate user
        auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {




                @Override
public void onComplete(@NonNull Task<AuthResult> task) {
        // If sign in fails, display a message to the user. If sign in succeeds
        // the auth state listener will be notified and logic to handle the
        // signed in user can be handled in the listener.
        progressBar.setVisibility(View.GONE);
        if (!task.isSuccessful()) {
        // there was an error
        if (password.length() < 6) {
        inputPassword.setError(getString(R.string.minimum_password));
        } else {
        Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
        }
        } else {

                Intent i=new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                /*
                mDatabase= FirebaseDatabase.getInstance().getReference().child("users");
                Intent intent = getIntent();
                HashMap<String, String> datamap = (HashMap<String, String>)intent.getSerializableExtra("Datamap");
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                mDatabase.child(user.getUid()).setValue(datamap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                                if (task.isSuccessful()) {
                                        Toast.makeText(LoginActivity.this, "Data Stored Successfully", Toast.LENGTH_LONG).show();
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        Log.e("Upload Activity",user.getUid());

                                        SignUpActivity s=new SignUpActivity();
                                      //  s.registerUser();



                                } else {
                                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_LONG).show();
                                }


                        }
                });*/





        /*        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                Log.e("Upload Activity",user.getUid());
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();*/
        }
        }
        });
        }
        });
        }

        public void logout(){
                auth.getInstance().signOut();
                /*Intent i=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(i);*/
        }

        }