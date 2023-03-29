////////// SIT305 Mobile Application Development
//////// Task: Pass Task 4.1
////// Student Name: Nicolas Andres Tomas
//// Student ID: 221351413
// Date: 29-03-2023
package com.example.workouttimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //defining variables
    EditText workoutDurationInput;
    EditText restDurationInput;
    EditText timeRemaining;
    ProgressBar progressBar;

    //defining timer
    CountDownTimer timer = null;

    //defining count
    int progressCount = 0;
    int buttonCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing defined variables
        workoutDurationInput = findViewById(R.id.workoutDuration);
        restDurationInput= findViewById(R.id.restDuration);
        timeRemaining = findViewById(R.id.timeRemaining);
        progressBar = findViewById(R.id.progressbar);

        //initializing progress bar
        progressBar.setProgress(progressCount);
    }

    public void onClickStart(View view) {
        //storing edit text into string variable
        String input1 = workoutDurationInput.getText().toString();
        String input2 = restDurationInput.getText().toString();

        //if "input1" & "input2" are empty, send toast message
        if (input1.matches("") & input2.matches(""))
        {
            Toast.makeText(MainActivity.this, "Please fill in duration", Toast.LENGTH_SHORT).show();
        }
        //else carry out "onClickStart" logic
        else {
            //convert string into long integer variable
            long millisInput1 = Long.parseLong(input1) * 1000;
            long millisInput2 = Long.parseLong(input2) * 1000;

            //adding "millisInput1" & "millisInput2" as a whole number aka add workoutDurationInput & restDurationInput
            long millisInFuture = millisInput1 + millisInput2;

            //"onClickStart" is counted and incremented by 1 each time button is on clicked (NOTE: this logic is to create an instance without having interferences with another created instance)
            buttonCount++;

            //if equal to 1, instance for timer is created
            if (buttonCount == 1) {
                timer = new CountDownTimer(millisInFuture, 1000) {
                    public void onTick(long millisUntilFinished) {
                        //initializing and displaying minutes and seconds
                        long totalTime = millisUntilFinished / 1000;
                        long minutes = (totalTime % 3600) / 60;
                        long seconds = totalTime % 60;
                        String timeString = String.format("%02d:%02d", minutes, seconds);
                        timeRemaining.setText("Time Remaining: " + timeString);

                        //initializing and displaying progress
                        int intInFuture = (int) millisInFuture;
                        progressCount++;
                        progressBar.setProgress(progressCount * 100 / (intInFuture / 1000));
                    }

                    public void onFinish() {
                        //reset count and progress
                        buttonCount = 0;
                        progressCount = 0;
                        progressBar.setProgress(0);
                        timeRemaining.setText("Time Remaining:");
                    }

                }
                //start timer
                .start();
            }
        }
    }

    public void onClickStop(View view) {
        //storing edit text into string variable
        String input1 = workoutDurationInput.getText().toString();
        String input2 = restDurationInput.getText().toString();

        //if "input1" & "input2" are empty, send toast message
        if (input1.matches("") && input2.matches(""))
        {
            Toast.makeText(MainActivity.this, "Please fill in duration", Toast.LENGTH_SHORT).show();
        }
        //else carry out "onClickStop" logic
        else {
            //reset count
            buttonCount = 0;
            progressCount = 0;

            //stop timer
            timer.cancel();
        }
    }
}
// SIT305 Mobile Application Development
//// Task: Pass Task 4.1
////// Student Name: Nicolas Andres Tomas
//////// Student ID: 221351413
////////// Date: 29-03-2023