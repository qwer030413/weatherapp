package com.example.applicationpractice;

import android.widget.ImageView;

public class Item {
    String date;
    String image;
    String weatherCondition;
    String temperature;

    public Item(String date, String weatherCondition, String temperature, String image )
    {
        this.date = date;
        this.weatherCondition = weatherCondition;
        this.temperature = temperature;
        this.image = image;



    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public String getTemperature() {
        return temperature;
    }
}
