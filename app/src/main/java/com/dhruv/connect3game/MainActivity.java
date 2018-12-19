package com.dhruv.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean gameActive = true;

    int player1 = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void gameEnd (int winner) {
        gameActive = false;

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        winnerTextView.setVisibility(View.VISIBLE);
        if (winner==0) {
            winnerTextView.setText("Yellow wins!");
        }
        else if (winner==1) {
            winnerTextView.setText("Red wins!");
        }
        else {
            winnerTextView.setText("Game Tied!");
        }

        Button playAgain = (Button) findViewById(R.id.playAgain);
        playAgain.setVisibility(View.VISIBLE);
    }

    public void resetGame (View view) {

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        winnerTextView.setVisibility(View.INVISIBLE);
        Button playAgain = (Button) findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);

        player1 = 0;
        gameActive = true;

        for(int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }

        // Some Errors, Debug Later!

//        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
//        int numberOfChilds = 9;
//
//        for(int i=0; i<numberOfChilds; i++) {
//
//            ImageView counter = (ImageView) gridLayout.getChildAt(i);
//            counter.setImageDrawable(null);
//        }

        // Alternate Approach
        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        imageView1.setImageDrawable(null);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView2.setImageDrawable(null);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        imageView3.setImageDrawable(null);
        ImageView imageView4 = (ImageView) findViewById(R.id.imageView4);
        imageView4.setImageDrawable(null);
        ImageView imageView5 = (ImageView) findViewById(R.id.imageView5);
        imageView5.setImageDrawable(null);
        ImageView imageView6 = (ImageView) findViewById(R.id.imageView6);
        imageView6.setImageDrawable(null);
        ImageView imageView7 = (ImageView) findViewById(R.id.imageView7);
        imageView7.setImageDrawable(null);
        ImageView imageView8 = (ImageView) findViewById(R.id.imageView8);
        imageView8.setImageDrawable(null);
        ImageView imageView9 = (ImageView) findViewById(R.id.imageView9);
        imageView9.setImageDrawable(null);

    }

    public int checkWin () {

        int winner = 2;

        for (int[] winningPosition: winningPositions) {
            if (gameState[winningPosition[0]]==gameState[winningPosition[1]] && gameState[winningPosition[0]]==gameState[winningPosition[2]] && gameState[winningPosition[0]]!=2) {
                winner = gameState[winningPosition[0]];
            }
        }

        return winner;
    }

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;

        int block = Integer.parseInt(counter.getTag().toString());

        if (gameActive) {
            if (gameState[block]!=2) {
                Toast.makeText(this, "Already blocked!", Toast.LENGTH_SHORT).show();

            } else {
                if (player1==0) {
                    counter.setImageResource(R.drawable.yellow);
                    gameState[block] = 0;
                    player1 = 1;

                } else {
                    counter.setImageResource(R.drawable.red);
                    gameState[block] = 1;
                    player1 = 0;
                }

                counter.setTranslationY(-1500);
                counter.animate().translationYBy(1500).setDuration(500);
            }
        }

        int winner = checkWin();

        if (winner==0 || winner==1) {
            gameEnd(winner);
        }
        else {
            boolean tieCase = true;
            for (int i=0; i<9; i++) {
                if (gameState[i]==2) {
                    tieCase = false;
                    break;
                }
            }

            if (tieCase) {
                gameEnd(2);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
