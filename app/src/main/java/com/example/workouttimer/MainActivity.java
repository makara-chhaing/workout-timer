package com.example.workouttimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
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
    TextView tv_timer, tv_previous;
    public static final String MY_PREFS = "MyPrefs";
    String workout_type, time;
    long totalSec = 3700, hour, minute, second;
    Boolean isRunning = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_play = findViewById(R.id.btn_play_id);
        btn_pause = findViewById(R.id.btn_pause_id);
        btn_stop = findViewById(R.id.btn_stop_id);
        et_workout_type = findViewById(R.id.et_type_id);
        tv_timer = findViewById(R.id.tv_timer_id);
        tv_previous = findViewById(R.id.tv_previous_id);

        SharedPreferences preferences = this.getSharedPreferences(MY_PREFS, MODE_PRIVATE);
        workout_type = preferences.getString("type", "undefined");
        time = preferences.getString("time", "undefined");

//        Time time = new Time();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                totalSec += 1;
//                hour = (totalSec / 3600) % 24;
//                minute = (totalSec / 60)  % 60;
//                second = totalSec % 60;
//                time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, minute, second );
//                Log.e("timer", time);
//                tv_timer.setText(time);
//                try {
//                    sleep(1000);
//                }catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();


        // Done: designing ui both portrait and landscape, it needs to change back ground to green or highlight when one is chosen.
        // For Java class, only find view by id and create sharePref

        // To Do: display timer, timer starts, pause, and stop correctly
        // implement functions for each button
        // save the reference for both timer and type of exercise for later.

    }

    private void refreshTimer(){
//        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                totalSec += 1;
//                hour = (totalSec / 3600) % 24;
//                minute = (totalSec / 60)  % 60;
//                second = totalSec % 60;
//                time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, minute, second );
//                Log.e("timer", time);
//                tv_timer.setText(time);
//            }
//        }, 1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                totalSec += 1;
                hour = (totalSec / 3600) % 24;
                minute = (totalSec / 60)  % 60;
                second = totalSec % 60;
                time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hour, minute, second );
                Log.e("timer", time);
                tv_timer.setText(time);
                try {
                    sleep(1000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void start(View v){
        isRunning = true;
        Log.e("timer start", "here");
//        refreshTimer();
//        while (isRunning){
//            refreshTimer();
//        }
    }

    public void pause(View v){

    }

    public void stop(View v){

    }
}