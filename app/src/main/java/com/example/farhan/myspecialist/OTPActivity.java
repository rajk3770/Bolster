package com.example.farhan.myspecialist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by raj on 14/3/18.
 */

public class OTPActivity extends AppCompatActivity {

    private TextView mHName;
    private TextView mOTP;
    private TextView t1;
    private TextView t2;
    int i;
    private DatabaseReference mDatabase;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        mHName = (TextView) findViewById(R.id.otp_hosp);
        mOTP = (TextView) findViewById(R.id.otp);

        String value = getIntent().getExtras().getString("Hospital");
        mHName.setText(value);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("users");

        try {
            i = generatePin();
            mDatabase.child(user.getUid()).child("OTP").setValue(i);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //int otp=getIntent().getExtras().getInt("OTP");
        //String otp = getIntent().getExtras().getString("OTP");
        //int otp = getIntent().getIntExtra("intVariableName", 0);
        //mOTP.setText(String.valueOf(otp));

        /*Bundle extras = getIntent().getExtras();
        String stringVariableName = extras.getString("OTP");
        int intVariableName = Integer.parseInt(stringVariableName);*/
       // mOTP.setText(String.valueOf(i));

        t1 = (TextView) findViewById(R.id.textView3);
        t2 = (TextView) findViewById(R.id.textView4);

        mOTP.setText(""+i);
//        while (true) {

            //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            //String id = user.getUid();
            mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
            if (mDatabase.child(user.getUid()).child("Active").equals("0")) {

                mHName.setText("");
                mOTP.setText("Your OTP Has Been Verified");
                t1.setText("");
                t2.setText("");

                new Timer().schedule(new TimerTask() {
                    public void run() {
                        startActivity(new Intent(OTPActivity.this, MainActivity.class));
                    }
                }, 4000);
    //            break;

  //          }

        }
    }
    public static int generatePin() throws Exception {
        Random generator = new Random();
        generator.setSeed(System.currentTimeMillis());

        int num = generator.nextInt(99999) + 99999;
        if (num < 100000 || num > 999999) {
            num = generator.nextInt(99999) + 99999;
            if (num < 100000 || num > 999999) {
                throw new Exception("Unable to generate PIN at this time..");
            }
        }
        return num;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i=new Intent(OTPActivity.this, MainActivity.class);
        startActivity(i);
    }
}
