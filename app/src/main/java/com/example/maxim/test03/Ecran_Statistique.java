package com.example.maxim.test03;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Ecran_Statistique extends AppCompatActivity {
    TableRow tr,trImg;

    TextView tvLvl, tvForce,tvMagie,tvPv,tvDef;

    private int force;
    private int magie;
    private int pv;
    private int sp;
    private int def;
    private int gemme;
    private int ressource;

    private String nomC;

    private int niveau;

    TableLayout tabStat;

    Intent i;
    @Override
    protected void onCreate(Bundle Info) {
        super.onCreate(Info);
        setContentView(R.layout.activity_main4);
        TableLayout tabImg= (TableLayout)findViewById(R.id.imageL);
        trImg= new TableRow(this);
        Button retour = (Button) findViewById(R.id.btnRetour);
        retour.setOnClickListener(boutonretour);
        i=getIntent();
        force=i.getIntExtra("Force",0);
        magie=i.getIntExtra("Magie",0);
        def=i.getIntExtra("Defense",0);
        pv=i.getIntExtra("PV",0);
        niveau=i.getIntExtra("Niveau",0);
        sp=i.getIntExtra("SkillPoint",0);
        ressource=i.getIntExtra("ressource",R.drawable.knight);
        nomC=i.getStringExtra("NomClasse");
        gemme=i.getIntExtra("gemme",0);
        InitStatWindow();
        ImageView img =  new ImageView(this);
        img.setImageResource(ressource);
        trImg.addView(img);
        tabImg.addView(trImg);

        img.getLayoutParams().width = 200;
        img.getLayoutParams().height = 70;
    }


    public void InitStatWindow(){
        tr= new TableRow(this);


        tvLvl =  new TextView(this);
        tvForce =  new TextView(this);
        tvMagie =  new TextView(this);
        tvPv =  new TextView(this);
        tvDef =  new TextView(this);



        Button bt1 = new Button(this);
        bt1.setText("+");
        Button bt2 = new Button(this);
        bt2.setText("+");
        Button bt3 = new Button(this);
        bt3.setText("+");
        Button bt4 = new Button(this);
        bt4.setText("+");



        tabStat= (TableLayout)findViewById(R.id.statW);

        tvLvl.setText(nomC+ "  Niveau :"+niveau);
        tr.addView(tvLvl);
        tabStat.addView(tr);
        tr= new TableRow(this);

        tvForce.setText("Force :"+force);
        tr.addView(tvForce);
        if(sp>0)
            tr.addView(bt1);
        tabStat.addView(tr);
        tr= new TableRow(this);

        tvMagie.setText("Magie :"+magie);
        tr.addView(tvMagie);
        if(sp>0)
            tr.addView(bt2);
        tabStat.addView(tr);
        tr= new TableRow(this);

        tvPv.setText("PV :"+pv);
        tr.addView(tvPv);
        if(sp>0)
            tr.addView(bt3);
        tabStat.addView(tr);
        tr= new TableRow(this);

        tvDef.setText("Defense :"+def);
        tr.addView(tvDef);
        if(sp>0)
            tr.addView(bt4);
        tabStat.addView(tr);
        bt1.setOnClickListener(addForce);
        bt2.setOnClickListener(addMagie);
        bt3.setOnClickListener(addPv);
        bt4.setOnClickListener(addDef);
    }

    private View.OnClickListener addForce= new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
           force++;
           sp--;
           gemme=0;
        tabStat.removeAllViews();
        InitStatWindow();
        }
    };

    private View.OnClickListener addMagie= new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            magie++;
            sp--;
            gemme=0;
            tabStat.removeAllViews();

            InitStatWindow();
        }
    };
    private View.OnClickListener addPv= new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            pv++;
            sp--;
            tabStat.removeAllViews();
            gemme=0;
            InitStatWindow();
        }
    };
    private View.OnClickListener addDef= new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            def++;
            sp--;
            tabStat.removeAllViews();
            gemme=0;
            InitStatWindow();
        }
    };

    private View.OnClickListener boutonretour= new View.OnClickListener()
    {
        @Override
        public void onClick(View v){


            Intent intent = new Intent(Ecran_Statistique.this,Ecran_Jeu.class);
            intent.putExtras(i);
            intent.putExtra("Force", force);
            intent.putExtra("PV", pv);
            intent.putExtra("Magie",magie);
            intent.putExtra("Defense",def);
            intent.putExtra("Niveau",niveau);
            intent.putExtra("SkillPoint",sp);
            if(gemme==3)
                intent.putExtra("gemme",0);

            startActivity(intent);
        }
    };
}
