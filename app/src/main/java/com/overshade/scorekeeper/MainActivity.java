package com.overshade.scorekeeper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button team1Minus, team1Plus, team2Minus, team2Plus;
    private TextView team1Counter, team2Counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adaptViewToVariables();
        addButtonListeners();
        performSavedInstanceAnalysis(savedInstanceState);
    }

    private void adaptViewToVariables() {
        team1Minus = findViewById(R.id.t1ButtonMinus);
        team1Plus = findViewById(R.id.t1ButtonPlus);
        team1Counter = findViewById(R.id.t1Counter);
        team2Minus = findViewById(R.id.t2ButtonMinus);
        team2Plus = findViewById(R.id.t2ButtonPlus);
        team2Counter = findViewById(R.id.t2Counter);
    }

    private void addButtonListeners() {
        team1Minus.setOnClickListener(e -> {sumToCounter(team1Counter, -1); performAnimation(team1Minus, team1Counter);});
        team1Plus.setOnClickListener(e -> {sumToCounter(team1Counter, 1); performAnimation(team1Plus, team1Counter);});
        team2Minus.setOnClickListener(e -> {sumToCounter(team2Counter, -1); performAnimation(team2Minus, team2Counter);});
        team2Plus.setOnClickListener(e -> {sumToCounter(team2Counter, 1); performAnimation(team2Plus, team2Counter);});

    }

    @SuppressLint("SetTextI18n")
    private void sumToCounter(TextView counter, int value) {
        int count = Integer.parseInt(counter.getText().toString())+value;
        counter.setText(Integer.toString(Math.max(count, 0)));
    }


    public void performAnimation(View button, View counter) {
        new ScaleTransition(button, 1f, 1.1f, 1f, 1.1f, 500).start();
        new ScaleTransition(counter, 1f, 1.5f, 1f, 1.5f, 500).start();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("TEAM1", String.valueOf(team1Counter.getText()));
        outState.putString("TEAM2", String.valueOf(team2Counter.getText()));
    }

    @SuppressLint("SetTextI18n")
    private void performSavedInstanceAnalysis(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            team1Counter.setText(savedInstanceState.getString("TEAM1"));
            team2Counter.setText(savedInstanceState.getString("TEAM2"));
        }
    }

}