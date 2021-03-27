package com.example.hw8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Map;

public class MapActivity extends AppCompatActivity {

    TextView cityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        cityName = findViewById(R.id.cityMapView);
        cityName.setText("Map of " + WeatherDisplay.name);

        //Navbar Start
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_recents:
                        Toast.makeText(MapActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        Intent intentHome = new Intent(MapActivity.this, MainActivity.class);
                        startActivity(intentHome);
                        break;
                    case R.id.action_favorites:
                        Toast.makeText(MapActivity.this, "Weather", Toast.LENGTH_SHORT).show();
                        Intent intentWeather = new Intent(MapActivity.this, WeatherDisplay.class);
                        startActivity(intentWeather);
                        break;
                    case R.id.action_nearby:
                        Toast.makeText(MapActivity.this, "Map", Toast.LENGTH_SHORT).show();
                        Intent intentMap = new Intent(MapActivity.this, MapActivity.class);
                        startActivity(intentMap);
                        break;
                }
                return true;
            }
        });
        //Navbar End

    }
}
