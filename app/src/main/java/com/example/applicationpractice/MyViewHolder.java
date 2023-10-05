package com.example.applicationpractice;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView temperature;
    TextView weatherCondition;
    TextView date;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imageView);
        temperature = itemView.findViewById(R.id.temp);
        weatherCondition = itemView.findViewById(R.id.weatherCond);
        date = itemView.findViewById(R.id.date);
    }
}
