package com.example.soso.tictactoc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 0 = yellow , 1 = red
    int activePlayer = 0;
    Boolean GameIsActive = true;

    // 2 means unplayed
    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] WinningPositions =  {{0,1,2} ,{3,4,5} ,{6,7,8} ,{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int tappedCountr = Integer.parseInt(counter.getTag().toString());
        if (gamestate[tappedCountr] == 2 && GameIsActive) {
            gamestate[tappedCountr] = activePlayer;
            counter.setTranslationY(-1000f);
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for (int[] WinningPosition : WinningPositions) {
                if (gamestate[WinningPosition[0]] == gamestate[WinningPosition[1]] &&
                        gamestate[WinningPosition[1]] == gamestate[WinningPosition[2]] &&
                        gamestate[WinningPosition[0]] != 2) {

                    //someone has win
                    GameIsActive = false;
                    String winner = "O";

                    if (gamestate[WinningPosition[0]] == 0) {
                        winner = "X";
                    }

                    System.out.print(gamestate[WinningPosition[0]]);

                    TextView winnerMessage = (TextView) findViewById(R.id.WinnerMsg);
                    winnerMessage.setText(winner + " HAS WON  ! ");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(view.VISIBLE);


                }else {
                    boolean GameIsOver = true;
                    for(int counterState : gamestate){
                        if(counterState == 2 ) GameIsOver = false;
                    }
                    if (GameIsOver){
                        TextView winnerMessage = (TextView) findViewById(R.id.WinnerMsg);
                        winnerMessage.setText(" X & O  DRAW ! ");
                        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                        layout.setVisibility(view.VISIBLE);
                    }
                }
            }
        }
    }
        public void playAgain(View view){
            GameIsActive = true;
            LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
            layout.setVisibility(view.INVISIBLE);
            activePlayer = 0;

            for (int i = 0; i < gamestate.length ; i++){
                gamestate[i] = 2;
            }
            GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);
            for (int i = 0 ; i <gridLayout.getChildCount(); i++){
                ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
            }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
