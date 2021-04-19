package com.example.workouttimer;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import java.sql.Time;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    ImageButton btn_play, btn_pause, btn_stop;
    EditText et_workout_type;
    TextView tv_previous;
    long pauseDuration = 0, totalTime = -3;
    public static final String MY_PREFS = "MyPrefs";
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Chronometer chronometer;
    Boolean isRunning = false, reset = false;
    String type;
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

        type = et_workout_type.getText().toString();

        if(savedInstanceState != null){
            isRunning = savedInstanceState.getBoolean("isRunning", false);
            if(isRunning) {
                pauseDuration = savedInstanceState.getLong("pause", 0);
                chronometer.setBase(savedInstanceState.getLong("base_time", 0));
                Log.d("Save168", "base time: " + savedInstanceState.getLong("base_time", 0));
                Log.d("Save168", "pause: " + savedInstanceState.getLong("pause", 0));
                Log.d("Save168", "is running: " + savedInstanceState.getBoolean("isRunning", false));
                chronometer.start();
            }
            else {
                pauseDuration = savedInstanceState.getLong("pause", 0);
                chronometer.setBase(SystemClock.elapsedRealtime() - pauseDuration);
            }
        }

        preferences = this.getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        type = preferences.getString("type", "");
        totalTime = preferences.getLong("time", 0);
        String time = String.format("%02d:%02d", totalTime/60, totalTime%60);
        tv_previous.setText("You spent " + time +  " on " + type + " last time");

        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(reset){totalTime = -3; reset = false;}
                totalTime++;
                Log.d("Save168", "total time: " + totalTime);
            }
       });

   }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(isRunning){
            outState.putLong("base_time", chronometer.getBase());
            outState.putLong("pause", pauseDuration);
            outState.putBoolean("isRunning", isRunning);
        }else {
        outState.putLong("pause", pauseDuration);
        outState.putBoolean("isRunning", isRunning);
        }

        editor = this.getSharedPreferences(MY_PREFS, MODE_PRIVATE).edit();
        editor.putString("type" , et_workout_type.getText().toString());
        editor.putLong("time", totalTime);
        editor.apply();

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
            String time = String.format("%02d:%02d", totalTime/60, totalTime%60);
            tv_previous.setText("You spent " + time +  " on " + et_workout_type.getText().toString() + " last time");
            pauseDuration = 0;
            reset = true;

        }
    }
}