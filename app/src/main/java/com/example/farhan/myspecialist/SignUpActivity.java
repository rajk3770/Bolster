package com.example.farhan.myspecialist;

/**
 * Created by raj on 13/3/18.
 */


import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
        import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.auth.AuthResult;
        import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    //defining view objects
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSignup;
    private ProgressDialog progressDialog;
    private EditText name;
    private EditText phone;
    private EditText age;
    private EditText gender;
    private EditText bg;
    private DatabaseReference mDatabase;
    private ListView mUserList;
    private ArrayList<String> mUsernames=new ArrayList<>();
    private ArrayList<String> mkeys=new ArrayList<>();
    //defining firebaseauth object
    private FirebaseAuth firebaseAuth;

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textView=findViewById(R.id.textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));

            }
        });
        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        //initializing views
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonSignup = (Button) findViewById(R.id.buttonSignup);

        progressDialog = new ProgressDialog(this);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        age=(EditText)findViewById(R.id.age);
        gender=(EditText)findViewById(R.id.gender);
        bg=(EditText)findViewById(R.id.bg);




        mDatabase= FirebaseDatabase.getInstance().getReference().child("users");
        final ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,mUsernames);
        //attaching listener to button
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                            }
        });


        buttonSignup.setOnClickListener(this);

    }

    public void registerUser(){

        //getting email and password from edit texts
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(SignUpActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();

                            HashMap<String, String> datamap = new HashMap<String, String>();

                            // String x = datamap.get("Name");
                            //  x="0";

                            //   mDatabase.child(FirebaseAuth.getInstance().getUid()).child("Active").setValue("1");
                            //   datamap.put("Name",x);
                            datamap.put("Name", name.getText().toString());
                            datamap.put("Age", age.getText().toString());
                            datamap.put("Phone", phone.getText().toString());
                            datamap.put("Gender", gender.getText().toString());
                            datamap.put("Blood Group", bg.getText().toString());
                            datamap.put("Active", "0");
                            datamap.put("OTP", "900");
                            datamap.put("Prescription", "pres");

                            Intent i=new Intent(SignUpActivity.this,LoginActivity.class);
                            i.putExtra("Datamap",datamap);
                            startActivity(i);



                        }else{
                            //display some message here
                            Toast.makeText(SignUpActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View view) {

/*
        //calling register method on click
        HashMap<String, String> datamap = new HashMap<String, String>();

       // String x = datamap.get("Name");
      //  x="0";

     //   mDatabase.child(FirebaseAuth.getInstance().getUid()).child("Active").setValue("1");
     //   datamap.put("Name",x);
        datamap.put("Name", name.getText().toString());
        datamap.put("Age", age.getText().toString());
        datamap.put("Phone", phone.getText().toString());
        datamap.put("Gender", gender.getText().toString());
        datamap.put("Blood Group", bg.getText().toString());
        datamap.put("Active", "0");
        datamap.put("OTP", "900");
        datamap.put("Prescription", "pres");

        Intent i=new Intent(SignUpActivity.this,LoginActivity.class);
        i.putExtra("Datamap",datamap);
        startActivity(i);
        Log.e("Output","Hi");
        Log.e("UID",FirebaseAuth.getInstance().getUid());

*/

        registerUser();

    }
}
