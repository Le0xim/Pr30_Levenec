package com.example.pr30_levenec;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Задержка 2000 мс (2 секунды)
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // ВАЖНО: Здесь мы открываем OnboardingActivity, а не MainActivity
                Intent intent = new Intent(SplashActivity.this, OnboardingActivity.class);
                startActivity(intent);
                finish(); // Закрываем Splash
            }
        }, 2000);
    }
}