package com.example.maxim.test03;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Ecran_Statistique extends AppCompatActivity {

    //Déclaration des différens éléments de la page
    private TableRow tr,trImg;
    private TextView tvLvl, tvForce,tvMagie,tvPv,tvDef;

    //Déclaration du personnage et ces informations
    Personnage p;
    private String nomC;
    private int ressource;

    TableLayout tabStat;


    //Déclaration des ressources(value)
    private Resources res;
    private int java_size_text;
    private int width;
    private int height;

    Intent i;
    @Override
    protected void onCreate(Bundle Info) {
        super.onCreate(Info);
        setContentView(R.layout.ecran_stat);
        //Recupération de divers ressources
        res = getResources();
        java_size_text= res.getInteger(R.integer.java_generated_text);
        width=res.getInteger(R.integer.w_img);
        height=res.getInteger(R.integer.h_img);
        //Affichage de l' image
        TableLayout tabImg= (TableLayout)findViewById(R.id.imageL);
        trImg=new TableRow(this);
        //Déclaration et init. du bouton retour
        Button retour = (Button) findViewById(R.id.btnRetour);
        retour.setOnClickListener(boutonretour);

        //Récupération de l' intent
        i=getIntent();
        p=(Personnage) i.getSerializableExtra("personnage");
        ressource=i.getIntExtra("ressource",R.drawable.gnulinux);
        nomC=i.getStringExtra("NomClasse");
        InitStatWindow();
        ImageView img =  new ImageView(this);
        //Formatage de l' image
        img.setImageResource(ressource);
        img.setMinimumWidth(width);
        img.setMaxHeight(height);
        trImg.addView(img);
        tabImg.addView(trImg);

    }


    public void InitStatWindow(){
        //Initialisation des TR et TV
        tr= new TableRow(this);
        tvLvl =  new TextView(this);
        tvForce =  new TextView(this);
        tvMagie =  new TextView(this);
        tvPv =  new TextView(this);
        tvDef =  new TextView(this);


        //Declaration et initialisation des boutons
        Button bt1 = new Button(this);
        bt1.setText("+");
        Button bt2 = new Button(this);
        bt2.setText("+");
        Button bt3 = new Button(this);
        bt3.setText("+");
        Button bt4 = new Button(this);
        bt4.setText("+");


        //Creation du layout qui contiendra les statistiques
        tabStat= (TableLayout)findViewById(R.id.statW);
        //Affichage du niveau
        tvLvl.setText(nomC+" "+ res.getString(R.string.niveau)+p.getNiveau());
        tvLvl.setTextSize(java_size_text);
        tr.addView(tvLvl);
        tabStat.addView(tr);
        tr= new TableRow(this);
        //Affichage de la force
        tvForce.setText(res.getString(R.string.str_stat)+p.getForce());
        tvForce.setTextSize(java_size_text);
        tr.addView(tvForce);
        if(p.getSkillPoint()>0)
            tr.addView(bt1);
        tabStat.addView(tr);
        tr= new TableRow(this);
        //Affichage de la magie
        tvMagie.setText(res.getString(R.string.mag_stat)+p.getMagie());
        tvMagie.setTextSize(java_size_text);
        tr.addView(tvMagie);
        if(p.getSkillPoint()>0)
            tr.addView(bt2);
        tabStat.addView(tr);
        tr= new TableRow(this);
        //Affichage de la vie
        tvPv.setText(res.getString(R.string.vie_stat)+p.getPv());
        tvPv.setTextSize(java_size_text);
        tr.addView(tvPv);
        if(p.getSkillPoint()>0)
            tr.addView(bt3);
        tabStat.addView(tr);
        tr= new TableRow(this);
        //Affichage de la defense
        tvDef.setText(res.getString(R.string.def_stat)+p.getDef());
        tvDef.setTextSize(java_size_text);
        tr.addView(tvDef);
        if(p.getSkillPoint()>0)
            tr.addView(bt4);
        tabStat.addView(tr);
        //Déclaration des listenen
        bt1.setOnClickListener(addForce);
        bt2.setOnClickListener(addMagie);
        bt3.setOnClickListener(addPv);
        bt4.setOnClickListener(addDef);
    }

    //Ajoute un point a la force
    private View.OnClickListener addForce= new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
           p.setForce(p.getForce()+1);
           p.setSkillPoint(p.getSkillPoint()-1);
           p.setGemme(0);
           tabStat.removeAllViews();
           InitStatWindow();
        }
    };

    //Ajoute un point a la magie
    private View.OnClickListener addMagie= new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            p.setMagie(p.getMagie()+1);
            p.setSkillPoint(p.getSkillPoint()-1);
            p.setGemme(0);
            tabStat.removeAllViews();

            InitStatWindow();
        }
    };

    //Ajoute un point a la vie
    private View.OnClickListener addPv= new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            p.setPv(p.getPv()+1);
            p.setSkillPoint(p.getSkillPoint()-1);
            p.setGemme(0);
            tabStat.removeAllViews();
            InitStatWindow();
        }
    };

    //Ajoute un point a la défense
    private View.OnClickListener addDef= new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            p.setDef(p.getDef()+1);
            p.setSkillPoint(p.getSkillPoint()-1);
            p.setGemme(0);
            tabStat.removeAllViews();
            InitStatWindow();
        }
    };

    //Rtourne vers l écran de jeu
    private View.OnClickListener boutonretour= new View.OnClickListener()
    {
        @Override
        public void onClick(View v){


            Intent intent = new Intent(Ecran_Statistique.this,Ecran_Jeu.class);
            intent.putExtras(i);
            intent.putExtra("personnage",p);
            startActivity(intent);
        }
    };
}
