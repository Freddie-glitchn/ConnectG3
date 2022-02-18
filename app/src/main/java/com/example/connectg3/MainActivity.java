package com.example.connectg3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = mice, 1 = cat
    int activePlayer = 0;

    boolean gameActive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPos = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void dropIn(View view){

        ImageView cat =  (ImageView) view;



        int tappedCat = Integer.parseInt(cat.getTag().toString());


//        cat.setImageResource(R.drawable.mice);

        if (gameState[tappedCat] == 2 && gameActive) {

            gameState[tappedCat] = activePlayer;
            cat.setTranslationY(-1000f);

            if (activePlayer == 0) {

                cat.setImageResource(R.drawable.mice);
                activePlayer = 1;

            } else {

                cat.setImageResource(R.drawable.cat1);
                activePlayer = 0;

            }


            cat.animate().translationYBy(1000f).rotationBy(720).setDuration(300);

            for (int[] winPosin : winPos ){

                if (gameState[winPosin[0]] == gameState[winPosin[1]] &&
                        gameState[winPosin[1]] == gameState[winPosin[2]] &&
                        gameState[winPosin[0]] != 2){

                    gameActive = false;

                    String winner = "Cat";

                    if (gameState[winPosin[0]] == 0){

                        winner = "Mice";

                    }

                    //someone has won

                    TextView winMessage = (TextView)findViewById(R.id.winMessage);

                    winMessage.setText(winner + " has won!");

                    LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                    layout.setVisibility(View.VISIBLE);


                }else {

                    boolean gameOver = true;

                    for (int catState : gameState){

                        if (catState == 2) gameOver = false;

                        }

                        if (gameOver){

                            TextView winMessage = (TextView)findViewById(R.id.winMessage);

                            winMessage.setText("It's a draw!");

                            LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

                            layout.setVisibility(View.VISIBLE);

                        }

                    }

                }

            }

        }
    

    public void playAgain(View view){

        gameActive = true;

        LinearLayout layout = (LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE);

        activePlayer = 0;

        for (int i = 0; i < gameState.length; i++){

            gameState[i] = 2;

        }

        GridLayout gridLayout = (GridLayout)findViewById(R.id.gridLayout);

        for (int i =0; i < gridLayout.getChildCount(); i++ ){

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
