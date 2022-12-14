package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapp.db.WeatherDatabase;
import com.example.weatherapp.db.entities.cityName;
import com.example.weatherapp.db.entities.cityWeather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

public class Screen2 extends AppCompatActivity {


    // необхідні змінні для роботи класу
    private WeatherDatabase db;
    private String  name, url;
    private double pressing, speedy;
    private TextView cityTextView , countryTextView , temperatureTextView , descriptionTextView;
    private TextView windSpeedTextView, windDirectionTextView, pressureTextView , humdidityTextView;
    private ImageView ImagePogoda;
    private JSONObject find;
    ProgressDialog progressDialog ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //необхідно для приховання заголовка
        getSupportActionBar().hide(); // приховує рядок заголовка
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //тепер вмикаємо на повний екран

        setContentView(R.layout.activity_screen2); // установлюємо макет для цього класу
         // робимо змінні для взяття значень погоди та синхронізації цього з макетом
         cityTextView= (TextView) findViewById(R.id.city_name_textView); // місто
         countryTextView= (TextView) findViewById(R.id.country_name_textView); // країна
         temperatureTextView= (TextView) findViewById(R.id.temperature_textView); // температура
         descriptionTextView= (TextView) findViewById(R.id.description_textView); // опис погоди
         windSpeedTextView= (TextView) findViewById(R.id.wind_speed_textView); // швидкіть вітру
         windDirectionTextView= (TextView) findViewById(R.id.wind_direction_textView); // кут вітру
         pressureTextView= (TextView) findViewById(R.id.pressure_textView); // атмосферний тиск
         humdidityTextView= (TextView) findViewById(R.id.humidity_textView); // вологість повітря
         ImagePogoda= (ImageView) findViewById(R.id.imageView2); //Картинка Яка зараз погода в місті
        // будуємо базу даних
        db = Room.databaseBuilder(this, WeatherDatabase.class, "student-db").allowMainThreadQueries().build();

        // отримуємо значення міста введеного користувачем
        name= getIntent().getStringExtra("name");
        //підключаємося до АРІ-джерела weatherbit.io по місту та ключу АРІ
        url = "https://api.weatherbit.io/v2.0/current?&city=" + name + "&country=UA&key=bcd5a77bdd4d4c8487b6ce621c529c1f";

