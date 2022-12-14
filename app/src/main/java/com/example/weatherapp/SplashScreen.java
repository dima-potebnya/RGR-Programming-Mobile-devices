package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); //необхідно для приховання заголовка
        getSupportActionBar().hide(); // приховує рядок заголовка
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //тепер вмикаємо на повний екран

        setContentView(R.layout.activity_splash_screen); // установлюємо макет для цього класу
    }

    public void movetoScreen1(View view) { // переміщення до наступного актівіті
        Intent intent = new Intent(this,Screen1.class);
        startActivity(intent);
        finish();
    }
}