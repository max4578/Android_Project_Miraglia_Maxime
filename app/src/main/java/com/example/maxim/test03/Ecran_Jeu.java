package com.example.maxim.test03;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Random;
import java.util.concurrent.ExecutionException;

import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.ImageButton;
import android.widget.TextView;


public class Ecran_Jeu extends AppCompatActivity {



    /*Element XML a utilisé en global*/
    TextView tvLvl,tvForce,tvMagie,tvPv,tvDef;
    /*element Xml permettant la gestion des potions*/
    ImageButton potForce,potRouge,potBleue,imgPers;
    TableRow trPotForce,trPotRouge,trPotBleue;
    TextView cptForce,cptRouge,cptBleue;
    ImageButton butPot;

   /*Déclaration des différent tablelayuot*/
    TableLayout tab,tabStat,tabPot;

     /*schéma de la map*/
     private int table[][]=null;

     Personnage p;



    Resources res;
    int java_size_text;



    Intent i;



    @Override
     protected void onCreate(Bundle bundle) {
                super.onCreate(bundle);
        setContentView(R.layout.ecran_jeu);
        TacheAsync_map as= new TacheAsync_map();
        try{
            as.execute().get();
        }catch(InterruptedException e){

        }catch(ExecutionException e){

        }
        res = getResources();
        java_size_text= res.getInteger(R.integer.java_generated_text);

        i=getIntent();
        table=as.getTable();
        for(int i=0;i<table.length;i++){

            for(int j=0;j<table[i].length;j++){
                System.out.print(table[i][j]);
            }
            System.out.println();
        }
        /*Si le bundle est vides les informations sont soit initialisé par défaut au vaeurs choisie, soit récupèrer depuis l' intent*/
        if(bundle!=null) {
           retrieveBundle(bundle);

        } else {
           retrieveIntent();
           generateGem();
        }

        /*Initialise les TL des potions et des statistiques*/
        InitPot();
        InitStat();

        /*Récupération des informations concernant les boutons*/
        butPot= (ImageButton)findViewById(R.id.BtnPotion);
        //butPot.setVisibility(View.INVISIBLE);
        ImageButton butBas= (ImageButton)findViewById(R.id.bas);
        ImageButton butHaut= (ImageButton)findViewById(R.id.haut);
        ImageButton butGauche= (ImageButton)findViewById(R.id.gauche);
        ImageButton butDroite= (ImageButton)findViewById(R.id.droite);
        /*Création des listener pour les bouton*/
        butPot.setOnClickListener(listenerMenuPot);
        butBas.setOnClickListener(listenerBtBas);
        butHaut.setOnClickListener(listenerBtHaut);
        butGauche.setOnClickListener(listenerBtGauche);
        butDroite.setOnClickListener(listenerBtDroite);


        table[p.getY()][p.getX()]=0;
        UpdateMap(p.getDirection());
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
         /*Sauvegarde des valeurs dans le Bundle*/

        bundle.putSerializable("personnage", p);
        bundle.putSerializable("table", table);
    }


    public void retrieveBundle(Bundle bundle){

        p=(Personnage)bundle.getSerializable("personnage");
        table=(int[][])bundle.getSerializable("table");
    }

    public void retrieveIntent(){
        //nomC = i.getStringExtra("NomClasse");
        //  ressource = i.getIntExtra("ressource", R.drawable.knight);
        if(i.getSerializableExtra("personnage")==null) {
            int force = i.getIntExtra("Force", 0);
            int magie = i.getIntExtra("Magie", 0);
            int def = i.getIntExtra("Defense", 0);
            int pv = i.getIntExtra("PV", 0);
            int niveau = i.getIntExtra("Niveau", 1);
            int nbrPotForce = i.getIntExtra("pForce", 2);
            int nbrPotRouge = i.getIntExtra("pRouge", 2);
            int nbrPotBleue = i.getIntExtra("pBleue", 2);
            int skillPoint = i.getIntExtra("SkillPoint", 0);
            int x = i.getIntExtra("posX", 7);
            int y = i.getIntExtra("posY", 5);
            int hd = i.getIntExtra("hd", 0);
            int vd = i.getIntExtra("vd", 0);
            int gemme = i.getIntExtra("gemme", 0);

            p = new Personnage(x, y, hd, vd, force, magie, def, pv, gemme, niveau, skillPoint, nbrPotForce, nbrPotRouge, nbrPotBleue,R.drawable.link_bas01);
        }
        else
            p=(Personnage) i.getSerializableExtra("personnage");
    }

    public void generateGem(){
        /*Boucle permettant de generer des gemmes aléatoirement sur la carte*/
        int cpt=0;
        Random rand;
        for(int k=0; k <11; k++) {
            for (int j = 0; j < table[k].length; j++) {
                rand= new Random();
                int n = rand.nextInt(100) + 1;
                if (table[k][j] == 2 && cpt < 3) {
                    if (n> 42) {
                        table[k][j] = 4;
                        cpt++;
                    }
                }
            }
        }

    }



