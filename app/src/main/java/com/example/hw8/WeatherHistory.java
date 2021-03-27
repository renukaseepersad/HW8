package com.example.hw8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;


public class WeatherHistory extends AppCompatActivity {
    TextView dtL0View;

    Date dailytime;
    String temp;
    String descrip;
    String humidity;
    String pressure;
    String uvi;
    String dewpoint;
    String windspeed;
    Date sunrise;
    Date sunset;

    Date dailytime1;
    String temp1;
    String descrip1;
    String humidity1;
    String pressure1;
    String uvi1;
    String dewpoint1;
    String windspeed1;
    Date sunrise1;
    Date sunset1;

    Date dailytime2;
    String temp2;
    String descrip2;
    String humidity2;
    String pressure2;
    String uvi2;
    String dewpoint2;
    String windspeed2;
    Date sunrise2;
    Date sunset2;

    Date dailytime3;
    String temp3;
    String descrip3;
    String humidity3;
    String pressure3;
    String uvi3;
    String dewpoint3;
    String windspeed3;
    Date sunrise3;
    Date sunset3;

    Date dailytime4;
    String temp4;
    String descrip4;
    String humidity4;
    String pressure4;
    String uvi4;
    String dewpoint4;
    String windspeed4;
    Date sunrise4;
    Date sunset4;

