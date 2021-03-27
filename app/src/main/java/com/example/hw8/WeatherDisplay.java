package com.example.hw8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Locale;

public class WeatherDisplay extends AppCompatActivity {
    TextView cityView;
    TextView tempView;
    TextView tempmaxView;
    TextView tempminView;
    TextView descripView;
    TextView humidityView;
    TextView pressureView;
    TextView sunriseView;
    TextView sunsetView;
    TextView lonView;
    TextView latView;
    public static String name;
    public static Double lat;
    public static Double lon;
    //WeatherHistory
    public static int time;

    //Text to Speech start
    TextToSpeech t1;
    Button b1;
    //Text to Speech end

    //WeatherDisplay Start
    String url;
    String key;
    EditText query;
    RequestQueue queue;
    WeatherDisplay context;
    public static JSONObject savedResponse1;
    //WeatherDisplay End

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_display);

        context = this;

        cityView = findViewById(R.id.cityView);
        tempView = findViewById(R.id.tempView);
        tempmaxView = findViewById(R.id.tempmaxView);
        tempminView = findViewById(R.id.tempminView);
        descripView = findViewById(R.id.descripView);
        humidityView = findViewById(R.id.humidityView);
        pressureView = findViewById(R.id.pressureView);
        sunriseView = findViewById(R.id.sunriseView);
        sunsetView = findViewById(R.id.sunsetView);
        lonView = findViewById(R.id.lonView);
        latView = findViewById(R.id.latView);

        //Navbar Start
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_recents:
                        Toast.makeText(WeatherDisplay.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(WeatherDisplay.this, MainActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.action_favorites:
                        Toast.makeText(WeatherDisplay.this, "Weather", Toast.LENGTH_SHORT).show();
                        Intent intentWeather = new Intent(WeatherDisplay.this, WeatherDisplay.class);
                        startActivity(intentWeather);
                        break;
                    case R.id.action_nearby:
                        Toast.makeText(WeatherDisplay.this, "Map", Toast.LENGTH_SHORT).show();
                        Intent intentMap = new Intent(WeatherDisplay.this, MapActivity.class);
                        startActivity(intentMap);
                        break;
                }
                return true;
            }
        });
        //Navbar End

        //static member savedResponse is obtained from MainActivity
        JSONObject weatherdata = MainActivity.savedResponse;
        JSONObject coord = null;
        JSONObject main= null;
        JSONArray weather = null;
        JSONObject sys = null;
        JSONObject innerWeather = null;

        try {
            System.out.println(weatherdata.toString());
            coord = weatherdata.getJSONObject("coord");
            main = weatherdata.getJSONObject("main");
            weather = weatherdata.getJSONArray("weather");
            innerWeather = weather.getJSONObject(0);
            sys = weatherdata.getJSONObject("sys");
        }
        catch (JSONException e){
            System.out.println("Something went wrong with parsing our JSON!");
        }

        if(coord != null && main != null && weather != null && sys != null){
            try {
                cityView.setText("Weather for " + weatherdata.getString("name"));
                tempView.setText(main.getString("temp")+"\u00B0F");
                tempmaxView.setText("Max temp:   " + main.getString("temp_max")+"\u00B0F");
                tempminView.setText("Min temp:   " + main.getString("temp_min")+"\u00B0F");
                descripView.setText(innerWeather.getString("description"));
                humidityView.setText("Humidity:   " + main.getString("humidity") + "%");
                pressureView.setText("Pressure:   " + main.getString("pressure") + " hPa");
                name = weatherdata.getString("name");
                Date sunset = new Date(sys.getLong("sunset") * 1000);
                Date sunrise = new Date(sys.getLong("sunrise") * 1000);
                sunsetView.setText("Sunset:   " + sunset.toString());
                sunriseView.setText("Sunrise:   " + sunrise.toString());
                lonView.setText("Longitude:   " + coord.getString("lon"));
                latView.setText("Latitude:   " + coord.getString("lat"));

                lat = coord.getDouble("lat");
                lon = coord.getDouble("lon");
                time = weatherdata.getInt("dt");

                System.out.println("Time is: " + weatherdata.getDouble("dt"));
            }
            catch (JSONException e){
                System.out.println("Something went wrong with parsing our JSON! Again!");
            }

        }
        //Text to Speech Start
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
        //Text to Speech End

    }


    public void onHistBtnClick(View v){
        //String q = query.getText().toString();
        //sendHistRequest(q);
        sendHistRequest();
    }

    public void sendHistRequest(){ // public void sendHistRequest(String q)
        if(queue == null){
            queue = Volley.newRequestQueue(this);
        }

        url = (String)getString(R.string.WEATHER_HIST_URL);
        key = (String)getString(R.string.API_KEY);
        String newlat = lat.toString();
        String newlon = lon.toString();
        String newtime = Integer.toString(time);
        System.out.println("The new time is: " + newtime);
        //replace the target areas in the url with what the user entered
        // and the api key (respectively)
        url = url.replace("LAT_REPLACE", newlat);
        url = url.replace("LON_REPLACE", newlon);
        url = url.replace("TIME_REPLACE", newtime);
        url = url.replace("API_KEY_REPLACE", key);


        //print out the first request to ensure the url was properly obtained and modified
        System.out.println("Using history url: " + url);

        //Code copied and pasted (but modified) from Send a simple request tutorial
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                savedResponse1 = response;
                //Intent is an object that provides runtime binding between separate
                //component. Intent is used to start another activity!!
                Intent intent = new Intent(context, WeatherHistory.class);
                startActivity(intent);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Something went wrong with the request!");
            }
        });

        queue.add(jsonObjectRequest);
    }

    public void onTexttoSpeechClick(View v){
        String word0 = cityView.getText().toString();
        String word1 = tempView.getText().toString();
        String word2 = tempmaxView.getText().toString();
        String word3 = tempminView.getText().toString();
        String word4 = descripView.getText().toString();
        String word = word0 + word1 + word2 + word3 + word4;
        System.out.println(word);

        t1.speak(word, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void onTexttoSpeechDetClick(View v){
        String word0 = humidityView.getText().toString();
        String word1 = pressureView.getText().toString();
        String word2 = sunriseView.getText().toString();
        String word3 = sunsetView.getText().toString();
        String word4 = lonView.getText().toString();
        String word5 = latView.getText().toString();
        String word = word0 + word1 + word2 + word3 + word4 + word5;
        System.out.println(word);

        t1.speak(word, TextToSpeech.QUEUE_FLUSH, null);
    }

    //Text to Speech Start
    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
    //Text to Speech End

}