        //виконуємо пошук значень
        Search();




    }


    private void Search() {

        // робимо запит до сервера
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            try {

                // отримуємо значення міста з бази даних
                cityName city = db.CityDAO().getCity(name.toLowerCase());
                if(city ==null)
                {
                    // якщо значення порожнє то вносимо його з введеного користувачем
                    cityName c = new cityName();
                    c.cityName = name;
                    db.CityDAO().insert(c);
                    // робимо привітання з користувачем
                    Toast.makeText(this, "Привіт!", Toast.LENGTH_SHORT).show();


                }
                // знову отримуємо значення міста з бази даних
                cityName city1 = db.CityDAO().getCity(name);
                // та виводимо користувачеві місто яке він обрав
                Toast.makeText(this, "Місто : "+ city1.cityName, Toast.LENGTH_SHORT).show();



                // отримуємо вихідні значення відповіді від сервера та вносимо в json-обробник
                JSONArray details = response.getJSONArray("data");




                // створюємо змінні для зручності пошуку
                find = details.getJSONObject(0);
                char tmp = 0x00B0;

                cityTextView.setText(name); // відображаємо місто
                if(find.getString("country_code").equals("UA")) {
                    countryTextView.setText("Україна"); // відображаємо країну
                }
                temperatureTextView.setText(find.getString("temp")+" °C"); // температуру
                JSONObject weather = find.getJSONObject("weather"); // робимо парсинг по блоку
                String icon = weather.getString("icon"); // номер картинки погоди
                String code = weather.getString("code"); // номер коду погоди
                speedy = Math.round(find.getDouble("wind_spd")); // швидкість вітру
                windSpeedTextView.setText(Integer.toString((int)speedy)+" м/с");
                windDirectionTextView.setText(find.getString("wind_dir")+" °"); // кут вітру
                pressing = Math.round(find.getDouble("pres") * 0.750062); // тиск, переводимо в мм рт.ст.
                pressureTextView.setText(Integer.toString((int)pressing)+" мм");
                humdidityTextView.setText(find.getString("rh")+" %"); // вологість
                //Далі підуть умови які регулюють опис погоди та картинку погоди
                if(code.equals("200")) {
                    descriptionTextView.setText("Гроза з невеликим дощем"); //Ставимо опис погоди
                    if(icon.equals("t01d")) {
                        ImagePogoda.setImageResource(R.drawable.t01d); //Ставимо картинку погоди
                    }
                    if(icon.equals("t01n")) {
                        ImagePogoda.setImageResource(R.drawable.t01n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("201")) {
                    descriptionTextView.setText("Гроза з дощем"); //Ставимо опис погоди
                    if(icon.equals("t02d")) {
                        ImagePogoda.setImageResource(R.drawable.t02d); //Ставимо картинку погоди
                    }
                    if(icon.equals("t02n")) {
                        ImagePogoda.setImageResource(R.drawable.t02n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("202")) {
                    descriptionTextView.setText("Гроза з сильним дощем"); //Ставимо опис погоди
                    if(icon.equals("t03d")) {
                        ImagePogoda.setImageResource(R.drawable.t03d); //Ставимо картинку погоди
                    }
                    if(icon.equals("t03n")) {
                        ImagePogoda.setImageResource(R.drawable.t03n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("230")) {
                    descriptionTextView.setText("Гроза з легкою мрякою"); //Ставимо опис погоди
                    if(icon.equals("t04d")) {
                        ImagePogoda.setImageResource(R.drawable.t04d); //Ставимо картинку погоди
                    }
                    if(icon.equals("t04n")) {
                        ImagePogoda.setImageResource(R.drawable.t04n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("231")) {
                    descriptionTextView.setText("Гроза з мрякою"); //Ставимо опис погоди
                    if(icon.equals("t04d")) {
                        ImagePogoda.setImageResource(R.drawable.t04d); //Ставимо картинку погоди
                    }
                    if(icon.equals("t04n")) {
                        ImagePogoda.setImageResource(R.drawable.t04n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("232")) {
                    descriptionTextView.setText("Гроза з сильною мрякою"); //Ставимо опис погоди
                    if(icon.equals("t04d")) {
                        ImagePogoda.setImageResource(R.drawable.t04d); //Ставимо картинку погоди
                    }
                    if(icon.equals("t04n")) {
                        ImagePogoda.setImageResource(R.drawable.t04n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("233")) {
                    descriptionTextView.setText("Гроза з градом"); //Ставимо опис погоди
                    if(icon.equals("t05d")) {
                        ImagePogoda.setImageResource(R.drawable.t05d); //Ставимо картинку погоди
                    }
                    if(icon.equals("t05n")) {
                        ImagePogoda.setImageResource(R.drawable.t05n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("300")) {
                    descriptionTextView.setText("Легка мряка"); //Ставимо опис погоди
                    if(icon.equals("d01d")) {
                        ImagePogoda.setImageResource(R.drawable.d01d); //Ставимо картинку погоди
                    }
                    if(icon.equals("d01n")) {
                        ImagePogoda.setImageResource(R.drawable.d01n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("301")) {
                    descriptionTextView.setText("Дрібний дощ"); //Ставимо опис погоди
                    if(icon.equals("d02d")) {
                        ImagePogoda.setImageResource(R.drawable.d02d); //Ставимо картинку погоди
                    }
                    if(icon.equals("d02n")) {
                        ImagePogoda.setImageResource(R.drawable.d02n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("302")) {
                    descriptionTextView.setText("Сильний дощ з мрякою"); //Ставимо опис погоди
                    if(icon.equals("d03d")) {
                        ImagePogoda.setImageResource(R.drawable.d03d); //Ставимо картинку погоди
                    }
                    if(icon.equals("d03n")) {
                        ImagePogoda.setImageResource(R.drawable.d03n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("500")) {
                    descriptionTextView.setText("Легкий дощ"); //Ставимо опис погоди
                    if(icon.equals("r01d")) {
                        ImagePogoda.setImageResource(R.drawable.r01d); //Ставимо картинку погоди
                    }
                    if(icon.equals("r01n")) {
                        ImagePogoda.setImageResource(R.drawable.r01n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("501")) {
                    descriptionTextView.setText("Помірний дощ"); //Ставимо опис погоди
                    if(icon.equals("r02d")) {
                        ImagePogoda.setImageResource(R.drawable.r02d); //Ставимо картинку погоди
                    }
                    if(icon.equals("r02n")) {
                        ImagePogoda.setImageResource(R.drawable.r02n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("502")) {
                    descriptionTextView.setText("Сильний дощ"); //Ставимо опис погоди
                    if(icon.equals("r03d")) {
                        ImagePogoda.setImageResource(R.drawable.r03d); //Ставимо картинку погоди
                    }
                    if(icon.equals("r03n")) {
                        ImagePogoda.setImageResource(R.drawable.r03n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("511")) {
                    descriptionTextView.setText("Крижаний дощ"); //Ставимо опис погоди
                    if(icon.equals("f01d")) {
                        ImagePogoda.setImageResource(R.drawable.f01d); //Ставимо картинку погоди
                    }
                    if(icon.equals("f01n")) {
                        ImagePogoda.setImageResource(R.drawable.f01n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("520")) {
                    descriptionTextView.setText("Легкий проливний дощ"); //Ставимо опис погоди
                    if(icon.equals("r04d")) {
                        ImagePogoda.setImageResource(R.drawable.r04d); //Ставимо картинку погоди
                    }
                    if(icon.equals("r04n")) {
                        ImagePogoda.setImageResource(R.drawable.r04n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("521")) {
                    descriptionTextView.setText("Злива"); //Ставимо опис погоди
                    if(icon.equals("r05d")) {
                        ImagePogoda.setImageResource(R.drawable.r05d); //Ставимо картинку погоди
                    }
                    if(icon.equals("r05n")) {
                        ImagePogoda.setImageResource(R.drawable.r05n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("522")) {
                    descriptionTextView.setText("Сильна злива"); //Ставимо опис погоди
                    if(icon.equals("r06d")) {
                        ImagePogoda.setImageResource(R.drawable.r06d); //Ставимо картинку погоди
                    }
                    if(icon.equals("r06n")) {
                        ImagePogoda.setImageResource(R.drawable.r06n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("600")) {
                    descriptionTextView.setText("Легкий сніг"); //Ставимо опис погоди
                    if(icon.equals("s01d")) {
                        ImagePogoda.setImageResource(R.drawable.s01d); //Ставимо картинку погоди
                    }
                    if(icon.equals("s01n")) {
                        ImagePogoda.setImageResource(R.drawable.s01n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("601")) {
                    descriptionTextView.setText("Сніг"); //Ставимо опис погоди
                    if(icon.equals("s02d")) {
                        ImagePogoda.setImageResource(R.drawable.s02d); //Ставимо картинку погоди
                    }
                    if(icon.equals("s02n")) {
                        ImagePogoda.setImageResource(R.drawable.s02n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("602")) {
                    descriptionTextView.setText("Снігопад"); //Ставимо опис погоди
                    if(icon.equals("s03d")) {
                        ImagePogoda.setImageResource(R.drawable.s03d); //Ставимо картинку погоди
                    }
                    if(icon.equals("s03n")) {
                        ImagePogoda.setImageResource(R.drawable.s03n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("610")) {
                    descriptionTextView.setText("Сніг з дощем"); //Ставимо опис погоди
                    if(icon.equals("s04d")) {
                        ImagePogoda.setImageResource(R.drawable.s04d); //Ставимо картинку погоди
                    }
                    if(icon.equals("s04n")) {
                        ImagePogoda.setImageResource(R.drawable.s04n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("611")) {
                    descriptionTextView.setText("Мокрий сніг"); //Ставимо опис погоди
                    if(icon.equals("s05d")) {
                        ImagePogoda.setImageResource(R.drawable.s05d); //Ставимо картинку погоди
                    }
                    if(icon.equals("s05n")) {
                        ImagePogoda.setImageResource(R.drawable.s05n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("612")) {
                    descriptionTextView.setText("Сильний мокрий сніг"); //Ставимо опис погоди
                    if(icon.equals("s05d")) {
                        ImagePogoda.setImageResource(R.drawable.s05d); //Ставимо картинку погоди
                    }
                    if(icon.equals("s05n")) {
                        ImagePogoda.setImageResource(R.drawable.s05n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("621")) {
                    descriptionTextView.setText("Снігова буря"); //Ставимо опис погоди
                    if(icon.equals("s01d")) {
                        ImagePogoda.setImageResource(R.drawable.s01d); //Ставимо картинку погоди
                    }
                    if(icon.equals("s01n")) {
                        ImagePogoda.setImageResource(R.drawable.s01n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("622")) {
                    descriptionTextView.setText("Сильна снігова буря"); //Ставимо опис погоди
                    if(icon.equals("s02d")) {
                        ImagePogoda.setImageResource(R.drawable.s02d); //Ставимо картинку погоди
                    }
                    if(icon.equals("s02n")) {
                        ImagePogoda.setImageResource(R.drawable.s02n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("623")) {
                    descriptionTextView.setText("Шквали снігу"); //Ставимо опис погоди
                    if(icon.equals("s06d")) {
                        ImagePogoda.setImageResource(R.drawable.s06d); //Ставимо картинку погоди
                    }
                    if(icon.equals("s06n")) {
                        ImagePogoda.setImageResource(R.drawable.s06n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("700")) {
                    descriptionTextView.setText("Туман"); //Ставимо опис погоди
                    if(icon.equals("a01d")) {
                        ImagePogoda.setImageResource(R.drawable.a01d); //Ставимо картинку погоди
                    }
                    if(icon.equals("a01n")) {
                        ImagePogoda.setImageResource(R.drawable.a01n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("711")) {
                    descriptionTextView.setText("Дим"); //Ставимо опис погоди
                    if(icon.equals("a02d")) {
                        ImagePogoda.setImageResource(R.drawable.a02d); //Ставимо картинку погоди
                    }
                    if(icon.equals("a02n")) {
                        ImagePogoda.setImageResource(R.drawable.a02n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("721")) {
                    descriptionTextView.setText("Мгла"); //Ставимо опис погоди
                    if(icon.equals("a03d")) {
                        ImagePogoda.setImageResource(R.drawable.a03d); //Ставимо картинку погоди
                    }
                    if(icon.equals("a03n")) {
                        ImagePogoda.setImageResource(R.drawable.a03n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("731")) {
                    descriptionTextView.setText("Пісок або пил"); //Ставимо опис погоди
                    if(icon.equals("a04d")) {
                        ImagePogoda.setImageResource(R.drawable.a04d); //Ставимо картинку погоди
                    }
                    if(icon.equals("a04n")) {
                        ImagePogoda.setImageResource(R.drawable.a04n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("741")) {
                    descriptionTextView.setText("Туман"); //Ставимо опис погоди
                    if(icon.equals("a05d")) {
                        ImagePogoda.setImageResource(R.drawable.a05d); //Ставимо картинку погоди
                    }
                    if(icon.equals("a05n")) {
                        ImagePogoda.setImageResource(R.drawable.a05n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("751")) {
                    descriptionTextView.setText("Крижаний Туман"); //Ставимо опис погоди
                    if(icon.equals("a06d")) {
                        ImagePogoda.setImageResource(R.drawable.a06d); //Ставимо картинку погоди
                    }
                    if(icon.equals("a06n")) {
                        ImagePogoda.setImageResource(R.drawable.a06n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("800")) {
                    descriptionTextView.setText("Чисте небо"); //Ставимо опис погоди
                    if(icon.equals("c01d")) {
                        ImagePogoda.setImageResource(R.drawable.c01d); //Ставимо картинку погоди
                    }
                    if(icon.equals("c01n")) {
                        ImagePogoda.setImageResource(R.drawable.c01n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("801")) {
                    descriptionTextView.setText("Кілька хмар"); //Ставимо опис погоди
                    if(icon.equals("c02d")) {
                        ImagePogoda.setImageResource(R.drawable.c02d); //Ставимо картинку погоди
                    }
                    if(icon.equals("c02n")) {
                        ImagePogoda.setImageResource(R.drawable.c02n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("802")) {
                    descriptionTextView.setText("Розсіяні хмари"); //Ставимо опис погоди
                    if(icon.equals("c02d")) {
                        ImagePogoda.setImageResource(R.drawable.c02d); //Ставимо картинку погоди
                    }
                    if(icon.equals("c02n")) {
                        ImagePogoda.setImageResource(R.drawable.c02n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("803")) {
                    descriptionTextView.setText("Розірвані хмари"); //Ставимо опис погоди
                    if(icon.equals("c03d")) {
                        ImagePogoda.setImageResource(R.drawable.c03d); //Ставимо картинку погоди
                    }
                    if(icon.equals("c03n")) {
                        ImagePogoda.setImageResource(R.drawable.c03n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("804")) {
                    descriptionTextView.setText("Похмурі хмари"); //Ставимо опис погоди
                    if(icon.equals("c04d")) {
                        ImagePogoda.setImageResource(R.drawable.c04d); //Ставимо картинку погоди
                    }
                    if(icon.equals("c04n")) {
                        ImagePogoda.setImageResource(R.drawable.c04n); //Ставимо картинку погоди
                    }
                }
                if(code.equals("900")) {
                    descriptionTextView.setText("Невідомі опади"); //Ставимо опис погоди
                    if(icon.equals("u00d")) {
                        ImagePogoda.setImageResource(R.drawable.u00d); //Ставимо картинку погоди
                    }
                    if(icon.equals("u00n")) {
                        ImagePogoda.setImageResource(R.drawable.u00n); //Ставимо картинку погоди
                    }
                }
                // тепер заносимо всі ці змінні про погоду в базу даних
                cityWeather cityWeathers = new cityWeather();
                cityWeathers.cityid = city1.cityid;
                cityWeathers.description = weather.getString("description");
                cityWeathers.windSpeed = Integer.toString((int)speedy);
                cityWeathers.humidity = find.getString("rh");
                cityWeathers.percip = Integer.toString((int)pressing);
                cityWeathers.windDir = find.getString("wind_dir");
                cityWeathers.recordDate =  new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                cityWeathers.temperature = find.getString("temp")+" C";

                db.CityDAO().insert(cityWeathers);





               




            // виключення, якщо помилка
            } catch (JSONException e) {
                Toast.makeText(this, "Щось пішло не так, будь-ласка спробуйте ще раз та перевірте назву міста!", Toast.LENGTH_SHORT).show();

            }
        }, error -> {

        });

        Volley.newRequestQueue(this).add(request);

    }
    // можливість повернутися до попереднього класу
    public void backtoscreen1(View view) {
        Intent intent = new Intent(this,Screen1.class);
        startActivity(intent);
        finish();
    }

   // можливість повернутися до класу історії
    public void movetohistory(View view) {
        Intent intent = new Intent(this,WeatherHistoryActivity.class);
        startActivity(intent);

    }





}