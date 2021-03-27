package com.example.hw8;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    Button gpsButton;
    EditText query;
    RequestQueue queue;
    public static JSONObject savedResponse;
    MainActivity context;
    String url;
    String key;


    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    FusedLocationProviderClient fused;

    private final int REQ_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        goButton = findViewById(R.id.goButton);
        gpsButton = findViewById(R.id.locButton);
        query = findViewById(R.id.query);

        Button speak = findViewById(R.id.speak);
        speak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Need to speak");
                try {
                    startActivityForResult(intent, REQ_CODE);
                } catch (ActivityNotFoundException a) {
                    Toast.makeText(getApplicationContext(),
                            "Sorry your device not supported",
                            Toast.LENGTH_SHORT).show();
                    System.out.println("new commit worked!");
                }
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //query.setText(String.valueOf(result));
                    query.setText(String.valueOf(result.get(0)));
                }
                break;
            }
        }
    }


    public void onGoClick(View v){
        String q = query.getText().toString();
        sendRequest(q);
    }

    public void onGPSClick(View v){
        if (ContextCompat.checkSelfPermission(this, mPermission)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{mPermission},
                    REQUEST_CODE_PERMISSION);
        }
        fused = LocationServices.getFusedLocationProviderClient(this);

        fused.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        //Got last known location. In rare situations, it may be null
                        if(location != null){
                            sendRequestGPS(location.getLatitude(), location.getLongitude());
                        }
                        else {
                            System.out.println("No GPS Data");
                        }
                    }
                });
    }

    public void sendRequestGPS(Double lat, Double lon){
        //checking to see if queue is already instantiated
        //if it is not, instantiate it
        if(queue == null){
            queue = Volley.newRequestQueue(this);
        }
        //otherwise use the queue
        //get and stringify the url and api key from strings.xml
        String url = (String)getString(R.string.WEATHER_URL_GPS);
        String key = (String)getString(R.string.API_KEY);
        //replace the target areas in the url with what the info obtained from location
        url = url.replace("LAT_REPLACE", lat.toString());
        url = url.replace("LON_REPLACE", lon.toString());
        url = url.replace("API_KEY_REPLACE", key);

        //print out the first request to ensure the url was properly obtained and modified
        System.out.println("Using url: " + url);

        //Code copied and pasted (but modified) from Send a simple request tutorial
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                savedResponse = response;
                //Intent is an object that provides runtime binding between separate
                //component. Intent is used to start another activity!!
                Intent intent = new Intent(context, WeatherDisplay.class);
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


    public static boolean isNumeric(String q) {
        try {
            Double.parseDouble(q);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }


    public void sendRequest(String q){
        //checking to see if queue is already instantiated
        //if it is not, instantiate it
        if(queue == null){
            queue = Volley.newRequestQueue(this);
        }


        //otherwise use the queue
        //get and stringify the url and api key from strings.xml
        String url = (String) getString(R.string.WEATHER_URL);
        String key = (String) getString(R.string.API_KEY);
        //replace the target areas in the url with what the user entered
        // and the api key (respectively)
        url = url.replace("QUERY_REPLACE", q);
        url = url.replace("API_KEY_REPLACE", key);

        //print out the first request to ensure the url was properly obtained and modified
        System.out.println("Using url: " + url);

        //Code copied and pasted (but modified) from Send a simple request tutorial
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());
                //Intent is an object that provides runtime binding between separate
                //component. Intent is used to start another activity!!
                Intent intent = new Intent(context, WeatherDisplay.class);
                startActivity(intent);
                savedResponse = response;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Something went wrong with the request!");
            }
        });

        queue.add(jsonObjectRequest);


    }


}