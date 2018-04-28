package com.example.farhan.myspecialist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class StartVitalSigns extends AppCompatActivity {
    private String user;
    private int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_vital_signs);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("Usr");
            p = extras.getInt("Page");
        }

        ImageButton VS = (ImageButton)this.findViewById(R.id.StartVS);

        VS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //switch is to decide which activity must be opened

/*
                    case 1:  {Intent i = new Intent(v.getContext(),HeartRateProcess.class);
                        i.putExtra("Usr", user);
                        startActivity(i);
                        finish();}
                        break;

                    case 2:  {Intent i = new Intent(v.getContext(),BloodPressureProcess.class);
                        i.putExtra("Usr", user);
                        startActivity(i);
                        finish();}
                        break;

                    case 3:  {Intent i = new Intent(v.getContext(),RespirationProcess.class);
                        i.putExtra("Usr", user);
                        startActivity(i);
                        finish();}
                        break;

                    case 4:  {Intent i = new Intent(v.getContext(),O2Process.class);
                        i.putExtra("Usr", user);
                        startActivity(i);
                        finish();}
                        break;
*/
                    Intent i = new Intent(v.getContext(),VitalSignsProcess.class);
                        i.putExtra("Usr", user);
                        startActivity(i);
                        finish();



            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(StartVitalSigns.this, MainActivity.class);
        startActivity(i);
        finish();
        super.onBackPressed();
            }


    }