    /*Methode qui initialise le TL des potions*/
    public void InitPot(){
        /*Initialisation du layout*/
        tabPot = (TableLayout) findViewById(R.id.tabPot);
        /* Initialisation et affichage de la potion de force*/
        trPotForce= new TableRow(this);
        potForce= new ImageButton(this);
        cptForce= new TextView(this);
        potForce.setBackgroundResource(R.drawable.s_pot);
        trPotForce.addView(potForce);
        cptForce.setText("x"+p.getNbrPotForce());
        trPotForce.addView(cptForce);
        cptForce.setTextSize(java_size_text);
        /* Initialisation et affichage de la potion rouge*/
        trPotRouge= new TableRow(this);
        potRouge= new ImageButton(this);
        cptRouge= new TextView(this);
        potRouge.setBackgroundResource(R.drawable.red_pot);
        trPotRouge.addView(potRouge);
        cptRouge.setText("x"+p.getNbrPotRouge());
        trPotRouge.addView(cptRouge);
        cptRouge.setTextSize(java_size_text);
        /* Initialisation et affichage de la potion de bleue*/
        trPotBleue= new TableRow(this);
        potBleue= new ImageButton(this);
        cptBleue= new TextView(this);
        potBleue.setBackgroundResource(R.drawable.blue_pot);
        trPotBleue.addView(potBleue);
        cptBleue.setText("x"+p.getNbrPotBleue());
        trPotBleue.addView(cptBleue);
        cptBleue.setTextSize(java_size_text);

        /*Initialisation des listener pour les potions*/
        potForce.setOnClickListener(listenerBtForce);
        potRouge.setOnClickListener(listenerBtRouge);
        potBleue.setOnClickListener(listenerBtBleue);

    }

   /*Methode qui initialise le TL affichant les stat du personnage*/
    public void InitStat(){
        if(tabStat!=null)
            tabStat.removeAllViews();
        /*Initialisation d' un TR qui sera reinitialisé et de TV pour le texte*/
        TableRow tr= new TableRow(this);
        tvLvl= new TextView(this);
        tvForce= new TextView(this);
        tvMagie= new TextView(this);
        tvPv= new TextView(this);
        tvDef= new TextView(this);

        tabStat = (TableLayout) findViewById(R.id.stat);
        tvLvl.setText(res.getString(R.string.niveau)+p.getNiveau());
        tvLvl.setTextSize(java_size_text);
        tr.addView(tvLvl);
        tabStat.addView(tr);

        tr= new TableRow(this);
        tvForce.setText(res.getString(R.string.str_stat)+p.getForce());
        tr.addView(tvForce);
        tabStat.addView(tr);
        tvForce.setTextSize(java_size_text);
        tr= new TableRow(this);
        tvMagie.setText(res.getString(R.string.mag_stat)+p.getMagie());
        tr.addView(tvMagie);
        tabStat.addView(tr);
        tvMagie.setTextSize(java_size_text);
        tr= new TableRow(this);
        tvPv.setText(res.getString(R.string.vie_stat)+p.getPv());
        tr.addView(tvPv);
        tabStat.addView(tr);
        tvPv.setTextSize(java_size_text);
        tr= new TableRow(this);
        tvDef.setText(res.getString(R.string.def_stat)+p.getDef());
        tr.addView(tvDef);
        tabStat.addView(tr);
        tvDef.setTextSize(java_size_text);
    }

    /*Met a jour le TL des potions*/


    /*Methode qui generera la carte dans le TL*/
    public void UpdateMap(int res){
       if(p.getGemme()==3){
            p.setNiveau(p.getNiveau()+1);
            p.setSkillPoint(p.getSkillPoint()+1);
            p.setGemme(0);
            tvLvl.setText("Niveau: "+p.getNiveau());
        }
        /*Initialisation du layout*/
        tab = (TableLayout) findViewById(R.id.lab);
        /*Declaration d' un TR et IV qui seront reinitialisé à la volée*/
        TableRow tr;
        ImageView img;
        for(int i=3+p.getVd();i<9+p.getVd();i++) {
            tr = new TableRow(this);
            tab.addView(tr);
            for (int j = 3+p.getHd(); j < 11+p.getHd(); j++) {
                imgPers= new ImageButton(this);//ImageButton du personnage
                img = new ImageView(this);
                switch (table[i][j]) {
                    case 0:
                        imgPers.setBackgroundResource(res);
                        tr.addView(imgPers);
                        imgPers.setOnClickListener(fenetreStat);
                        break;
                    case 1:
                        img.setBackgroundResource(R.drawable.tile_rocher);
                        tr.addView(img);
                        break;
                    case 2:
                        img.setBackgroundResource(R.drawable.tile_chemin);
                        tr.addView(img);
                        break;
                    case 3:
                        img.setBackgroundResource(R.drawable.tile_arbre);
                        tr.addView(img);
                        break;
                    case 4:
                        img.setImageResource(R.drawable.pearl_tile);
                        tr.addView(img);
                        break;
                }

            }
        }
    }





