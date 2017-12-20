package com.example.maxim.test03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class Ecran_Personnage extends AppCompatActivity {

    private CheckBox c1,c2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecran_personnage);

        //Déclarations et initialisation des boutons
        Button btn = (Button)findViewById(R.id.continuer);
        c1=(CheckBox) findViewById(R.id.P1);
        c2=(CheckBox) findViewById(R.id.P2);
        //Création des listener
        btn.setOnClickListener(entrer);
        c1.setOnClickListener(CB1);
        c2.setOnClickListener(CB2);
    }


    //Choix perso 1
    private View.OnClickListener CB1= new View.OnClickListener()
    {
        @Override
        public void onClick(View v){

        if(c2.isChecked())
            c2.setChecked(false);

        }
    };

    //Choix perso 2
    private View.OnClickListener CB2= new View.OnClickListener()
    {
        @Override
        public void onClick(View v){

            if(c1.isChecked())
                c1.setChecked(false);

        }
    };

    //Entrez en jeu
    private View.OnClickListener entrer= new View.OnClickListener()
    {
        @Override
        public void onClick(View v){


            Intent intent = new Intent(Ecran_Personnage.this,Ecran_Jeu.class);
            if(c1.isChecked()){
                intent.putExtra("Force", 16);
                intent.putExtra("PV", 50);
                intent.putExtra("Magie",50);
                intent.putExtra("Defense",42);
                intent.putExtra("ressource",R.drawable.gnulinux);
                intent.putExtra("NomClasse", "Pro Linux");
                startActivity(intent);
            }
            else if(c2.isChecked()){
                intent.putExtra("Force", 25);
                intent.putExtra("PV", 45);
                intent.putExtra("Magie",55);
                intent.putExtra("Defense",25);
                intent.putExtra("ressource",R.drawable.hqdefault);
                intent.putExtra("NomClasse", "pyjama");
                startActivity(intent);

            }

        }
    };
}