    Date dailytime5;
    String temp5;
    String descrip5;
    String humidity5;
    String pressure5;
    String uvi5;
    String dewpoint5;
    String windspeed5;
    Date sunrise5;
    Date sunset5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_history);

        dtL0View = findViewById(R.id.dtL0View);

        try {

            JSONObject jsonObject = new JSONObject((Map) WeatherDisplay.savedResponse1);

            //Current
            JSONObject current = jsonObject.getJSONObject("current");
            JSONArray inWeather = current.getJSONArray("weather");
            JSONObject innerWeather = inWeather.getJSONObject(0);
            dailytime = new Date(current.getLong("dt") * 1000);
            temp = current.getString("temp");
            descrip = innerWeather.getString("description");
            humidity = current.getString("humidity");
            pressure = current.getString("pressure");
            uvi = current.getString("uvi");
            dewpoint = current.getString("dew_point");
            windspeed = current.getString("wind_speed");
            sunrise = new Date(current.getLong("sunrise") * 1000);
            sunset = new Date(current.getLong("sunset") * 1000);

            JSONArray jsonarray = jsonObject.getJSONArray("daily");
            //Day 1
            JSONObject jsonObject1 = jsonarray.getJSONObject(1);
            JSONArray inWeather1 = jsonObject1.getJSONArray("weather");
            JSONObject innerWeather1 = inWeather1.getJSONObject(0);
            JSONObject tempobj1 = jsonObject1.getJSONObject("temp");
            dailytime1 = new Date (jsonObject1.getLong("dt") * 1000);
            temp1 = tempobj1.getString("day");
            descrip1 = innerWeather1.getString("description");
            humidity1 = jsonObject1.getString("humidity");
            pressure1 = jsonObject1.getString("pressure");
            uvi1 = jsonObject1.getString("uvi");
            dewpoint1 = jsonObject1.getString("dew_point");
            windspeed1 = jsonObject1.getString("wind_speed");
            sunrise1 = new Date(jsonObject1.getLong("sunrise") * 1000);
            sunset1 = new Date(jsonObject1.getLong("sunset") * 1000);

            //Day 2
            JSONObject jsonObject2 = jsonarray.getJSONObject(2);
            JSONArray inWeather2 = jsonObject2.getJSONArray("weather");
            JSONObject innerWeather2 = inWeather2.getJSONObject(0);
            JSONObject tempobj2 = jsonObject2.getJSONObject("temp");
            dailytime2 = new Date (jsonObject2.getLong("dt") * 1000);
            temp2 = tempobj2.getString("day");
            descrip2 = innerWeather2.getString("description");
            humidity2 = jsonObject2.getString("humidity");
            pressure2 = jsonObject2.getString("pressure");
            uvi2 = jsonObject2.getString("uvi");
            dewpoint2 = jsonObject2.getString("dew_point");
            windspeed2 = jsonObject2.getString("wind_speed");
            sunrise2 = new Date(jsonObject2.getLong("sunrise") * 1000);
            sunset2 = new Date(jsonObject2.getLong("sunset") * 1000);

            //Day 3
            JSONObject jsonObject3 = jsonarray.getJSONObject(3);
            JSONArray inWeather3 = jsonObject3.getJSONArray("weather");
            JSONObject innerWeather3 = inWeather3.getJSONObject(0);
            JSONObject tempobj3 = jsonObject3.getJSONObject("temp");
            dailytime3 = new Date (jsonObject3.getLong("dt") * 1000);
            temp3 = tempobj3.getString("day");
            descrip3 = innerWeather3.getString("description");
            humidity3 = jsonObject3.getString("humidity");
            pressure3 = jsonObject3.getString("pressure");
            uvi3 = jsonObject3.getString("uvi");
            dewpoint3 = jsonObject3.getString("dew_point");
            windspeed3 = jsonObject3.getString("wind_speed");
            sunrise3 = new Date(jsonObject3.getLong("sunrise") * 1000);
            sunset3 = new Date(jsonObject3.getLong("sunset") * 1000);

            //Day 4
            JSONObject jsonObject4 = jsonarray.getJSONObject(4);
            JSONArray inWeather4 = jsonObject4.getJSONArray("weather");
            JSONObject innerWeather4 = inWeather4.getJSONObject(0);
            JSONObject tempobj4 = jsonObject4.getJSONObject("temp");
            dailytime4 = new Date (jsonObject4.getLong("dt") * 1000);
            temp4 = tempobj4.getString("day");
            descrip4 = innerWeather4.getString("description");
            humidity4 = jsonObject4.getString("humidity");
            pressure4 = jsonObject4.getString("pressure");
            uvi4 = jsonObject4.getString("uvi");
            dewpoint4 = jsonObject4.getString("dew_point");
            windspeed4 = jsonObject4.getString("wind_speed");
            sunrise4 = new Date(jsonObject4.getLong("sunrise") * 1000);
            sunset4 = new Date(jsonObject4.getLong("sunset") * 1000);

            //Day 5
            JSONObject jsonObject5 = jsonarray.getJSONObject(5);
            JSONArray inWeather5 = jsonObject5.getJSONArray("weather");
            JSONObject innerWeather5 = inWeather5.getJSONObject(0);
            JSONObject tempobj5 = jsonObject5.getJSONObject("temp");
            dailytime5 = new Date (jsonObject5.getLong("dt") * 1000);
            temp5 = tempobj5.getString("day");
            descrip5 = innerWeather5.getString("description");
            humidity5 = jsonObject5.getString("humidity");
            pressure5 = jsonObject5.getString("pressure");
            uvi5 = jsonObject5.getString("uvi");
            dewpoint5 = jsonObject5.getString("dew_point");
            windspeed5 = jsonObject5.getString("wind_speed");
            sunrise5 = new Date(jsonObject5.getLong("sunrise") * 1000);
            sunset5 = new Date(jsonObject5.getLong("sunset") * 1000);

            //System.out.println("inner hour is " + innerHour);
        } catch (JSONException e) {
            System.out.println("WH 103 NOO. Something went wrong with parsing our JSON!");
            System.out.println("WH 104 Error" + e.toString());
        }

        dtL0View.setText("Weather Forecast for " + WeatherDisplay.name);

        ListView list = findViewById(R.id.simpleListView);
        ArrayList<String> listOne = new ArrayList<>(Arrays.asList(("Date :   " + dailytime.toString() + "\n" +
                "Temperature :   " + temp + "\u00B0F" + "\n" +
                "Description :   " + descrip + "\n" +
                "Humidity :   " + humidity + "%\n" +
                "Pressure :   " + pressure + " hPa\n" +
                "UVI :   " + uvi + "\n" +
                "Dew Point :   " + dewpoint + "\u00B0F" +"\n" +
                "Wind Speed :   " + windspeed + " mph\n" +
                "Sunrise :   " + sunrise.toString() + "\n" +
                "Sunset :   " + sunset.toString() + "\n"
        )));

        ArrayList<String> listTwo = new ArrayList<>(Arrays.asList(("Date :   " + dailytime1.toString() + "\n" +
                "Temperature :   " + temp1 + "\u00B0F" + "\n" +
                "Description :   " + descrip1 + "\n" +
                "Humidity :   " + humidity1 + "%\n" +
                "Pressure :   " + pressure1 + " hPa\n" +
                "UVI :   " + uvi1 + "\n" +
                "Dew Point :   " + dewpoint1 + "\u00B0F" + "\n" +
                "Wind Speed :   " + windspeed1 + " mph\n" +
                "Sunrise :   " + sunrise1.toString() + "\n" +
                "Sunset :   " + sunset1.toString() + "\n"
        )));
        listOne.addAll(listTwo);

        ArrayList<String> listThree = new ArrayList<>(Arrays.asList(("Date :   " + dailytime2.toString() + "\n" +
                "Temperature :   " + temp2 + "\u00B0F" + "\n" +
                "Description :   " + descrip2 + "\n" +
                "Humidity :   " + humidity2 + "%\n" +
                "Pressure :   " + pressure2 + " hPa\n" +
                "UVI :   " + uvi2 + "\n" +
                "Dew Point :   " + dewpoint2 + "\u00B0F" + "\n" +
                "Wind Speed :   " + windspeed2 + " mph\n" +
                "Sunrise :   " + sunrise2.toString() + "\n" +
                "Sunset :   " + sunset2.toString() + "\n"
        )));
        listOne.addAll(listThree);

        ArrayList<String> listFour = new ArrayList<>(Arrays.asList(("Date :   " + dailytime3.toString() + "\n" +
                "Temperature :   " + temp3 + "\u00B0F" + "\n" +
                "Description :   " + descrip3 + "\n" +
                "Humidity :   " + humidity3 + "%\n" +
                "Pressure :   " + pressure3 + " hPa\n" +
                "UVI :   " + uvi3 + "\n" +
                "Dew Point :   " + dewpoint3 + "\u00B0F" + "\n" +
                "Wind Speed :   " + windspeed3 + " mph\n" +
                "Sunrise :   " + sunrise3.toString() + "\n" +
                "Sunset :   " + sunset3.toString() + "\n"
        )));
        listOne.addAll(listFour);

        ArrayList<String> listFive = new ArrayList<>(Arrays.asList(("Date :   " + dailytime4.toString() + "\n" +
                "Temperature :   " + temp4 + "\u00B0F" + "\n" +
                "Description :   " + descrip4 + "\n" +
                "Humidity :   " + humidity4 + "%\n" +
                "Pressure :   " + pressure4 + " hPa\n" +
                "UVI :   " + uvi4 + "\n" +
                "Dew Point :   " + dewpoint4 + "\u00B0F" + "\n" +
                "Wind Speed :   " + windspeed4 + " mph\n" +
                "Sunrise :   " + sunrise4.toString() + "\n" +
                "Sunset :   " + sunset4.toString() + "\n"
        )));
        listOne.addAll(listFive);

        ArrayList<String> listSix = new ArrayList<>(Arrays.asList(("Date :   " + dailytime5.toString() + "\n" +
                "Temperature :   " + temp5 + "\u00B0F" + "\n" +
                "Description :   " + descrip5 + "\n" +
                "Humidity :   " + humidity5 + "%\n" +
                "Pressure :   " + pressure5 + " hPa\n" +
                "UVI :   " + uvi5 + "\n" +
                "Dew Point :   " + dewpoint5 + "\u00B0F" + "\n" +
                "Wind Speed :   " + windspeed5 + " mph\n" +
                "Sunrise :   " + sunrise5.toString() + "\n" +
                "Sunset :   " + sunset5.toString() + "\n"
        )));
        listOne.addAll(listSix);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(WeatherHistory.this, R.layout.list_items, listOne);
        list.setAdapter(arrayAdapter);

        //Navbar Start
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_recents:
                        Toast.makeText(WeatherHistory.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(WeatherHistory.this, MainActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.action_favorites:
                        Toast.makeText(WeatherHistory.this, "Weather", Toast.LENGTH_SHORT).show();
                        Intent intentWeather = new Intent(WeatherHistory.this, WeatherDisplay.class);
                        startActivity(intentWeather);
                        break;
                    case R.id.action_nearby:
                        Toast.makeText(WeatherHistory.this, "Map", Toast.LENGTH_SHORT).show();
                        Intent intentMap = new Intent(WeatherHistory.this, MapActivity.class);
                        startActivity(intentMap);
                        break;
                }
                return true;
            }

        });
        //Navbar End
    }
}
