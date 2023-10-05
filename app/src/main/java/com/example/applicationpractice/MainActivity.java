package com.example.applicationpractice;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private final String url = "https://api.weatherapi.com/v1/forecast.json?key=";
    private final String api = "2a31497048f048dfa2a30204230206";
    String tempCity = "";

    TextView Ecity;
    TextView Weather;

    TextView temp;
    EditText city;

    ImageButton button;
    TextView db;
    ImageView backGround;
    List<Item> items = new ArrayList<Item>();
    RecyclerView recyclerView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Ecity = findViewById(R.id.CityName);
        city = findViewById(R.id.SearchCity);
        temp = findViewById(R.id.temperature);
        button = findViewById(R.id.imageButton);
        db = findViewById(R.id.debug);
        Weather = findViewById(R.id.WeatherCondition);
        backGround = findViewById(R.id.background);
        recyclerView = findViewById(R.id.scroll);

//        how to add to the lists
//        items.add(new Item("monday", "sunny", "60F", R.drawable.img));




    }

    private void getWeatherInfo(String cityName)
    {

        String tempUrl ="";
        Ecity.setText(city.getText());

//        if(city.equals(""))
//        {
//            tvResult.setText("search bar cannot be empty!");
//
//        }




    }

        public void update(View view) {
        if(!(tempCity.equals(city.getText().toString())))
        {
            items.clear();
        }
        String urlCall = url + api + "&q=" + city.getText().toString() + "&days=3&aqi=no&alerts=no";
        String tempUrl = "https://api.weatherapi.com/v1/forecast.json?key=2a31497048f048dfa2a30204230206&q=London&days=3&aqi=no&alerts=no";






        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlCall, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try{
                    String temperature = response.getJSONObject("current").getString("temp_f");
                    String weatherCond = response.getJSONObject("current").getJSONObject("condition").getString("text");
                    String country = response.getJSONObject("location").getString("country");
//                    String date = response.getJSONObject("current").getString("last_updated");
                    String icon = response.getJSONObject("current").getJSONObject("condition").getString("icon");
//                    String dateFormat = date.substring(0,11);
//                    JSONObject isDay = response.getJSONObject("current");
                    String isDay = response.getJSONObject("current").getString("is_day");


                    //everything works when called except for forecast
                    //1:08:11 in video
                    //weather api only goes until 3 days so I will just do that and make it a button so that it will show hourly temp log
                    //gotta fix background
                    //gotta learn how to read url and convert to image


//                    String a = response.getJSONObject("forecast").getJSONArray("forecastday").getJSONObject(0).getString("date");

                    JSONObject forecastObj = response.getJSONObject("forecast");
                    JSONArray dateArray = forecastObj.getJSONArray("forecastday");



                    temp.setText(temperature + "\u2109");
                    Ecity.setText(city.getText() + ", " + country);
                    Weather.setText(weatherCond);

                    for(int i = 0; i < dateArray.length(); i++)
                    {
                        String dateForcast = dateArray.getJSONObject(i).getString("date");
                        String tempForcast = dateArray.getJSONObject(i).getJSONObject("day").getString("avgtemp_f");
                        String weatherCondForcast = dateArray.getJSONObject(i).getJSONObject("day").getJSONObject("condition").getString("text");
                        String image = dateArray.getJSONObject(i).getJSONObject("day").getJSONObject("condition").getString("icon");
//                        Picasso.get().load("http".concat(image)).into(Icon);

                        if(items.size() < 3)
                        {
                            items.add(new Item(dateForcast, weatherCondForcast, tempForcast+ "\u2109",  image));


                        }
                                    db.setText(image);





                    }


                    //715
                    if(isDay.equals("1"))
                    {
                        if(weatherCond.equals("Sunny"))
                        {
                            //685?
                            backGround.setImageDrawable(getDrawable(R.drawable.sunny_715));


                        }
                        else if(weatherCond.equals("Partly cloudy"))
                        {
                            backGround.setImageDrawable(getDrawable(R.drawable.cloudy_715));

                        }
                        else{
                            backGround.setImageDrawable(getDrawable(R.drawable.card_back));
                        }

                    } else if (isDay.equals("0")) {
                        backGround.setImageDrawable(getDrawable(R.drawable.night_715));

                    }




                }
                catch(Exception e)
                {
                    e.printStackTrace();

                }


            }
        }
        , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Nuh uh!", Toast.LENGTH_SHORT).show();
                db.setText(error.toString());

            }
        });



            requestQueue.add(jsonObjectRequest);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(new MyAdapter(getApplicationContext(),items));
            tempCity = city.getText().toString();



    }

//     how to get text input and set the text
//    public void update(View view) {
//
//        Ecity.setText(city.getText());
//
//
//    }


//public void openHourlyActivity()
//{
//
//        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
//        startActivity(intent);
//
//}


}