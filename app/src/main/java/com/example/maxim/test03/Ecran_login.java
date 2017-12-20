package com.example.maxim.test03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

/**
 * A login screen that offers login via email/password.
 */
public class Ecran_login extends AppCompatActivity{


    // UI references.
    EditText tvLog;
    EditText tvPass;
    TextView errorMess;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ecran_login);
        //Initialisation des champs d' entrée et du message d' erreur
        tvLog = (EditText) findViewById(R.id.email);
        tvPass = (EditText) findViewById(R.id.password);
        errorMess= (TextView) findViewById(R.id.errorMessage);
        Button butSign = (Button) findViewById(R.id.signIn);
        butSign.setOnClickListener(conn);
    }


    //Bouton qui soumet les logins du compte
    private View.OnClickListener conn= new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            String code="1000";

                TacheAsync_login as= new TacheAsync_login();
                try{
                    as.setIdent(tvLog.getText().toString());
                    as.setPass( tvPass.getText().toString());
                    as.execute().get();
                    code=as.getCode();
                }catch(InterruptedException e){

                }catch(ExecutionException e){

                }
                switch(code) {
                    case "0":
                        Intent i = new Intent(Ecran_login.this, Ecran_Personnage.class);
                        startActivity(i);
                        break;
                    case "100":
                        errorMess.setText("Erreur : login null ou vide");
                        break;
                    case "110":
                        errorMess.setText("Erreur : mot de passe null ou vide");
                        break;
                    case "200":
                        errorMess.setText("combinaison login mot de passe incorrect");
                        break;
                    case "1000":
                        errorMess.setText("Une autre erreur est survenue");
                        break;




                }


        }
    };



}

