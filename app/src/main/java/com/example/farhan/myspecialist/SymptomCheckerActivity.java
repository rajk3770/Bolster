package com.example.farhan.myspecialist;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebViewClient;

/**
 * Created by raj on 12/3/18.
 */

public class SymptomCheckerActivity extends Activity {
    //private WebView w;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        Log.e("Hi",""+(WebView)findViewById(R.id.webx));
        setContentView(R.layout.fragment_symptom_checker);
        WebView w=(WebView)findViewById(R.id.webx);
        Log.e("Output",w.toString());
        //MainActivity m=new MainActivity();
        WebSettings webSettings = w.getSettings();
        //Log.e("Check","Hi");
        webSettings.setJavaScriptEnabled(true);
        w.loadUrl("file:///android_asset/js-symptom-checker-example-master/index.html");
        //Log.e("Check","Hi");
        //m.load(w);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent i=new Intent(SymptomCheckerActivity.this,MainActivity.class);
        startActivity(i);

    }
}