     /*Ensemble des Listener lié au bouton*/
    private View.OnClickListener fenetreStat = new View.OnClickListener()
    {
        @Override
        public void onClick(View v){

            Intent intent = new Intent(Ecran_Jeu.this,Ecran_Statistique.class);
            intent.putExtras(i);
            intent.putExtra("personnage",p);
           // intent.putExtra("table",table);
            startActivity(intent);


        }
    };
    /*Listener des mouvements*/
    private View.OnClickListener listenerBtBas = new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            tab.removeAllViews();
            if(table[p.getY()+1][p.getX()]==4){
                table[p.getY()][p.getX()]=2;
                p.setY(p.getY()+1);
                table[p.getY()][p.getX()]=0;
                p.setVd(p.getVd()+1);
                p.setGemme(p.getGemme()+1);
            }else if(table[p.getY()+1][p.getX()]==2){
                table[p.getY()][p.getX()]=2;
                p.setY(p.getY()+1);
                table[p.getY()][p.getX()]=0;
                p.setVd(p.getVd()+1);
            }
            p.setDirection(R.drawable.link_bas01);
            UpdateMap(R.drawable.link_bas01);


        }
    };

   private View.OnClickListener listenerBtHaut = new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            tab.removeAllViews();
            if(table[p.getY()-1][p.getX()]==4){
                table[p.getY()][p.getX()]=2;
                p.setY(p.getY()-1);
                table[p.getY()][p.getX()]=0;
                p.setVd(p.getVd()-1);
                p.setGemme(p.getGemme()+1);
            }else if(table[p.getY()-1][p.getX()]==2){
                table[p.getY()][p.getX()]=2;
                p.setY(p.getY()-1);
                table[p.getY()][p.getX()]=0;
                p.setVd(p.getVd()-1);
            }
            p.setDirection(R.drawable.link_haut01);
            UpdateMap(R.drawable.link_haut01);


        }
    };

    private View.OnClickListener listenerBtGauche = new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            tab.removeAllViews();
            if(table[p.getY()][p.getX()-1]==4){
                table[p.getY()][p.getX()]=2;
                p.setX(p.getX()-1);
                table[p.getY()][p.getX()]=0;
                p.setHd(p.getHd()-1);
                p.setGemme(p.getGemme()+1);
            }else if(table[p.getY()][p.getX()-1]==2){
                table[p.getY()][p.getX()]=2;
                p.setX(p.getX()-1);
                table[p.getY()][p.getX()]=0;
                p.setHd(p.getHd()-1);
            }
            p.setDirection(R.drawable.link_gauche01);
            UpdateMap(R.drawable.link_gauche01);


        }
    };



    private View.OnClickListener listenerBtDroite = new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            tab.removeAllViews();
            if(table[p.getY()][p.getX()+1]==4){
                table[p.getY()][p.getX()]=2;
                p.setX(p.getX()+1);
                table[p.getY()][p.getX()]=0;
                p.setHd(p.getHd()+1);
                p.setGemme(p.getGemme()+1);
            }else if(table[p.getY()][p.getX()+1]==2){
                table[p.getY()][p.getX()]=2;
                p.setX(p.getX()+1);
                table[p.getY()][p.getX()]=0;
                p.setHd(p.getHd()+1);
            }
            p.setDirection(R.drawable.link_droite01);
            UpdateMap(R.drawable.link_droite01);

        }
    };

/*Affiche de layout des potions*/
    private View.OnClickListener listenerMenuPot = new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            if(tabPot.getChildCount()>0) {
                tabPot.removeAllViews();
            } else {
                tabPot.addView(trPotForce);
                tabPot.addView(trPotRouge);
                tabPot.addView(trPotBleue);
            }

        }
    };
/*Utilisation des potions*/
    private View.OnClickListener listenerBtForce = new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            if(p.getNbrPotForce()>0){
                p.setForce(p.getForce()+10);
                p.setNbrPotForce(p.getNbrPotForce()-1);
                cptForce.setText("x"+p.getNbrPotForce());
                tvForce.setText(res.getString(R.string.str_stat)+ p.getForce());
            }

        }
    };

    private View.OnClickListener listenerBtRouge = new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            if(p.getNbrPotRouge()>0){
                p.setPv(p.getPv()+10);
                p.setNbrPotRouge(p.getNbrPotRouge()-1);
                cptRouge.setText("x"+p.getNbrPotRouge());
                tvPv.setText(res.getString(R.string.vie_stat)+ p.getPv());
            }

        }
    };


    private View.OnClickListener listenerBtBleue = new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            if(p.getNbrPotBleue()>0){
                p.setMagie(p.getMagie()+10);
                p.setNbrPotBleue(p.getNbrPotBleue()-1);
                cptBleue.setText("x"+p.getNbrPotBleue());
                tvMagie.setText(res.getString(R.string.mag_stat)+ p.getMagie());
            }

        }
    };


}



