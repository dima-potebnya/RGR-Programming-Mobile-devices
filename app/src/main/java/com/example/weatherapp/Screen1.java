package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class Screen1 extends AppCompatActivity {

    private EditText cityName;
    private String name ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //необхідно для приховання заголовка
        getSupportActionBar().hide(); // приховує рядок заголовка
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //тепер вмикаємо на повний екран

        setContentView(R.layout.activity_screen1); // установлюємо макет для цього класу

        cityName = (EditText) findViewById(R.id.city_name); // заносимо в змінну інформацію про назву місто
    }

    public void movetoScreen2(View view) { // переміщення до наступного актівіті
        name = cityName.getText().toString();
        if (name.equals("")) { //якщо пусте поле, то висвічується підказка
            Toast.makeText(this, "Введіть місто.....", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, Screen2.class);
            intent.putExtra("name",name ); //передаємо значення міста наступному актівіті
            startActivity(intent);
            finish();
        }
    }

    public void backtoSplash(View view) { //також створюємо кнопку для переходу назад до сплешу
        Intent intent = new Intent(this,SplashScreen.class);
        startActivity(intent);
        finish();
    }
}