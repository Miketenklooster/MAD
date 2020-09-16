package com.example.clickerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvCounter;
    TextView plusCounter;
    Button btnIncrement;
    Button btnPlusClick;
    int counter = 0;
    int plusClick = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // de saved data van de counter wordt opgevangen en terug op counter gezet(als er geen gesavde data is = counter 0)
        counter = Integer.valueOf(this.getSharedPreferences("highScore", Context.MODE_PRIVATE).getString("highScore", "0"));

        plusCounter = findViewById(R.id.plusCounter);
        tvCounter = findViewById(R.id.tvCounter);
        btnIncrement = findViewById(R.id.btnIncrement);
        btnPlusClick = findViewById(R.id.btnPlusClick);

        //set de counter om naar text zodat het gediscplayed kan worden
        tvCounter.setText(String.valueOf(counter));
        plusCounter.setText(String.valueOf(plusClick));

        btnPlusClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plusClick = plusClick + 1;
                plusCounter.setText(String.valueOf(plusClick));
            }
        });

        //bij elke click op de button wordt dit uitgevoerd
        btnIncrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter = counter + plusClick;
                tvCounter.setText(String.valueOf(counter));
            }
        });
    }
    //hier wordt de counter opgeslagen als "highScore"
    protected void onStop(){
        this.getSharedPreferences("highScore", Context.MODE_PRIVATE).edit().putString("highScore", String.valueOf(counter)).apply();

        super.onStop();
    }

}
