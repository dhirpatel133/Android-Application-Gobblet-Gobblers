package com.example.pateldhirgobbletgobbler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
    }

    //When the "Play Game" button is pressed, the screen is changed to the
    //game screen.
    public void togamescreen(View view) {
        //Instead of opening up a new screen/activity, the "finish()" method ends
        //the current instructions screen. This way, the placement of the pieces on the
        //game grid is saved and not not lost.
        finish();
    }
}