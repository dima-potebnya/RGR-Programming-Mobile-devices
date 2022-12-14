package com.example.weatherapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import com.example.weatherapp.R;
import com.example.weatherapp.db.WeatherDatabase;
import com.example.weatherapp.db.entities.cityName;
import com.example.weatherapp.db.entities.cityWeather;

import java.util.ArrayList;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {

    // створюємо необхідні значення
    private final ArrayList<cityWeather>  cities;
    Context context;
    private int serialCount =1;
    private WeatherDatabase db;

    // оголошуємо модуль для передачі даних
    public WeatherAdapter(Context context, ArrayList<cityWeather> cities) {
        this.cities = cities;
        this.context = context;
    }


    // функція створення холдеру
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // роздуваємо макет елемента
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_recycler_view, parent, false);
        // встановлюємо розмір перегляду, поля, відступи та параметри макета
        MyViewHolder vh = new MyViewHolder(v); // передаємо перегляд View Holder
        return vh;
    }


    // функція для заповнення холдера значеннями
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //будуємо БД
        db = Room.databaseBuilder(context.getApplicationContext(), WeatherDatabase.class, "student-db").allowMainThreadQueries().build();

        // беремо позицію елемента
        cityWeather city = cities.get(position);
        // беремо по ID
        cityName cityname = db.CityDAO().getCitybyID(city.cityid);
        // вносимо в холдер значення історії по місту
        holder.name.setText("Місто : " +cityname.cityName);
        holder.temperature.setText(city.temperature);
        holder.windSpeed.setText("Вітер: "+city.windSpeed);
        holder.windDir.setText("Кут: "+city.windDir);
        holder.pres.setText("Тиск: " +city.percip);
        holder.date.setText("Дата запису : "+city.recordDate);



        // реалізуємо подію setOnClickListener у поданні елемента.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return cities.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        // ініціюємо перегляд елементів
        TextView name , temperature , windSpeed, windDir, pres , date ;



        public MyViewHolder(View itemView) {
            super(itemView);
            // отримуємо посилання на перегляди елементів
            name= (TextView) itemView.findViewById(R.id.city_name_text_id);
            temperature= (TextView)itemView.findViewById(R.id.temperature_id);
            windSpeed =(TextView)itemView.findViewById(R.id.wind_speed_id);
            windDir = (TextView)itemView.findViewById(R.id.wind_dir_id);
            pres = (TextView)itemView.findViewById(R.id.wind_pres_id);
            date = (TextView)itemView.findViewById(R.id.date_id);

        }
    }
}
