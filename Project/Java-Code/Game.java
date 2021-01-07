package com.example.pateldhirgobbletgobbler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Game extends AppCompatActivity {
    //Global Variables
    //Initializing the 2-D int Array
    int board[][] = new int[3][3];
    //Initializing the "turn" variable(global variable)
    int turn = 1;
    //Initializing a variable which will store the player's choices
    int pick = 0;
    //Initializing two variables to handle the players personal setting options (background picture and grid pictures)
    int choicebckg = 1;
    int choicegrid = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //Setting up the "Rate Us" button to set up a rating bar
        //When the "rate Us" button is pressed on the game screen, a pop up dialog box will
        //appear with a rating bar on it
        final Button rate = (Button) findViewById(R.id.rate);
        rate.setOnClickListener(new View.OnClickListener() {
            //When the button is clicked, a dialog box is shown
            //The code for this can be found in the "ShowDialog()" method
            public void onClick(View v) {
                ShowDialog();
            }
        });
        //This takes cares of customizing the game screen according to the selection of choices in the
        //settings screen
        //Reading in the background picture preferences from a different screen
        try {
            FileInputStream in = openFileInput("choicebckg.txt");
            choicebckg = in.read();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Reading in the grid picture preferences from a different screen
        try {
            FileInputStream in = openFileInput("choicegrid.txt");
            choicegrid = in.read();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Update screen accordingly to preferences selected in the "Settings Screen"
        ImageView picture = (ImageView) findViewById(R.id.picture);
        //This if handles and updates the background picture
        if (choicebckg == 1)
            picture.setImageResource(R.drawable.gbackground);
        else if (choicebckg == 2)
            picture.setImageResource(R.drawable.bckgone);
        else if (choicebckg == 3)
            picture.setImageResource(R.drawable.instrbckg);
        //This if handles and updates the grid picture
        if (choicegrid == 1)
            ChestImage();
        else if (choicegrid == 2)
            TntImage();
        else if (choicegrid == 3)
            TreeImage();
    }

    //When the "Instructions" button is pressed, the screen is changed to the
    //instruction screen.
    public void toinstructions(View view) {
        Intent i = new Intent(this, Instructions.class);
        startActivity(i);
    }

    //When the "Settings" icon is pressed, the screen is changed to the
    //settings screen.
    public void tosettings(View view) {
        Intent i = new Intent(this, Settings.class);
        startActivity(i);
    }

    //When the "back" icon is pressed, the screen is changed to the
    //splash/home screen.
    public void home(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    //This method is called in the onCreate to produce a rating bar in a pop up dialog box
    //when the "Rate Us" button is pressed.
    public void ShowDialog() {
        //Creating the alert dialog box
        final AlertDialog.Builder RateDialog = new AlertDialog.Builder(this);
        //Creating a new rating bar which will be placed inside the dialog box
        final RatingBar rating = new RatingBar(this);
        //Setting the number of stars for rating
        rating.setNumStars(5);
        //Setting up how much the player can go up by on the ratings
        rating.setStepSize((float) 0.5);
        //Creating a linearlayout to hold the rating bar
        LinearLayout layout = new LinearLayout(this);
        //Setting width and height constraints
        LinearLayout.LayoutParams parameters = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //Adding the constraints created above to the linear layout
        layout.setLayoutParams(parameters);
        //Setting up the orientation of the linearlayout
        layout.setGravity(Gravity.CENTER_HORIZONTAL);
        //Adding the rating bar to the linear layout
        layout.addView(rating);
        //Setting up an icon for the pop up dialog box
        RateDialog.setIcon(android.R.drawable.btn_star_big_on);
        //Adding a title to the pop up dialog box
        RateDialog.setTitle("Your feedback matters. Rate us!");
        //Adding the linearlayout which contains the rating bar to the dialog box
        RateDialog.setView(layout);
        // A toast appears once the player has rated the game and clicks "Ok"
        RateDialog.setPositiveButton("ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //If the rating is 0.5 or 1, a certain message appears
                        if (rating.getRating() == 0.5 || rating.getRating() == 1) {
                            Toast.makeText(getApplicationContext(), "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
                        }
                        //If the rating is 1.5 or 2 or 2.5, a certain message appears
                        else if (rating.getRating() == 1.5 || rating.getRating() == 2 || rating.getRating() == 2.5) {
                            Toast.makeText(getApplicationContext(), "Hope you enjoyed the game!", Toast.LENGTH_SHORT).show();
                        }
                        //If the rating is 3 or 3.5, a certain message appears
                        else if (rating.getRating() == 3 || rating.getRating() == 3.5) {
                            Toast.makeText(getApplicationContext(), "We are glad you liked the game!", Toast.LENGTH_SHORT).show();
                        }
                        //If the rating is 4 then the message: "Wow! Thank you for giving us a 4!" appears
                        else if (rating.getRating() == 4) {
                            Toast.makeText(getApplicationContext(), "Wow! Thank you for giving us a " + (rating.getRating()) + "!", Toast.LENGTH_SHORT).show();
                        }
                        //If the rating is 4 then the message: "Wow! Thank you for giving us a 5!" appears
                        else if (rating.getRating() == 5) {
                            Toast.makeText(getApplicationContext(), "Wow! Thank you for giving us a " + (rating.getRating()) + "!", Toast.LENGTH_SHORT).show();
                        }
                        //If the rating is anything else, (specifically 4.5) then a certain message appears
                        else {
                            Toast.makeText(getApplicationContext(), "Wow! Thank you for leaving an excellent feedback!", Toast.LENGTH_SHORT).show();
                        }
                        //After the "Ok" button is pressed, the dialog box is dismissed
                        dialog.dismiss();
                    }
                });
        //If the player doesn't rate the game and clicks "Cancel" then nothing happens.
        //The pop up dialog box is dismissed
        RateDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        //Dialog box is created
        RateDialog.create();
        //Dialog box is shown on the screen
        RateDialog.show();
    }

    //This method is created to set all the grid pictures to "chest icon"
    //This method is then called in the onCreate method to update the game screen
    //to the player's preferences.
    public void ChestImage() {
        ImageView a = (ImageView) findViewById(R.id.a);
        a.setImageResource(R.drawable.chest);
        ImageView b = (ImageView) findViewById(R.id.b);
        b.setImageResource(R.drawable.chest);
        ImageView c = (ImageView) findViewById(R.id.c);
        c.setImageResource(R.drawable.chest);
        ImageView d = (ImageView) findViewById(R.id.d);
        d.setImageResource(R.drawable.chest);
        ImageView e = (ImageView) findViewById(R.id.e);
        e.setImageResource(R.drawable.chest);
        ImageView f = (ImageView) findViewById(R.id.f);
        f.setImageResource(R.drawable.chest);
        ImageView g = (ImageView) findViewById(R.id.g);
        g.setImageResource(R.drawable.chest);
        ImageView h = (ImageView) findViewById(R.id.h);
        h.setImageResource(R.drawable.chest);
        ImageView i = (ImageView) findViewById(R.id.i);
        i.setImageResource(R.drawable.chest);
        ImageView d1 = (ImageView) findViewById(R.id.d1);
        d1.setImageResource(R.drawable.dshovel);
        ImageView d2 = (ImageView) findViewById(R.id.d2);
        d2.setImageResource(R.drawable.daxe);
        ImageView d3 = (ImageView) findViewById(R.id.d3);
        d3.setImageResource(R.drawable.dsword);
        ImageView g1 = (ImageView) findViewById(R.id.g1);
        g1.setImageResource(R.drawable.gshovel);
        ImageView g2 = (ImageView) findViewById(R.id.g2);
        g2.setImageResource(R.drawable.gaxe);
        ImageView g3 = (ImageView) findViewById(R.id.g3);
        g3.setImageResource(R.drawable.gsword);
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                board[k][j] = 0;
            }
        }
    }

    //This method is created to set all the grid pictures to "TnT icon"
    //This method is then called in the onCreate method to update the game screen
    //to the player's preferences.
    public void TntImage() {
        ImageView a = (ImageView) findViewById(R.id.a);
        a.setImageResource(R.drawable.tnt);
        ImageView b = (ImageView) findViewById(R.id.b);
        b.setImageResource(R.drawable.tnt);
        ImageView c = (ImageView) findViewById(R.id.c);
        c.setImageResource(R.drawable.tnt);
        ImageView d = (ImageView) findViewById(R.id.d);
        d.setImageResource(R.drawable.tnt);
        ImageView e = (ImageView) findViewById(R.id.e);
        e.setImageResource(R.drawable.tnt);
        ImageView f = (ImageView) findViewById(R.id.f);
        f.setImageResource(R.drawable.tnt);
        ImageView g = (ImageView) findViewById(R.id.g);
        g.setImageResource(R.drawable.tnt);
        ImageView h = (ImageView) findViewById(R.id.h);
        h.setImageResource(R.drawable.tnt);
        ImageView i = (ImageView) findViewById(R.id.i);
        i.setImageResource(R.drawable.tnt);
        ImageView d1 = (ImageView) findViewById(R.id.d1);
        d1.setImageResource(R.drawable.dshovel);
        ImageView d2 = (ImageView) findViewById(R.id.d2);
        d2.setImageResource(R.drawable.daxe);
        ImageView d3 = (ImageView) findViewById(R.id.d3);
        d3.setImageResource(R.drawable.dsword);
        ImageView g1 = (ImageView) findViewById(R.id.g1);
        g1.setImageResource(R.drawable.gshovel);
        ImageView g2 = (ImageView) findViewById(R.id.g2);
        g2.setImageResource(R.drawable.gaxe);
        ImageView g3 = (ImageView) findViewById(R.id.g3);
        g3.setImageResource(R.drawable.gsword);
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                board[k][j] = 0;
            }
        }
    }

    //This method is created to set all the grid pictures to "Tree icon"
    //This method is then called in the onCreate method to update the game screen
    //to the player's preferences.
    public void TreeImage() {
        ImageView a = (ImageView) findViewById(R.id.a);
        a.setImageResource(R.drawable.tree);
        ImageView b = (ImageView) findViewById(R.id.b);
        b.setImageResource(R.drawable.tree);
        ImageView c = (ImageView) findViewById(R.id.c);
        c.setImageResource(R.drawable.tree);
        ImageView d = (ImageView) findViewById(R.id.d);
        d.setImageResource(R.drawable.tree);
        ImageView e = (ImageView) findViewById(R.id.e);
        e.setImageResource(R.drawable.tree);
        ImageView f = (ImageView) findViewById(R.id.f);
        f.setImageResource(R.drawable.tree);
        ImageView g = (ImageView) findViewById(R.id.g);
        g.setImageResource(R.drawable.tree);
        ImageView h = (ImageView) findViewById(R.id.h);
        h.setImageResource(R.drawable.tree);
        ImageView i = (ImageView) findViewById(R.id.i);
        i.setImageResource(R.drawable.tree);
        ImageView d1 = (ImageView) findViewById(R.id.d1);
        d1.setImageResource(R.drawable.dshovel);
        ImageView d2 = (ImageView) findViewById(R.id.d2);
        d2.setImageResource(R.drawable.daxe);
        ImageView d3 = (ImageView) findViewById(R.id.d3);
        d3.setImageResource(R.drawable.dsword);
        ImageView g1 = (ImageView) findViewById(R.id.g1);
        g1.setImageResource(R.drawable.gshovel);
        ImageView g2 = (ImageView) findViewById(R.id.g2);
        g2.setImageResource(R.drawable.gaxe);
        ImageView g3 = (ImageView) findViewById(R.id.g3);
        g3.setImageResource(R.drawable.gsword);
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                board[k][j] = 0;
            }
        }
    }

    //This method will check for the number of each player's pieces on the game board.
    //*Note: As per the rule of the game, there cannot be more than two of the same type
    //of pieces (not related to colour) on the grid.
    //The output of the method is an integer array.
    public int[] click() {
        //6 Variables declared (Each variable corresponds to one of the six buttons on the game screen)
        //The variables are declared locally instead of globally because after each turn, all the 6
        //variables will be set back to 0 and the method will run again to check the numbers of piece
        //on the game board.
        int click1 = 0;
        int click2 = 0;
        int click3 = 0;
        int click4 = 0;
        int click5 = 0;
        int click6 = 0;
        //An empty 1-D integer array is declared
        int store[] = new int[6];
        //Two nested loops used to find each position in the "board" 2-D array.
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                //If board[x][y] holds a 1(diamond shovel) then store[0] becomes click1++.
                //For each diamond shovel on the "board[][]" click1 increases by one.
                if (board[a][b] == 1)
                    store[0] = click1++;
                    //If board[x][y] holds a 2(diamond axe) then store[1] becomes click2++.
                    //For each diamond axe on the "board[][]" click2 increases by one.
                else if (board[a][b] == 3)
                    store[1] = click2++;
                    //If board[x][y] holds a 1(diamond sword) then store[2] becomes click3++.
                    //For each diamond sword on the "board[][]" click3 increases by one.
                else if (board[a][b] == 5)
                    store[2] = click3++;
                    //If board[x][y] holds a 1(gold shovel) then store[3] becomes click4++.
                    //For each gold shovel on the "board[][]" click4 increases by one.
                else if (board[a][b] == 2)
                    store[3] = click4++;
                    //If board[x][y] holds a 1(gold axe) then store[4] becomes click4++.
                    //For each gold axe on the "board[][]" click4 increases by one.
                else if (board[a][b] == 4)
                    store[4] = click5++;
                    //If board[x][y] holds a 1(diamond shovel) then store[0] becomes click1++.
                    //For each diamond shovel on the "board[][]" click1 increases by one.
                else if (board[a][b] == 6)
                    store[5] = click6++;
            }
        }
        //From above, the store[] array is not empty anymore.
        //The values inside the store[] array is returned to the click() method.
        return store;
    }

    //This is the onClick for the small diamond shovel icon at the bottom of the screen
    public void shvl1(View view) {
        ImageView i = (ImageView) findViewById(R.id.d1);
        //The values returned form the click() method is stored in the integer a[] array.
        int a[] = click();
        //If there are less than 2 diamond shovel on the board, then the button will be clicked.
        if (a[0] < 1) {
            if (turn == 1)
                pick = 1;
            i.setImageResource(R.drawable.dshovel);
        }
        //If there are already 2 diamond shovels on the board, the button will no longer be highlighted.
        //A message will also show up.
        else {
            i.setImageResource(R.drawable.ishovel);
            Toast.makeText(getApplicationContext(), "All of these pieces are used. Select another one", Toast.LENGTH_SHORT).show();
        }
        //These "if" statements check during the click if there are less than 2 of the gold player's piece on the board.
        //If there are less than 2 pieces of a type, then that piece is highlighted before it's gold's turn
        //so the gold player can select that piece during their turn.
        //Checks for gold shovel
        if (a[3] < 1) {
            ImageView b = (ImageView) findViewById(R.id.g1);
            b.setImageResource(R.drawable.gshovel);
        }
        //Checks for gold axe
        if (a[4] < 1) {
            ImageView c = (ImageView) findViewById(R.id.g2);
            c.setImageResource(R.drawable.gaxe);
        }
        //Checks for gold sword
        if (a[5] < 1) {
            ImageView d = (ImageView) findViewById(R.id.g3);
            d.setImageResource(R.drawable.gsword);
        }
    }

    //This is the onClick for the small diamond axe icon at the bottom of the screen
    public void axe1(View view) {
        ImageView i = (ImageView) findViewById(R.id.d2);
        //The values returned form the click() method is stored in the integer a[] array.
        int a[] = click();
        //If there are less than 2 diamond axes on the board, then the button will be clicked.
        if (a[1] < 1) {
            if (turn == 1)
                pick = 3;
        }
        //If there are already 2 diamond axes on the board, the button will no longer be highlighted.
        //A message will also show up.
        else {
            i.setImageResource(R.drawable.iaxe);
            Toast.makeText(getApplicationContext(), "All of these pieces are used. Select another one", Toast.LENGTH_SHORT).show();
        }
        //These "if" statements check during the click if there are less than 2 of the gold player's piece on the board.
        //If there are less than 2 pieces of a type, then that piece is highlighted before it's gold's turn
        //so the gold player can select that piece during their turn.
        //Checks for gold shovel
        if (a[3] < 1) {
            ImageView b = (ImageView) findViewById(R.id.g1);
            b.setImageResource(R.drawable.gshovel);
        }
        //Checks for gold axe
        if (a[4] < 1) {
            ImageView c = (ImageView) findViewById(R.id.g2);
            c.setImageResource(R.drawable.gaxe);
        }
        //Checks for gold sword
        if (a[5] < 1) {
            ImageView d = (ImageView) findViewById(R.id.g3);
            d.setImageResource(R.drawable.gsword);
        }
    }

    //This is the onClick for the small diamond sword icon at the bottom of the screen
    public void swrd1(View view) {
        ImageView i = (ImageView) findViewById(R.id.d3);
        //The values returned form the click() method is stored in the integer a[] array.
        int a[] = click();
        //If there are less than 2 diamond swords on the board, then the button will be clicked.
        if (a[2] < 1) {
            if (turn == 1)
                pick = 5;
        }
        //If there are already 2 diamond sword on the board, the button will no longer be highlighted.
        //A message will also show up.
        else {
            i.setImageResource(R.drawable.isword);
            Toast.makeText(getApplicationContext(), "All of these pieces are used. Select another one", Toast.LENGTH_SHORT).show();
        }
        //These "if" statements check during the click if there are less than 2 of the gold player's piece on the board.
        //If there are less than 2 pieces of a type, then that piece is highlighted before it's gold's turn
        //so the gold player can select that piece during their turn.
        //Checks for gold shovel
        if (a[3] < 1) {
            ImageView b = (ImageView) findViewById(R.id.g1);
            b.setImageResource(R.drawable.gshovel);
        }
        //Checks for gold axe
        if (a[4] < 1) {
            ImageView c = (ImageView) findViewById(R.id.g2);
            c.setImageResource(R.drawable.gaxe);
        }
        //Checks for gold sword
        if (a[5] < 1) {
            ImageView d = (ImageView) findViewById(R.id.g3);
            d.setImageResource(R.drawable.gsword);
        }
    }

    //This is the onClick for the small gold shovel icon at the bottom of the screen
    public void shvl2(View view) {
        ImageView i = (ImageView) findViewById(R.id.g1);
        //The values returned form the click() method is stored in the integer a[] array.
        int a[] = click();
        //If there are less than 2 gold shovels on the board, then the button will be clicked.
        if (a[3] < 1) {
            if (turn != 1)
                pick = 2;
        }
        //If there are already 2 gold shovels on the board, the button will no longer be highlighted.
        //A message will also show up.
        else {
            i.setImageResource(R.drawable.ishovel);
            Toast.makeText(getApplicationContext(), "All of these pieces are used. Select another one", Toast.LENGTH_SHORT).show();
        }
        //These "if" statements check during the click if there are less than 2 of the diamond player's piece on the board.
        //If there are less than 2 pieces of a type, then that piece is highlighted before it's diamond's turn
        //so the diamond player can select that piece during their turn.
        //Checks for diamond shovel
        if (a[0] < 1) {
            ImageView b = (ImageView) findViewById(R.id.d1);
            b.setImageResource(R.drawable.dshovel);
        }
        //Checks for diamond axe
        if (a[1] < 1) {
            ImageView b = (ImageView) findViewById(R.id.d2);
            b.setImageResource(R.drawable.daxe);
        }
        //Checks for diamond sword
        if (a[2] < 1) {
            ImageView d = (ImageView) findViewById(R.id.d3);
            d.setImageResource(R.drawable.dsword);
        }
    }

    //This is the onClick for the small gold axe icon at the bottom of the screen
    public void axe2(View view) {
        ImageView i = (ImageView) findViewById(R.id.g2);
        //The values returned form the click() method is stored in the integer a[] array.
        int a[] = click();
        //If there are less than 2 gold axes on the board, then the button will be clicked.
        if (a[4] < 1) {
            if (turn != 1)
                pick = 4;
        }
        //If there are already 2 gold axes on the board, the button will no longer be highlighted.
        //A message will also show up.
        else {
            i.setImageResource(R.drawable.iaxe);
            Toast.makeText(getApplicationContext(), "All of these pieces are used. Select another one", Toast.LENGTH_SHORT).show();
        }
        //These "if" statements check during the click if there are less than 2 of the diamond player's piece on the board.
        //If there are less than 2 pieces of a type, then that piece is highlighted before it's diamond's turn
        //so the diamond player can select that piece during their turn.
        //Checks for diamond shovel
        if (a[0] < 1) {
            ImageView b = (ImageView) findViewById(R.id.d1);
            b.setImageResource(R.drawable.dshovel);
        }
        //Checks for diamond axe
        if (a[1] < 1) {
            ImageView b = (ImageView) findViewById(R.id.d2);
            b.setImageResource(R.drawable.daxe);
        }
        //Checks for diamond sword
        if (a[2] < 1) {
            ImageView d = (ImageView) findViewById(R.id.d3);
            d.setImageResource(R.drawable.dsword);
        }
    }

    //This is the onClick for the small gold sword icon at the bottom of the screen
    public void swrd2(View view) {
        ImageView i = (ImageView) findViewById(R.id.g3);
        //The values returned form the click() method is stored in the integer a[] array.
        int a[] = click();
        //If there are less than 2 gold swords on the board, then the button will be clicked.
        if (a[5] < 1) {
            if (turn != 1)
                pick = 6;
        }
        //If there are already 2 gold swords on the board, the button will no longer be highlighted.
        //A message will also show up.
        else {
            i.setImageResource(R.drawable.isword);
            Toast.makeText(getApplicationContext(), "All of these pieces are used. Select another one", Toast.LENGTH_SHORT).show();
        }
        //These "if" statements check during the click if there are less than 2 of the diamond player's piece on the board.
        //If there are less than 2 pieces of a type, then that piece is highlighted before it's diamond's turn
        //so the diamond player can select that piece during their turn.
        //Checks for diamond shovel
        if (a[0] < 1) {
            ImageView b = (ImageView) findViewById(R.id.d1);
            b.setImageResource(R.drawable.dshovel);
        }
        //Checks for diamond axe
        if (a[1] < 1) {
            ImageView b = (ImageView) findViewById(R.id.d2);
            b.setImageResource(R.drawable.daxe);
        }
        //Checks for diamond sword
        if (a[2] < 1) {
            ImageView d = (ImageView) findViewById(R.id.d3);
            d.setImageResource(R.drawable.dsword);
        }
    }

    // This method places the "piece choice" of the player on the main game grid
    public void setPic(ImageView i) {
        //If the player selects the diamond shovel icon, then a large diamond shovel will be placed on the board
        if (pick == 1)
            i.setImageResource(R.drawable.shovel1);
            //If the player selects the gold shovel icon, then a large gold shovel will be placed on the board
        else if (pick == 2)
            i.setImageResource(R.drawable.shovel2);
            //If the player selects the diamond axe icon, then a large diamond axe will be placed on the board
        else if (pick == 3)
            i.setImageResource(R.drawable.axe1);
            //If the player selects the gold axe icon, then a large gold axe will be placed on the board
        else if (pick == 4)
            i.setImageResource(R.drawable.axe2);
            //If the player selects the diamond sword icon, then a large diamond sword will be placed on the board
        else if (pick == 5)
            i.setImageResource(R.drawable.sword1);
            //If the player selects the gold sword icon, then a large gold sword will be placed on the board
        else
            i.setImageResource(R.drawable.sword2);
    }

    // This method handles the switching of the turn symbol on the game screen
    public void playerturn() {
        //The values returned form the click() method is stored in the integer a[] array.
        int a[] = click();
        ImageView turnpic = (ImageView) findViewById(R.id.turn);
        //If it's the first player's turn, then it is switched to the second player's turn after
        if (turn == 1) {
            turn = 2;
            //The turn image is also changed
            turnpic.setImageResource(R.drawable.gturn);
            //As the turn switches to gold's turn, the check() method which  is stored in a[] is
            //implemented to check if any of the gold's pieces should be highlighted once again
            //if there are less than 2 of a specific type of piece on the board.
            //Checks for gold shovel
            if (a[3] < 1) {
                ImageView b = (ImageView) findViewById(R.id.g1);
                b.setImageResource(R.drawable.gshovel);
            }
            //Checks for gold axe
            if (a[4] < 1) {
                ImageView c = (ImageView) findViewById(R.id.g2);
                c.setImageResource(R.drawable.gaxe);
            }
            //Checks for gold sword
            if (a[5] < 1) {
                ImageView d = (ImageView) findViewById(R.id.g3);
                d.setImageResource(R.drawable.gsword);
            }
        }
        //If it's the second player's turn, then it is switched to the first player's turn
        else {
            turn = 1;
            //The turn image is also changed
            turnpic.setImageResource(R.drawable.dturn);
            //As the turn switches to gold's turn, the check() method which  is stored in a[] is
            //implemented to check if any of the gold's pieces should be highlighted once again
            //if there are less than 2 of a specific type of piece on the board.
            //Checks for diamond shovel
            if (a[0] < 1) {
                ImageView i = (ImageView) findViewById(R.id.d1);
                i.setImageResource(R.drawable.dshovel);
            }
            //Checks for diamond axe
            if (a[1] < 1) {
                ImageView daxe = (ImageView) findViewById(R.id.d2);
                daxe.setImageResource(R.drawable.daxe);
            }
            //Checks for diamond sword
            if (a[2] < 1) {
                ImageView dsword = (ImageView) findViewById(R.id.d3);
                dsword.setImageResource(R.drawable.dsword);
            }
        }
    }

    /*
    Note: For the next 9 methods, from the "aClick" method to the "iClick" method, the code is the same for all the grid button
    onClick so it is only necessary to check the "aClick" method to understand the rest of them. I have only written comments for the
    "aClick" method only because the comments for the other 8 methods would be exactly the same so it would be redundant to read.
     */

    //Method which handles the click on the [0][0] position of the int array
    public void aClick(View view) {
        //This makes the same type of piece (but of different colour) the same size
        int size = pick + (pick % 2);
        //Checks if the space occupied is less than size and if the piece selected is the correct coloured piece
        //for the person whose turn it is.
        if (board[0][0] < size - 1 && pick % 2 == turn % 2) {
            ImageView i = (ImageView) findViewById(R.id.a);
            //If the conditions are met, then the setPic() method is called to place a large piece on the game board
            setPic(i);
            //The space on the board is now equal to the piece placed there, which is the pick variable.
            //It can be (1, 2, 3, 4, 5, or 6)
            board[0][0] = pick;
            //The player's turn is switched by calling the playerturn() method.
            playerturn();
        }
        //If the player didn't choose a piece of their colour, then a message shows up
        else if (pick % 2 != turn % 2) {
            Toast.makeText(getApplicationContext(), "Please choose the piece of your colour!", Toast.LENGTH_SHORT).show();
        }
        //If the player selects the correct coloured piece but tries to place a lower piece on a higher one
        //then an error message will show up.
        else if ((board[0][0] + (pick % 2)) > size) {
            Toast.makeText(getApplicationContext(), "Please choose a higher piece!", Toast.LENGTH_SHORT).show();
        }
        //If nothing else can top a piece holding that space (ie. a sword) then a message shows up
        else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        //After each click the win() method is called to check the win condition
        win();
    }

    //Method which handles the click on the [0][1] position of the int array
    public void bClick(View view) {
        int size = pick + (pick % 2);
        if (board[0][1] < size - 1 && pick % 2 == turn % 2) {
            ImageView i = (ImageView) findViewById(R.id.b);
            setPic(i);
            board[0][1] = pick;
            playerturn();
        } else if (pick % 2 != turn % 2) {
            Toast.makeText(getApplicationContext(), "Please choose the piece of your colour!", Toast.LENGTH_SHORT).show();
        } else if ((board[0][1] + (pick % 2)) > size) {
            Toast.makeText(getApplicationContext(), "Please choose a higher piece!", Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        //After each click the win() method is called to check the win condition
        win();
    }

    //Method which handles the click on the [0][2] position of the int array
    public void cClick(View view) {
        int size = pick + (pick % 2);
        if (board[0][2] < size - 1 && pick % 2 == turn % 2) {
            ImageView i = (ImageView) findViewById(R.id.c);
            setPic(i);
            board[0][2] = pick;
            playerturn();
        } else if (pick % 2 != turn % 2) {
            Toast.makeText(getApplicationContext(), "Please choose the piece of your colour!", Toast.LENGTH_SHORT).show();
        } else if ((board[0][2] + (pick % 2)) > size) {
            Toast.makeText(getApplicationContext(), "Please choose a higher piece!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    //Method which handles the click on the [1][0] position of the int array
    public void dClick(View view) {
        int size = pick + (pick % 2);
        if (board[1][0] < size - 1 && pick % 2 == turn % 2) {
            ImageView i = (ImageView) findViewById(R.id.d);
            setPic(i);
            board[1][0] = pick;
            playerturn();
        } else if (pick % 2 != turn % 2) {
            Toast.makeText(getApplicationContext(), "Please choose the piece of your colour!", Toast.LENGTH_SHORT).show();
        } else if ((board[1][0] + (pick % 2)) > size) {
            Toast.makeText(getApplicationContext(), "Please choose a higher piece!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    //Method which handles the click on the [1][1] position of the int array
    public void eClick(View view) {
        int size = pick + (pick % 2);
        if (board[1][1] < size - 1 && pick % 2 == turn % 2) {
            ImageView i = (ImageView) findViewById(R.id.e);
            setPic(i);
            board[1][1] = pick;
            playerturn();
        } else if (pick % 2 != turn % 2) {
            Toast.makeText(getApplicationContext(), "Please choose the piece of your colour!", Toast.LENGTH_SHORT).show();
        } else if ((board[1][1] + (pick % 2)) > size) {
            Toast.makeText(getApplicationContext(), "Please choose a higher piece!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    //Method which handles the click on the [1][2] position of the int array
    public void fClick(View view) {
        int size = pick + (pick % 2);
        if (board[1][2] < size - 1 && pick % 2 == turn % 2) {
            ImageView i = (ImageView) findViewById(R.id.f);
            setPic(i);
            board[1][2] = pick;
            playerturn();
        } else if (pick % 2 != turn % 2) {
            Toast.makeText(getApplicationContext(), "Please choose the piece of your colour!", Toast.LENGTH_SHORT).show();
        } else if ((board[1][2] + (pick % 2)) > size) {
            Toast.makeText(getApplicationContext(), "Please choose a higher piece!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    //Method which handles the click on the [2][0] position of the int array
    public void gClick(View view) {
        int size = pick + (pick % 2);
        if (board[2][0] < size - 1 && pick % 2 == turn % 2) {
            ImageView i = (ImageView) findViewById(R.id.g);
            setPic(i);
            board[2][0] = pick;
            playerturn();
        } else if (pick % 2 != turn % 2) {
            Toast.makeText(getApplicationContext(), "Please choose the piece of your colour!", Toast.LENGTH_SHORT).show();
        } else if ((board[2][0] + (pick % 2)) > size) {
            Toast.makeText(getApplicationContext(), "Please choose a higher piece!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    //Method which handles the click on the [2][1] position of the int array
    public void hClick(View view) {
        int size = pick + (pick % 2);
        if (board[2][1] < size - 1 && pick % 2 == turn % 2) {
            ImageView i = (ImageView) findViewById(R.id.h);
            setPic(i);
            board[2][1] = pick;
            playerturn();
        } else if (pick % 2 != turn % 2) {
            Toast.makeText(getApplicationContext(), "Please choose the piece of your colour!", Toast.LENGTH_SHORT).show();
        } else if ((board[2][1] + (pick % 2)) > size) {
            Toast.makeText(getApplicationContext(), "Please choose a higher piece!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    //Method which handles the click on the [2][2] position of the int array
    public void iClick(View view) {
        int size = pick + (pick % 2);
        if (board[2][2] < size - 1 && pick % 2 == turn % 2) {
            ImageView i = (ImageView) findViewById(R.id.i);
            setPic(i);
            board[2][2] = pick;
            playerturn();
        } else if (pick % 2 != turn % 2) {
            Toast.makeText(getApplicationContext(), "Please choose the piece of your colour!", Toast.LENGTH_SHORT).show();
        } else if ((board[2][2] + (pick % 2)) > size) {
            Toast.makeText(getApplicationContext(), "Please choose a higher piece!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
        }
        win();
    }

    //Method which handles all the winning conditions of the game
    public void win() {
        //A local variable declared
        int winner = 0;
        //Another 3x3 int array created to use it to check for the win conditions
        int board2[][] = new int[3][3];
        //Nested loops used to loop through the entire 2-D array
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //If there empty spaces on the original board[][] array, then place those empty
                //spaces in the exact same location on the board2[][] array.
                if (board[i][j] == 0)
                    board2[i][j] = 0;
                    //If there are diamond coloured pieces on the original board[][] array, then place those
                    //positions of the diamond coloured pieces in the exact same location on the board2[][] array.
                else if ((board[i][j] % 2) == 1)
                    board2[i][j] = 1;
                    //If there are gold coloured pieces on the original board[][] array, then place those
                    //positions of the gold coloured pieces in the exact same location on the board2[][] array.
                else
                    board2[i][j] = 2;
            }
        }
        //Using board2[][] to test the winning conditions
        //Vertical winning conditions
        if (board2[0][0] == board2[0][1] && board2[0][0] == board2[0][2] && board2[0][0] != 0)
            winner = board2[0][0];
        else if (board2[1][0] == board2[1][1] && board2[1][0] == board2[1][2] && board2[1][0] != 0)
            winner = board2[1][0];
        else if (board2[2][0] == board2[2][1] && board2[2][0] == board2[2][2] && board2[2][0] != 0)
            winner = board2[2][0];
            //Horizontal winning condition
        else if (board2[0][0] == board2[1][0] && board2[0][0] == board2[2][0] && board2[0][0] != 0)
            winner = board2[0][0];
        else if (board2[0][1] == board2[1][1] && board2[0][1] == board2[2][1] && board2[0][1] != 0)
            winner = board2[0][1];
        else if (board2[0][2] == board2[1][2] && board2[0][2] == board2[2][2] && board2[0][2] != 0)
            winner = board2[1][2];
            //Diagonal winning condition
        else if (board2[0][0] == board2[1][1] && board2[1][1] == board2[2][2] && board2[1][1] != 0)
            winner = board2[1][1];
        else if (board2[0][2] == board2[1][1] && board2[1][1] == board2[2][0] && board2[0][2] != 0)
            winner = board2[0][2];
            //Tie game condition (when none of the other conditions above are met and no space is empty)
        else if (board2[0][0] != 0 && board2[0][1] != 0 && board2[0][2] != 0 &&
                board2[1][0] != 0 && board2[1][1] != 0 && board2[1][2] != 0 &&
                board2[2][0] != 0 && board2[2][1] != 0 && board2[2][2] != 0)
            winner = 3;
        //Pop up a message (DialogBox) depending on which player won the game
        if (winner == 1) {
            DwinBox();
        } else if (winner == 2) {
            GwinBox();
        } else if (winner == 3) {
            TieBox();
        }
    }

    //This is the onClick for the "Reset" button
    public void reset(View view) {
        //If the player's choice from setting is to use the "chest icon" as their
        //grid, the the ChestImage() method is called.
        if (choicegrid == 1)
            ChestImage();
            //If the player's choice from setting is to use the "TnT icon" as their
            //grid, the the TnTImage() method is called.
        else if (choicegrid == 2)
            TntImage();
            //If the player's choice from setting is to use the "tree icon" as their
            //grid, the the TreeImage() method is called.
        else if (choicegrid == 3)
            TreeImage();
        //This by default sets diamond's turn as first
        ImageView turnpic = (ImageView) findViewById(R.id.turn);
        turnpic.setImageResource(R.drawable.dturn);
        turn = 1;
    }

    //DialogBox created to show a message when "Diamond" wins the game
    public void DwinBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Adding the ImageView
        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.sword1);
        builder.setView(i);
        //The title on the Dialog
        builder.setTitle("Winner");
        //The message that will appear
        builder.setMessage("Congratulations!" +
                "\nDiamond wins the game!\n\nClick \"Play Again'\' to restart the game.");
        //What to do if the button is pressed
        builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //do something if they click the "Close" button
                //Once the game is over, another dialog box will appear which will ask the player to rate the game
                //The code for this is in the "ShowDialog()" method
                ShowDialog();
                //If the player's choice from setting is to use the "chest icon" as their
                //grid, the the ChestImage() method is called.
                if (choicegrid == 1)
                    ChestImage();
                    //If the player's choice from setting is to use the "TnT icon" as their
                    //grid, the the TnTImage() method is called.
                else if (choicegrid == 2)
                    TntImage();
                    //If the player's choice from setting is to use the "tree icon" as their
                    //grid, the the TreeImage() method is called.
                else if (choicegrid == 3)
                    TreeImage();
            }
        }).show();
    }

    //DialogBox created to show a message when "Gold" wins the game
    public void GwinBox() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Adding the ImageView
        ImageView i = new ImageView(this);
        i.setImageResource(R.drawable.sword2);
        builder.setView(i);
        //The title on the Dialog
        builder.setTitle("Winner");
        //The message that will appear
        builder.setMessage("Congratulations!" +
                "\nGold wins the game!\n\nClick \"Play Again'\' to restart the game.")
                //What to do if the button is pressed
                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do something if they click the "Close" button
                        //Once the game is over, another dialog box will appear which will ask the player to rate the game
                        //The code for this is in the "ShowDialog()" method
                        ShowDialog();
                        //If the player's choice from setting is to use the "chest icon" as their
                        //grid, the the ChestImage() method is called.
                        if (choicegrid == 1)
                            ChestImage();
                            //If the player's choice from setting is to use the "TnT icon" as their
                            //grid, the the TnTImage() method is called.
                        else if (choicegrid == 2)
                            TntImage();
                            //If the player's choice from setting is to use the "tree icon" as their
                            //grid, the the TreeImage() method is called.
                        else if (choicegrid == 3)
                            TreeImage();
                    }
                }).show();
    }

    //DialogBox created to show a message when the game ends in a tie
    public void TieBox() {
        new AlertDialog.Builder(this)
                //The title on the Dialog
                .setTitle("Game Tied")
                //The message that will appear
                .setMessage("Well played!" +
                        "\nIf you don't win, make sure you don't lose. The game has ended in a draw!" +
                        "\n\n Click \"Play Again'\' to reset the game.")
                //What to do if the button is pressed
                .setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //do something if they click the "Close" button
                        //Once the game is over, another dialog box will appear which will ask the player to rate the game
                        //The code for this is in the "ShowDialog()" method
                        ShowDialog();
                        //If the player's choice from setting is to use the "chest icon" as their
                        //grid, the the ChestImage() method is called.
                        if (choicegrid == 1)
                            ChestImage();
                            //If the player's choice from setting is to use the "TnT icon" as their
                            //grid, the the TnTImage() method is called.
                        else if (choicegrid == 2)
                            TntImage();
                            //If the player's choice from setting is to use the "tree icon" as their
                            //grid, the the TreeImage() method is called.
                        else if (choicegrid == 3)
                            TreeImage();
                    }
                }).show();
    }
}