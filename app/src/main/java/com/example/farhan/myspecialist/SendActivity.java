package com.example.farhan.myspecialist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * Created by raj on 14/3/18.
 */

public class SendActivity extends AppCompatActivity {

    private EditText mEdit;
    private Button mData;

    private DatabaseReference mDatabase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        mEdit = (EditText)findViewById(R.id.hospitaltxt);
        Log.e("X",mEdit.getText().toString());
        mData=(Button)findViewById(R.id.databtn);

        mData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {


                    Log.e("HI","reached");
                    Intent intent=new Intent(SendActivity.this,OTPActivity.class);
                    Log.e("HI","reached");
                    intent.putExtra("Hospital",mEdit.getText().toString());
                    //intent.putExtra("OTP",i);
                    Log.e("HI","reached");
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }


}
