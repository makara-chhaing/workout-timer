package com.example.workouttimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_play, btn_pause, btn_stop;
    EditText et_workout_type;
    TextView tv_record;
    public static final String MY_PREFS = "MyPrefs";
    String workout_type, time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_play = findViewById(R.id.btn_play_id);
        btn_pause = findViewById(R.id.btn_pause_id);
        btn_stop = findViewById(R.id.btn_stop_id);
        et_workout_type = findViewById(R.id.et_type_id);
        tv_record = findViewById(R.id.tv_timer_id);

        SharedPreferences preferences = this.getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        workout_type = preferences.getString("type", "undefined");
        time = preferences.getString("time", "undefined");

        Timer timer = new Timer();


        // Done: designing ui both portrait and landscape, it needs to change back ground to green or highlight when one is chosen.
        // For Java class, only find view by id and create sharePref

        // To Do: display timer, timer starts, pause, and stop correctly
        // implement functions for each button
        // save the reference for both timer and type of exercise for later.

    }
}