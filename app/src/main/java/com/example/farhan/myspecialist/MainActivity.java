package com.example.farhan.myspecialist;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;
    FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(this);

       // FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
      //  String x=user.getUid();
       // x=getIntent().getExtras().getString("UID");

        //Log.e("Upload Activity",user.getUid());

        mDrawerlayout = (DrawerLayout)findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this,mDrawerlayout,R.string.open,R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.main_container, new HomeFragment());
        fragmentTransaction.commit();
        getSupportActionBar().setTitle("Bolster");
        navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new HomeFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Bolster");
                        item.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;

                    case R.id.hospital_id:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new HospitalFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Bolster");
                        item.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;

                    case R.id.symptom_checker:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new SymptomCheckerFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Bolster");
                        item.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;

                    case R.id.logout:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new LogoutFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Bolster");
                        item.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;

                    case R.id.uploads:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new UploadFragment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Bolster");
                        item.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;

                    case R.id.vitalsigns:
                        fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.main_container, new VitalSignsFagment());
                        fragmentTransaction.commit();
                        getSupportActionBar().setTitle("Bolster");
                        item.setChecked(true);
                        mDrawerlayout.closeDrawers();
                        break;



                }
                return true;
            }
        });
    }
  /*  public void load(WebView w1){
        Log.e("Check","Hi");
        WebView w=w1;
        //w=(WebView)findViewById(R.id.webview);
       // w.addJavascriptInterface(new JavaScriptInterface(),"Android");
        Log.e("Check","Hi");
        //w.setWebViewClient(new WebViewClient());

//w.loadUrl("https://192.168.43.128:8000/");

        w.loadUrl("file:///android_asset/js-symptom-checker-example-master/index.html");
        Log.e("Check","Hi");
    }
*/
    /*private class JavaScriptInterface{
        JavaScriptInterface(){
        }
        public void showOffers() {
            w.loadUrl("file:///android_asset/js-symptom-checker-example-master/index.html");
        }
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

/*
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }*/
}
