package com.dam.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    /** Var globales **/
    Button btnPlusOuMoins, btnMorpion;

    /** Lien entre Java et design **/
    private void initUI() {
        btnPlusOuMoins = findViewById(R.id.btnPlusOuMoins);
        btnMorpion = findViewById(R.id.btnMorpion);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        /** Appel de la méthode pour initialiser les composants graphiques **/
        initUI();
        btnPlusOuMoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, PlusOuMoins.class);

                String titre = btnPlusOuMoins.getText().toString();

                intent.putExtra("TitrePage",titre);

                startActivity(intent);
            }
        });
    }


}