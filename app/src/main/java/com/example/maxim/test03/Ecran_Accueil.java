package com.example.maxim.test03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Ecran_Accueil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);



        Button btn = (Button)findViewById(R.id.btnEntrer);

        btn.setOnClickListener(entrer);
    }

    private View.OnClickListener entrer= new View.OnClickListener()
    {
        @Override
        public void onClick(View v){


            Intent intent = new Intent(Ecran_Accueil.this,Ecran_Personnage.class);
            startActivity(intent);
        }
    };
}
