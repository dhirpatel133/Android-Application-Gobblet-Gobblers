package com.example.pateldhirgobbletgobbler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    //Global variables
    //Two variables are globally declared to hold player's preferences of
    //background pictures and game grid pictures
    //Variable for background picture
    int choicebckg = 1;
    //Variable for game grid pictures
    int choicegrid = 1;

    //First onClick method for background picture
    public void choiceone(View view) {
        choicebckg = 1;
    }

    //Second onClick method for background picture
    public void choicetwo(View view) {
        choicebckg = 2;
    }

    //Third onClick method for background picture
    public void choicethree(View view) {
        choicebckg = 3;
    }

    //First onClick method for game grid picture
    public void chestgrid(View view) {
        choicegrid = 1;
    }

    //Second onClick method for game grid picture
    public void tntgrid(View view) {
        choicegrid = 2;
    }

    //Third onClick method for game grid picture
    public void treegrid(View view) {
        choicegrid = 3;
    }

    //OnClick method for the button which enables the player to return back to the game
    //screen with their chosen preferences
    public void BackToGame(View view) {
        //This first FIleOutputStream will save all the background picture choices
        //Save the background picture preferences to choicebckg.txt
        try {
            FileOutputStream out = openFileOutput("choicebckg.txt", Activity.MODE_PRIVATE);
            //Print out which cat
            out.write(choicebckg);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //This second FileOutputStream will save the game grid pictures choices
        //Save the picture preferences to choice.txt
        try {
            FileOutputStream out = openFileOutput("choicegrid.txt", Activity.MODE_PRIVATE);
            //Print out which cat
            out.write(choicegrid);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //This starts a new screen (Takes the player back to the game screen)
        Intent i = new Intent(this, Game.class);
        startActivity(i);
    }
}