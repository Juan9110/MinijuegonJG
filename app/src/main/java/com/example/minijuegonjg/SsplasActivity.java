package com.example.minijuegonjg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SsplasActivity extends AppCompatActivity {
   Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssplas);

        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent= new Intent(SsplasActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1500);

    }
}


