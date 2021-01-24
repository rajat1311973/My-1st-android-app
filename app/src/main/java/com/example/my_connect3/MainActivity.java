package com.example.my_connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0 = yellow and 1 = Red
    int activePlayer = 0;
    boolean gameisactive = true;
    //2 means unplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    int [][] K = {{0,1,2},{3,4,5},{6,7,8},{0,4,8},{2,4,6},{2,5,8},{1,4,7},{0,3,6}};
    public void dropIn(View view)
    {
        ImageView C = (ImageView) view;
        int tapped = Integer.parseInt(C.getTag().toString());
        if(gameState[tapped]==2&&gameisactive) {
            gameState[tapped] = activePlayer;
            C.setTranslationY(-1000f);
            if (activePlayer == 0) {
                C.setImageResource(R.drawable.red);
                activePlayer = 1;
            } else {
                C.setImageResource(R.drawable.yellow);
                activePlayer = 0;
            }
            C.animate().translationYBy(1000f).setDuration(500);
            for(int [] A : K)
            {
                if(gameState[A[0]]==gameState[A[1]]&&gameState[A[1]]==gameState[A[2]]&&gameState[A[0]]!=2)
                {
                    //someone Has won
                    gameisactive = false;
                    String S;
                    if(gameState[A[0]]==0)
                    S = "Red";
                    else S = "Yellow";
                    TextView T = (TextView) findViewById(R.id.win);
                    T.setText(S + " Has won!!");
                    LinearLayout P = (LinearLayout) findViewById(R.id.play);
                    P.setVisibility(View.VISIBLE);
                }
                else{
                    boolean gameover = true;
                    for(int counter: gameState)
                    {
                        if(counter==2)
                            gameover = false;}
                    if(gameover)
                    {
                        TextView T = (TextView) findViewById(R.id.win);
                        T.setText("Game is draw!!");
                        LinearLayout P = (LinearLayout) findViewById(R.id.play);
                        P.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
    public void again(View view)
    {
        gameisactive = true;
        LinearLayout P = (LinearLayout) findViewById(R.id.play);
        P.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        for(int i=0;i<9;i++) {
            gameState[i] = 2;
        }

        androidx.gridlayout.widget.GridLayout G = (androidx.gridlayout.widget.GridLayout) findViewById(R.id.grid);
        for(int i=0;i<G.getChildCount();i++) {
            ((ImageView) G.getChildAt(i)).setImageResource(0);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}