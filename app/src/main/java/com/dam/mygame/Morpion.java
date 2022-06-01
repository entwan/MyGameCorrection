package com.dam.mygame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Morpion extends AppCompatActivity {

    /** Variable globale **/
    private static final String TAG = "Morpion";

    //O : case vide // 1 : StarWars  // 2 : StarTrek
    int activePlayer = 1;

    //tableau en début de parite
    int[] gameState = {0,0,0,0,0,0,0,0,0};

    int[][] winningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, // Combinaisons horizontales
                                {0,3,6}, {1,4,7}, {2,5,8}, // Combinaisons verticales
                                {0,4,8}, {2,4,6}}; // Combinaisons diagonales


    TextView tvWinner;
    Button btnPlayAgain;

    private void initUI() {
        tvWinner = findViewById(R.id.tvWinner);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morpion);

        initUI();

        // Récupération des données de l'intent
        Intent intent = getIntent();
        String titre = intent.getStringExtra("TitrePage");
        setTitle(titre);
    }

    public void dropIn(View view) {
        ImageView jeton = (ImageView) view;
        Log.i(TAG, jeton.getTag().toString());

        int emplacementJeton = Integer.parseInt(jeton.getTag().toString());


        Log.i(TAG, "Joueur: " + activePlayer + " a joue dans la case : " + emplacementJeton);

        //si la case est vide on peut jouer
        if (gameState[emplacementJeton] == 0) {

            gameState[emplacementJeton] = activePlayer;

            jeton.setTranslationY(-1000);
            if (activePlayer == 1) {
                jeton.setImageResource(R.drawable.star_wars);
                activePlayer = 2;
            } else {
                jeton.setImageResource(R.drawable.star_trek);
                activePlayer = 1;
            }
            jeton.animate()
                    .translationYBy(1000)
                    .rotation(1800)
                    .setDuration(500);

            // Loop dans le tableau winningPositions
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]]
                        && gameState[winningPosition[1]] == gameState[winningPosition[2]]
                        && gameState[winningPosition[0]] != 0) {

                    String happyEnd;
                    String winner;

                    if (activePlayer == 2) {
                        winner = "StarWars";
                        happyEnd = "May the force be with you !";

                    } else {
                        winner = "StarTrek";
                        happyEnd = "Live long and prosper";

                    }
                    Toast.makeText(this, happyEnd, Toast.LENGTH_LONG).show();
                    tvWinner.setText(winner + " has won!");
                    tvWinner.setVisibility(View.VISIBLE);
                    btnPlayAgain.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void playAgain(View view) {
        tvWinner.setVisibility(View.GONE);
        btnPlayAgain.setVisibility(View.GONE);
        GridLayout gridLayout = findViewById(R.id.myGridLayout);
        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
        }

        for(int j = 0; j < 9 ; j++) {
            gameState[j] = 0;
        }

        activePlayer = 1;
    }


}