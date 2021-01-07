package com.example.pateldhirgobbletgobbler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

// Name: Dhir Patel
// Date: March 3, 2020
// Purpose: The game I am intending to create is Gobblet Gobblers. This game is designed to keep grade 3's
// of Ms. Frizzle's class proactive during their free time.

public class MainActivity extends AppCompatActivity {
    MediaPlayer music;
    int paused;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        music = MediaPlayer.create(MainActivity.this, R.raw.bckgmusic);
        music.start();
        music.setLooping(true);
    }

    //When the "2 Players" button is pressed, the screen is changed to the
    //game screen.
    public void togame(View view) {
        Intent i = new Intent(this, Game.class);
        startActivity(i);
    }

    //When the "1 Player" button is pressed, the screen is changed to the
    //game screen.
    public void togameAI(View view) {
        Intent i = new Intent(this, AIScreen.class);
        startActivity(i);
    }
}