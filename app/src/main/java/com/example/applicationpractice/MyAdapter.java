package com.example.applicationpractice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Item> items;

    public MyAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.weatherCondition.setText(items.get(position).getWeatherCondition());
        holder.temperature.setText(items.get(position).getTemperature());
        holder.date.setText(items.get(position).getDate());
        Picasso.get().load("https:".concat(items.get(position).getImage())).into(holder.imageView);



    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
