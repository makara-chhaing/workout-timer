package com.example.workouttimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.sql.Time;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_play, btn_pause, btn_stop;
    EditText et_workout_type;
    TextView tv_previous;
    public static final String MY_PREFS = "MyPrefs";
    String workout_type, time;
    long pauseDuration;
    Chronometer chronometer;
    Boolean isRunning = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_play = findViewById(R.id.btn_play_id);
        btn_pause = findViewById(R.id.btn_pause_id);
        btn_stop = findViewById(R.id.btn_stop_id);
        et_workout_type = findViewById(R.id.et_type_id);
        chronometer = findViewById(R.id.cm_timer_id);
        tv_previous = findViewById(R.id.tv_previous_id);

        SharedPreferences preferences = this.getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        workout_type = preferences.getString("type", "undefined");
        time = preferences.getString("time", "undefined");


    }

    public void start(View v) {
        if(!isRunning){
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseDuration);
            chronometer.start();
            isRunning = true;
        }
    }
    public void pause(View v) {
        if(isRunning){
            chronometer.stop();
            pauseDuration = SystemClock.elapsedRealtime() - chronometer.getBase();
            isRunning = false;
        }
    }
    public void stop(View v) {
        if(!isRunning){
            chronometer.setBase(SystemClock.elapsedRealtime());
            pauseDuration = 0;
        }
    }
}