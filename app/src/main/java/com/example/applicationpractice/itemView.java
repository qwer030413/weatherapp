package com.example.applicationpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class itemView extends AppCompatActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_view);
        MainActivity a = new MainActivity();

    }

//    public void openHourlyActivity()
//    {
//        Intent intent = new Intent(MainActivity.class, MainActivity2.class);
//        startActivity(intent);
//
//    }
}