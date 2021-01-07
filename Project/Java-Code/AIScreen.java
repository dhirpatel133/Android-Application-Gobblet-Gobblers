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

public class AIScreen extends AppCompatActivity {
    //Initializing the 2-D int Array
    int board[][] = new int[3][3];
    //Initializing the "turn" variable(global variable)
    int turn = 1;
    //Initializing a variable which will store the player's choices
    int pick = 0;
    //Initializing a variable which will indicate if one of the three piece choices for the player is selected
    int m = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_i_screen);
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
    }

    //When the "Instructions" button is pressed, the screen is changed to the
    //instruction screen.
    public void toinstructions(View view) {
        Intent i = new Intent(this, Instructions.class);
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
        //Indicates that the button was clicked and "m" holds a 1 instead of a 0
        m = 1;
        ImageView i = (ImageView) findViewById(R.id.d1);
        //This is the onClick for the small diamond shovel icon at the bottom of the screen
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
    }

    //This is the onClick for the small diamond axe icon at the bottom of the screen
    public void axe1(View view) {
        //Indicates that the button was clicked and "m" holds a 1 instead of a 0
        m = 1;
        ImageView i = (ImageView) findViewById(R.id.d2);
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
    }

    //This is the onClick for the small diamond sword icon at the bottom of the screen
    public void swrd1(View view) {
        //Indicates that the button was clicked and "m" holds a 1 instead of a 0
        m = 1;
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
    }

    // This method places the "choice" of the player on the main game grid
    public void setPic(ImageView i) {
        //If the player selects the diamond shovel icon, then a large diamond shovel will be placed on the board
        if (pick == 1)
            i.setImageResource(R.drawable.shovel1);
            //If "pick = 2" then it means that the AI has chosen golden shovel as it piece
        else if (pick == 2)
            i.setImageResource(R.drawable.shovel2);
            //If the player selects the diamond axe icon, then a large diamond axe will be placed on the board
        else if (pick == 3)
            i.setImageResource(R.drawable.axe1);
            //If "pick = 4" then it means that the AI has chosen golden axe as it piece
        else if (pick == 4)
            i.setImageResource(R.drawable.axe2);
            //If the player selects the diamond sword icon, then a large diamond sword will be placed on the board
        else if (pick == 5)
            i.setImageResource(R.drawable.sword1);
            //If "pick = 6" then it means that the AI has chosen golden sword as it piece
        else if (pick == 6)
            i.setImageResource(R.drawable.sword2);
    }

    // This method handles the switching of the turn symbol on the game screen
    public void playerturn() {
        //The values returned form the click() method is stored in the integer a[] array.
        int a[] = click();
        ImageView turnpic = (ImageView) findViewById(R.id.turn);
        //If it's the first player's turn, then it is switched to the computer's turn after
        if (turn == 1) {
            turn = 2;
            turnpic.setImageResource(R.drawable.gturn);
        }
        //If it's the computer's turn, then it is switched to the first player's turn after
        else {
            turn = 1;
            turnpic.setImageResource(R.drawable.dturn);
            //As the turn switches to the player's turn, the check() method which is stored in a[] is
            //implemented to check if any of the player's pieces should be highlighted once again
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

    //A method which keeps track of where the AI places the piece (ie. the x and y coordinates)
    //Depending on the placement of the piece, the screen is updated accordingly
    public void placement(int x, int y) {
        //The spot [0][0] is updated on the screen
        if (x == 0 && y == 0) {
            ImageView a = (ImageView) findViewById(R.id.a);
            setPic(a);
        }
        //The spot [0][1] is updated on the screen
        else if (x == 0 && y == 1) {
            ImageView b = (ImageView) findViewById(R.id.b);
            setPic(b);
        }
        //The spot [0][2] is updated on the screen
        else if (x == 0 && y == 2) {
            ImageView c = (ImageView) findViewById(R.id.c);
            setPic(c);
        }
        //The spot [1][0] is updated on the screen
        else if (x == 1 && y == 0) {
            ImageView d = (ImageView) findViewById(R.id.d);
            setPic(d);
        }
        //The spot [1][1] is updated on the screen
        else if (x == 1 && y == 1) {
            ImageView e = (ImageView) findViewById(R.id.e);
            setPic(e);
        }
        //The spot [1][2] is updated on the screen
        else if (x == 1 && y == 2) {
            ImageView f = (ImageView) findViewById(R.id.f);
            setPic(f);
        }
        //The spot [2][0] is updated on the screen
        else if (x == 2 && y == 0) {
            ImageView g = (ImageView) findViewById(R.id.g);
            setPic(g);
        }
        //The spot [2][1] is updated on the screen
        else if (x == 2 && y == 1) {
            ImageView h = (ImageView) findViewById(R.id.h);
            setPic(h);
        }
        //The spot [2][2] is updated on the screen
        else if (x == 2 && y == 2) {
            ImageView i = (ImageView) findViewById(R.id.i);
            setPic(i);
        }
    }

    //This method is used if all the sword and shovel pieces are used up and the AI can no
    //longer use those two pieces. In that case, this method is implemented and places a
    //golden shovel in the next empty space the AI finds.
    public void placement2() {
        int x = 0;
        int y = 0;
        for (int j = 0; j < 3; j++)
            for (int k = 0; k < 3; k++)
                if (board[j][k] == 0 && turn != 1) {
                    pick = 2;
                    board[j][k] = pick;
                    x = j;
                    y = k;
                    placement(x, y);
                    playerturn();
                }
    }

    //This method is implemented if the AI can not place a piece on a particular position because
    //that position contains the highest piece possible. In that case, this method searches for the spot
    //next to the occupied position and places a random piece.
    public void AIplacement() {
        //Gives a random integer between and including 1-3
        int select2 = (int) ((Math.random() * 3) + 1);
        //The values returned form the click() method is stored in the integer a[] array.
        int a[] = click();
        //The x & y variables are locally declared. This two integers will be used to determine the element location on the 2-D array
        int x = 0;
        int y = 0;
        //Two nested loops used to loop through the entire game board
        for (int j = 0; j < 3; j++)
            for (int k = 0; k < 3; k++)
                //If the computer finds an empty space and it is not the player's turn
                if (board[j][k] == 0 && turn != 1) {
                    //Then the computer runs a while loop, which keeps on looping until the computer has randomly picked a
                    //piece to place on the empty spot
                    while (pick % 2 != 0) {
                        //If the conditions are met, then this places a golden shovel
                        if (select2 == 1 && a[3] < 1) {
                            pick = 2;
                            board[j][k] = pick;
                            x = j;
                            y = k;
                            placement(x, y);
                            playerturn();
                        }
                        //If the conditions are met then this places the golden axe
                        else if (select2 == 2 && a[4] < 1) {
                            pick = 4;
                            board[j][k] = pick;
                            x = j;
                            y = k;
                            placement(x, y);
                            playerturn();
                        }
                        //If the conditions are met then this places the golden sword
                        else if (select2 == 3 && a[5] < 1) {
                            pick = 6;
                            board[j][k] = pick;
                            x = j;
                            y = k;
                            placement(x, y);
                            playerturn();
                        }
                        //If none of the conditions are met, then another random number is generated and the while loop runs again
                        select2 = (int) ((Math.random() * 3) + 1);
                    }
                }
                //If the space next to the occupied position is not empty and holds a diamond shovel, then the computer will place a golden axe
                else if (board[j][k] != 0 && board[j][k] == 1 && a[4] < 1 && turn != 1) {
                    pick = 4;
                    board[j][k] = pick;
                    x = j;
                    y = k;
                    placement(x, y);
                    playerturn();
                }
                //If the space next to the occupied position is not empty and holds a diamong axe, then the computer will place a golden sword
                else if (board[j][k] != 0 && board[j][k] == 3 && a[5] < 1 && turn != 1) {
                    pick = 6;
                    board[j][k] = pick;
                    x = j;
                    y = k;
                    placement(x, y);
                    playerturn();
                }
    }

    /*
    Note: The next 9 methods from "AIaClick" to "AIiClick" are going to be the same in layout and format.
          The only difference between these methods will be the position of the game board (ie. board[0][0], board [0][1] ... board[2][2])
          These methods are used by the computer to place pieces on the game board following a certain pattern/algorithm
     */
    //This method allows the AI to place a piece on the spot [0][0] or calls in other methods to place in a different location
    // if the spot [0][0] already contains the highest possible piece (ie. a sword)
    public void AIaClick() {
        //If it's not the player's turn
        if (turn != 1) {
            //The values returned form the click() method is stored in the integer a[] array.
            int a[] = click();
            ImageView i = (ImageView) findViewById(R.id.a);
            //If there is nothing on the position [0][0] then the AI will place a golden shovel
            if (board[0][0] == 0 && a[3] < 1) {
                //This indicates the the golden shovel has been chosen
                pick = 2;
                //Updates the position [0][0] to now hold a 2 instead of 0. (this indicates that a golden shovel is placed there)
                board[0][0] = pick;
                //Places the golden shovel
                setPic(i);
                //Changes the turn
                playerturn();
            }
            //If the position [0][0] has a diamond shovel on it, then the AI will place a golden axe
            else if (board[0][0] == 1 && a[4] < 1) {
                //This indicates the the golden axe has been chosen
                pick = 4;
                //Updates the position [0][0] to now hold a 4 instead of 0. (this indicates that a golden axe is placed there)
                board[0][0] = pick;
                //Places the golden axe
                setPic(i);
                //Changes the turn
                playerturn();
            }
            //If the position [0][0] has a diamond axe on it, then the AI will place a golden sword
            else if (board[0][0] == 3 && a[5] < 1) {
                //This indicates the the golden sword has been chosen
                pick = 6;
                //Updates the position [0][0] to now hold a 6 instead of 0. (this indicates that a golden sword is placed there)
                board[0][0] = pick;
                //Places the golden sword
                setPic(i);
                //Changes the turn
                playerturn();
            }
            //If the position [0][0] contains a diamond sword (this highest possible piece) then the AI will call the AIplacement() method.
            //This method will loop through the entire board and find a position next to [0][0] to place a piece.
            else if (board[0][0] == 5) {
                AIplacement();
            }
            //This condition is placed so that if the computer has already place two golden axes on the game board, then it will
            //not be able to place more of those pieces. So then the placement2() method is called. This method loops through the entire
            //game board and finds an empty space. On this empty space the AI will place a golden shovel.
            else if (a[4] >= 1)
                placement2();
                //This condition is placed so that if the computer has already place two golden swords on the game board, then it will
                //not be able to place more of those pieces. So then the placement2() method is called. This method loops through the entire
                //game board and finds an empty space. On this empty space the AI will place a golden shovel.
            else if (a[5] >= 1)
                placement2();
                //If none of the above possibilities work for the AI and if the AI can no longer place a piece on the board (highly impossible)
                //then an alert will show up and the player will win by default.
            else
                AiAlert();
        }
        //After the Ai has placed a piece, the win() method is called to check for any winning conditions
        win();
    }

    //This method allows the AI to place a piece on the spot [0][1] or calls in other methods to place in a different location
    // if the spot [0][1] already contains the highest possible piece (ie. a sword)
    public void AIbClick() {
        if (turn != 1) {
            ImageView i = (ImageView) findViewById(R.id.b);
            int a[] = click();
            int x = 0;
            int y = 0;
            if (board[0][1] == 0 && a[3] < 1) {
                pick = 2;
                board[0][1] = pick;
                setPic(i);
                playerturn();
            } else if (board[0][1] == 1 && a[4] < 1) {
                pick = 4;
                board[0][1] = pick;
                setPic(i);
                playerturn();
            } else if (board[0][1] == 3 && a[5] < 1) {
                pick = 6;
                board[0][1] = pick;
                setPic(i);
                playerturn();
            } else if (board[0][1] == 5) {
                AIplacement();
            } else if (a[4] >= 1)
                placement2();
            else if (a[5] >= 1)
                placement2();
            else
                AiAlert();
        }
        win();
    }

    //This method allows the AI to place a piece on the spot [0][2] or calls in other methods to place in a different location
    // if the spot [0][2] already contains the highest possible piece (ie. a sword)
    public void AIcClick() {
        if (turn != 1) {
            ImageView i = (ImageView) findViewById(R.id.c);
            int a[] = click();
            int x = 0;
            int y = 0;
            if (board[0][2] == 0 && a[3] < 1) {
                pick = 2;
                board[0][2] = pick;
                setPic(i);
                playerturn();
            } else if (board[0][2] == 1 && a[4] < 1) {
                pick = 4;
                board[0][2] = pick;
                setPic(i);
                playerturn();
            } else if (board[0][2] == 3 && a[5] < 1) {
                pick = 6;
                board[0][2] = pick;
                setPic(i);
                playerturn();
            } else if (board[0][2] == 5) {
                AIplacement();
            } else if (a[4] >= 1)
                placement2();
            else if (a[5] >= 1)
                placement2();
            else
                AiAlert();
        }
        win();
    }

    //This method allows the AI to place a piece on the spot [1][0] or calls in other methods to place in a different location
    // if the spot [1][0] already contains the highest possible piece (ie. a sword)
    public void AIdClick() {
        if (turn != 1) {
            ImageView i = (ImageView) findViewById(R.id.d);
            int a[] = click();
            int x = 0;
            if (board[1][0] == 0 && a[3] < 1) {
                pick = 2;
                board[1][0] = pick;
                setPic(i);
                playerturn();
            } else if (board[1][0] == 1 && a[4] < 1) {
                pick = 4;
                board[1][0] = pick;
                setPic(i);
                playerturn();
            } else if (board[1][0] == 3 && a[5] < 1) {
                pick = 6;
                board[1][0] = pick;
                setPic(i);
                playerturn();
            } else if (board[1][0] == 5) {
                AIplacement();
            } else if (a[4] >= 1)
                placement2();
            else if (a[5] >= 1)
                placement2();
            else
                AiAlert();
        }
        win();
    }

    //This method allows the AI to place a piece on the spot [1][1] or calls in other methods to place in a different location
    // if the spot [1][1] already contains the highest possible piece (ie. a sword)
    public void AIeClick() {
        if (turn != 1) {
            ImageView i = (ImageView) findViewById(R.id.e);
            int a[] = click();
            int x = 0;
            int y = 0;
            if (board[1][1] == 0 && a[3] < 1) {
                pick = 2;
                board[1][1] = pick;
                setPic(i);
                playerturn();
            } else if (board[1][1] == 1 && a[4] < 1) {
                pick = 4;
                board[1][1] = pick;
                setPic(i);
                playerturn();
            } else if (board[1][1] == 3 && a[5] < 1) {
                pick = 6;
                board[1][1] = pick;
                setPic(i);
                playerturn();
            } else if (board[1][1] == 5 || (a[4] > 1 && a[5] > 1)) {
                AIplacement();
            } else if (a[4] >= 1)
                placement2();
            else if (a[5] >= 1)
                placement2();
            else
                AiAlert();
        }
        win();
    }

    //This method allows the AI to place a piece on the spot [1][2] or calls in other methods to place in a different location
    // if the spot [1][2] already contains the highest possible piece (ie. a sword)
    public void AIfClick() {
        if (turn != 1) {
            ImageView i = (ImageView) findViewById(R.id.f);
            int a[] = click();
            int x = 0;
            int y = 0;
            if (board[1][2] == 0 && a[3] < 1) {
                pick = 2;
                board[1][2] = pick;
                setPic(i);
                playerturn();
            } else if (board[1][2] == 1 && a[4] < 1) {
                pick = 4;
                board[1][2] = pick;
                setPic(i);
                playerturn();
            } else if (board[1][2] == 3 && a[5] < 1) {
                pick = 6;
                board[1][2] = pick;
                setPic(i);
                playerturn();
            } else if (board[1][2] == 5) {
                AIplacement();
            } else if (a[4] >= 1)
                placement2();
            else if (a[5] >= 1)
                placement2();
            else
                AiAlert();
        }
        win();
    }

    //This method allows the AI to place a piece on the spot [2][0] or calls in other methods to place in a different location
    // if the spot [2][0] already contains the highest possible piece (ie. a sword)
    public void AIgClick() {
        if (turn != 1) {
            ImageView i = (ImageView) findViewById(R.id.g);
            int a[] = click();
            int x = 0;
            int y = 0;
            if (a[5] > 1)
                placement2();
            else if (board[2][0] == 0 && a[3] < 1) {
                pick = 2;
                board[2][0] = pick;
                setPic(i);
                playerturn();
            } else if (board[2][0] == 1 && a[4] < 1) {
                pick = 4;
                board[2][0] = pick;
                setPic(i);
                playerturn();
            } else if (board[2][0] == 3 && a[5] < 1) {
                pick = 6;
                board[2][0] = pick;
                setPic(i);
                playerturn();
            } else if (board[2][0] == 5) {
                AIplacement();
            } else if (a[4] >= 1)
                placement2();
            else if (a[5] >= 1)
                placement2();
            else
                AiAlert();
        }
        win();
    }

    //This method allows the AI to place a piece on the spot [2][1] or calls in other methods to place in a different location
    // if the spot [2][1] already contains the highest possible piece (ie. a sword)
    public void AIhClick() {
        if (turn != 1) {
            ImageView i = (ImageView) findViewById(R.id.h);
            int a[] = click();
            int x = 0;
            int y = 0;
            if (board[2][1] == 0 && a[3] < 1) {
                pick = 2;
                board[2][1] = pick;
                setPic(i);
                playerturn();
            } else if (board[2][1] == 1 && a[4] < 1) {
                pick = 4;
                board[2][1] = pick;
                setPic(i);
                playerturn();
            } else if (board[2][1] == 3 && a[5] < 1) {
                pick = 6;
                board[2][1] = pick;
                setPic(i);
                playerturn();
            } else if (board[2][1] == 5) {
                AIplacement();
            } else if (a[4] >= 1)
                placement2();
            else if (a[5] >= 1)
                placement2();
            else
                AiAlert();
        }
        win();
    }

    //This method allows the AI to place a piece on the spot [2][2] or calls in other methods to place in a different location
    // if the spot [2][2] already contains the highest possible piece (ie. a sword)
    public void AIiClick() {
        if (turn != 1) {
            ImageView i = (ImageView) findViewById(R.id.i);
            int a[] = click();
            int x = 0;
            int y = 0;
            if (board[2][2] == 0 && a[3] < 1) {
                pick = 2;
                board[2][2] = pick;
                setPic(i);
                playerturn();
            } else if (board[2][2] == 1 && a[4] < 1) {
                pick = 4;
                board[2][2] = pick;
                setPic(i);
                playerturn();
            } else if (board[2][2] == 3 && a[5] < 1) {
                pick = 6;
                board[2][2] = pick;
                setPic(i);
                playerturn();
            } else if (board[2][2] == 5) {
                AIplacement();
            } else if (a[4] >= 1)
                placement2();
            else if (a[5] >= 1)
                placement2();
            else
                AiAlert();
        }
        win();
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
        ImageView i = (ImageView) findViewById(R.id.a);
        //If the player has selected one of the piece choices from the bottom of the screen
        if (m == 1) {
            //Checks if the space occupied is less than size and if the piece selected is the correct coloured piece
            //for the person whose turn it is.
            if (board[0][0] < size - 1 && pick % 2 == turn % 2) {
                //If the conditions are met, then the setPic() method is called to place a large piece on the game board
                setPic(i);
                //The space on the board is now equal to the piece placed there, which is the pick variable.
                //Pick can only be 1, 3, or 5 because the player can only select the diamond pieces, the other pieces
                // 2, 4 and 6 are selected by the AI as golden pieces)
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
        }
        //The winning condition is checked after the player places a piece. If it's true, then the computer doesn't get
        //a chance to place a piece afterwards because the player already won.
        if (win() == true)
            win();
            //After the player has selected and placed a piece and placed it on the game board, then the respective AI method for the specific
            //position on the grid is called. For example, if this "aClick" method is implemented it means that the player placed a piece on the position
            //[0][0]. This means that the "AIaClick" method will be called as the computer can place another piece on top of the player's piece in the exact
            //same spot or choose a different spot.
        else
            AIaClick();
    }

    //Method which handles the click on the [0][1] position of the int array
    public void bClick(View view) {
        ImageView i = (ImageView) findViewById(R.id.b);
        int size = pick + (pick % 2);
        if (m == 1 && turn == 1) {
            if (board[0][1] < size - 1 && pick % 2 == turn % 2) {
                setPic(i);
                board[0][1] = pick;
                playerturn();
            } else if (pick % 2 != turn % 2) {
                Toast.makeText(getApplicationContext(), "Please choose the piece of your colour!", Toast.LENGTH_SHORT).show();
            } else if ((board[0][1] + (pick % 2)) > size) {
                Toast.makeText(getApplicationContext(), "Please choose a higher piece!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Place already taken", Toast.LENGTH_SHORT).show();
            }
        }
        if (win() == true)
            win();
        else
            AIbClick();
    }

    //Method which handles the click on the [0][2] position of the int array
    public void cClick(View view) {
        ImageView i = (ImageView) findViewById(R.id.c);
        int size = pick + (pick % 2);
        if (m == 1 && turn == 1) {
            if (board[0][2] < size - 1 && pick % 2 == turn % 2) {
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
        }
        if (win() == true)
            win();
        else
            AIcClick();
    }

    //Method which handles the click on the [1][0] position of the int array
    public void dClick(View view) {
        ImageView i = (ImageView) findViewById(R.id.d);
        int size = pick + (pick % 2);
        if (m == 1 && turn == 1) {
            if (board[1][0] < size - 1 && pick % 2 == turn % 2) {
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
        }
        if (win() == true)
            win();
        else
            AIdClick();
    }

    //Method which handles the click on the [1][1] position of the int array
    public void eClick(View view) {
        ImageView i = (ImageView) findViewById(R.id.e);
        int size = pick + (pick % 2);
        if (m == 1 && turn == 1) {
            if (board[1][1] < size - 1 && pick % 2 == turn % 2) {
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
        }
        if (win() == true)
            win();
        else
            AIeClick();
    }

    //Method which handles the click on the [1][2] position of the int array
    public void fClick(View view) {
        ImageView i = (ImageView) findViewById(R.id.f);
        int size = pick + (pick % 2);
        if (m == 1 && turn == 1) {
            if (board[1][2] < size - 1 && pick % 2 == turn % 2) {
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
        }
        if (win() == true)
            win();
        else
            AIfClick();
    }

    //Method which handles the click on the [2][0] position of the int array
    public void gClick(View view) {
        ImageView i = (ImageView) findViewById(R.id.g);
        int size = pick + (pick % 2);
        if (m == 1 && turn == 1) {
            if (board[2][0] < size - 1 && pick % 2 == turn % 2) {
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
        }
        if (win() == true)
            win();
        else
            AIgClick();
    }

    //Method which handles the click on the [2][1] position of the int array
    public void hClick(View view) {
        ImageView i = (ImageView) findViewById(R.id.h);
        int size = pick + (pick % 2);
        if (m == 1 && turn == 1) {
            if (board[2][1] < size - 1 && pick % 2 == turn % 2) {
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
        }
        if (win() == true)
            win();
        else
            AIhClick();
    }

    //Method which handles the click on the [2][2] position of the int array
    public void iClick(View view) {
        ImageView i = (ImageView) findViewById(R.id.i);
        int size = pick + (pick % 2);
        if (m == 1 && turn == 1) {
            if (board[2][2] < size - 1 && pick % 2 == turn % 2) {
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
        }
        if (win() == true)
            win();
        else
            AIiClick();
    }

    //Method which handles all the winning conditions of the game
    public boolean win() {
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
        //If the player wins, or if the computer wins or if there is a tie, the return will be true otherwise,
        //the return will be false which means that unless there's a win or a tie, the win method will be ignored.
        if (winner == 1) {
            DwinBox();
            return true;
        } else if (winner == 2) {
            AiwinBox();
            return true;
        } else if (winner == 3) {
            TieBox();
            return true;
        } else
            return false;
    }

    //This is the onClick for the "Reset" button
    public void reset(View view) {
        //This resets all the grid buttons and pieces choice buttons to back to normal
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
        //This by default sets diamond's turn as first
        ImageView turnpic = (ImageView) findViewById(R.id.turn);
        turnpic.setImageResource(R.drawable.dturn);
        turn = 1;
        //This sets game grid back to normal so that every space is empty
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                board[k][j] = 0;
            }
        }
    }

    //Another copy of the reset method is created to use the "reset" attribute in pop up dialog box
    public void reset2() {
        //This resets all the grid buttons and pieces choice buttons to back to normal
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
        //This by default sets diamond's turn as first
        ImageView turnpic = (ImageView) findViewById(R.id.turn);
        turnpic.setImageResource(R.drawable.dturn);
        turn = 1;
        //This sets game grid back to normal so that every space is empty
        for (int k = 0; k < 3; k++) {
            for (int j = 0; j < 3; j++) {
                board[k][j] = 0;
            }
        }
    }

    //DialogBox created to show a message when the AI doesn't know where to place a piece
    //If this happens then the player wins by default (But this is a very high chance that this will not happen
    //as out of all the tests, the AI hasn't failed once to place a piece).
    public void AiAlert() {
        new AlertDialog.Builder(this)
                //The title on the Dialog
                .setTitle("Oh oh!")
                //The message that will appear
                .setMessage("Congratulations you win!" +
                        "\n\"The computer no longer knows where to place the piece.\"\n" +
                        "\"You have outsmarted the computer, you win!\"!\n\nClick \"Ok'\' to reset the game.")
                //What to do if the button is pressed
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //If the button is clicked then the reset method is called
                        reset2();
                    }
                }).show();
    }

    //DialogBox created to show a message when "Diamond" or the "player" wins the game
    public void DwinBox() {
        new AlertDialog.Builder(this)
                //The title on the Dialog
                .setTitle("Winner")
                //The message that will appear
                .setMessage("Congratulations!" +
                        "\nDiamond wins the game!\n\nClick \"Ok'\' to reset the game.")
                //What to do if the button is pressed
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //If the button is clicked then the reset method is called
                        reset2();
                    }
                }).show();
    }

    //DialogBox created to show a message when "Gold" or the "Computer" wins the game
    public void AiwinBox() {
        new AlertDialog.Builder(this)
                //The title on the Dialog
                .setTitle("Winner")
                //The message that will appear
                .setMessage("Oh no!" +
                        "\nIt looks like the computer won the game!\n\nClick \"Ok'\' to reset the game.")
                //What to do if the button is pressed
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //If the button is clicked then the reset method is called
                        reset2();
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
                        "\n\n Click \"Ok'\' to reset the game.")
                //What to do if the button is pressed
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //If the button is clicked then the reset method is called
                        reset2();
                    }
                }).show();
    }
}