package com.dam.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView imageView = findViewById(R.id.ivSplash);

        imageView.animate()
                .alpha(1)
                .rotation(3600)
                .setDuration(3000)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(Splash.this, Home.class));
                    }
                })
                .start();

    }
}