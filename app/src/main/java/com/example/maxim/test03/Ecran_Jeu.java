package com.example.maxim.test03;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Random;

import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.ImageButton;
import android.widget.TextView;


public class Ecran_Jeu extends AppCompatActivity {

    /*Info a passer sur la page suivante*/
    private String nomC;
    private int ressource;

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
   private int table[][] = {
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
           {1,1,1,1,2,2,2,1,2,2,1,1,1,1,1},
           {1,1,1,1,2,2,2,1,2,2,1,1,1,1,1},
           {1,1,1,1,2,2,2,2,2,2,1,1,1,1,1},
           {1,1,1,1,2,2,2,1,2,2,1,1,1,1,1},
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
           {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}};

    /*Valeur permettant de gerer la zone de départ et le déplacement du personnage*/
    private int x;
    private int y;
    private int hd=0;
    private int vd=0;

    /*Statistique du personnage*/
    private int force;
    private int magie;
    private int pv;
    private int def;
    private int gemme;
    private int niveau;
    private int skillPoint;

    /*Nombre de potions du personnages*/
    private int nbrPotForce;
   private int nbrPotRouge;
   private int nbrPotBleue;



    Intent i;






    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
         /*Sauvegarde des valeurs dans le Bundle*/
        bundle.putInt("Force",force);
        bundle.putInt("Magie",magie);
        bundle.putInt("Defense",def);
        bundle.putInt("PV",pv);
        bundle.putInt("Niveau",niveau);
        bundle.putInt("pForce",nbrPotForce);
        bundle.putInt("pRouge",nbrPotRouge);
        bundle.putInt("pBleue",nbrPotBleue);
        bundle.putInt("SkillPoint",skillPoint);
        bundle.putSerializable("table",table);
        bundle.putInt("posX",x);
        bundle.putInt("posY",y);
        bundle.putInt("hd",hd);
        bundle.putInt("vd",vd);
        bundle.putInt("gemme",gemme);
    }


    @Override
     protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main2);
        i=getIntent();
        if(bundle!=null) {
            force=bundle.getInt("Force");
            magie=bundle.getInt("Magie");
            def=bundle.getInt("Defense");
            pv=bundle.getInt("PV");
            niveau = bundle.getInt("Niveau");
            nbrPotForce=bundle.getInt("pForce");
            nbrPotBleue=bundle.getInt("pBleue");
            nbrPotRouge=bundle.getInt("pRouge");
            skillPoint=bundle.getInt("SkillPoint");
            table=(int[][])bundle.getSerializable("table");
            x=bundle.getInt("posX");
            y=bundle.getInt("posY");
            hd=bundle.getInt("hd");
            vd=bundle.getInt("vd");
            gemme=bundle.getInt("gemme");

        } else {
            nomC = i.getStringExtra("NomClasse");
            ressource = i.getIntExtra("ressource", R.drawable.knight);
            force = i.getIntExtra("Force", 0);
            magie = i.getIntExtra("Magie", 0);
            def = i.getIntExtra("Defense", 0);
            pv = i.getIntExtra("PV", 0);
            niveau = i.getIntExtra("Niveau", 1);
            nbrPotForce = i.getIntExtra("pForce", 2);
            nbrPotRouge = i.getIntExtra("pRouge", 2);
            nbrPotBleue = i.getIntExtra("pBleue", 2);
            skillPoint=i.getIntExtra("SkillPoint",0);


           // table=(int[][])i.getSerializableExtra("table");
            x=i.getIntExtra("posX",7);
            y=i.getIntExtra("posY",6);
            hd=i.getIntExtra("hd",0);
            vd=i.getIntExtra("vd",0);
            gemme=i.getIntExtra("gemme",0);
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






        /*Initialise les TL des potions et des statistiques*/
        InitPot();
        InitStat();


       /*Récupération des informations concernant les boutons*/
        butPot= (ImageButton)findViewById(R.id.BtnPotion);
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
        /*Methode permettant de generer la map du jeu*/
        table[y][x]=0;
        UpdateMap(R.drawable.link_bas01);
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
            potForce.getLayoutParams().width = 55;
            potForce.getLayoutParams().height = 55;
            cptForce.setText("x"+nbrPotForce);
             /* Initialisation et affichage de la potion rouge*/
            trPotForce.addView(cptForce);
            trPotRouge= new TableRow(this);
            potRouge= new ImageButton(this);
            cptRouge= new TextView(this);
            potRouge.setBackgroundResource(R.drawable.red_pot);
            trPotRouge.addView(potRouge);
            potRouge.getLayoutParams().width = 55;
            potRouge.getLayoutParams().height = 55;
            cptRouge.setText("x"+nbrPotRouge);
            trPotRouge.addView(cptRouge);
             /* Initialisation et affichage de la potion de bleue*/
            trPotBleue= new TableRow(this);
            potBleue= new ImageButton(this);
            cptBleue= new TextView(this);
            potBleue.setBackgroundResource(R.drawable.blue_pot);
            trPotBleue.addView(potBleue);
           potBleue.getLayoutParams().width = 55;
            potBleue.getLayoutParams().height = 55;
            cptBleue.setText("x"+nbrPotBleue);
            trPotBleue.addView(cptBleue);
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
        tvLvl.setText("Niveau:"+niveau);
        tr.addView(tvLvl);
        tabStat.addView(tr);
        tr= new TableRow(this);
        tvForce.setText("Force:"+force);
        tr.addView(tvForce);
        tabStat.addView(tr);
        tr= new TableRow(this);
        tvMagie.setText("Magie:"+magie);
        tr.addView(tvMagie);
        tabStat.addView(tr);
        tr= new TableRow(this);
        tvPv.setText("PV:"+pv);
        tr.addView(tvPv);
        tabStat.addView(tr);
        tr= new TableRow(this);
        tvDef.setText("Def:"+def);
        tr.addView(tvDef);
        tabStat.addView(tr);
    }

    /*Met a jour le TL des potions*/


    /*Methode qui generera la carte dans le TL*/
    public void UpdateMap(int res){
       if(gemme==3){
            niveau++;
            skillPoint++;
            gemme=0;
            tvLvl.setText("Niveau: "+niveau);
        }
        /*Initialisation du layout*/
        tab = (TableLayout) findViewById(R.id.lab);
        /*Declaration d' un TR et IV qui seront reinitialisé à la volée*/
        TableRow tr;
        ImageView img;
        for(int i=3+vd;i<9+vd;i++) {
            tr = new TableRow(this);
            tab.addView(tr);
            for (int j = 3+hd; j < 11+hd; j++) {
                imgPers= new ImageButton(this);//ImageButton du personnage
                img = new ImageView(this);
                switch (table[i][j]) {
                    case 0:
                        imgPers.setBackgroundResource(res);
                        tr.addView(imgPers);
                        imgPers.getLayoutParams().width = 80;
                        imgPers.getLayoutParams().height = 80;
                        imgPers.setOnClickListener(fenetreStat);
                        break;
                    case 1:
                        img.setImageResource(R.drawable.tile_rocher);
                        tr.addView(img);
                        img.getLayoutParams().width = 80;
                        img.getLayoutParams().height = 80;
                        break;
                    case 2:
                        img.setImageResource(R.drawable.tile_chemin);
                        tr.addView(img);
                        img.getLayoutParams().width = 80;
                        img.getLayoutParams().height = 80;
                        break;
                    case 3:
                        img.setImageResource(R.drawable.tile_arbre);
                        tr.addView(img);
                        img.getLayoutParams().width = 80;
                        img.getLayoutParams().height = 80;
                        break;
                    case 4:
                        img.setImageResource(R.drawable.pearl_tile);
                        tr.addView(img);
                        img.getLayoutParams().width = 80;
                        img.getLayoutParams().height = 80;
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
            intent.putExtra("Niveau",niveau);
            intent.putExtra("SkillPoint", skillPoint);
            intent.putExtra("pRouge", nbrPotRouge);
            intent.putExtra("pForce", nbrPotForce);
            intent.putExtra("pBleue", nbrPotBleue);
            intent.putExtra("posX",x);
            intent.putExtra("posY",y);
            intent.putExtra("hd",hd);
            intent.putExtra("vd",vd);
            intent.putExtra("gemme",gemme);
            intent.putExtra("Force", force);
            intent.putExtra("PV", pv);
            intent.putExtra("Magie",magie);
            intent.putExtra("Defense",def);
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
            if(table[y+1][x]==4){
                table[y][x]=2;
                y++;
                table[y][x]=0;
                vd++;
                gemme++;
            }else if(table[y+1][x]==2){
                table[y][x]=2;
                y++;
                table[y][x]=0;
                vd++;
            }
            UpdateMap(R.drawable.link_bas01);


        }
    };

   private View.OnClickListener listenerBtHaut = new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            tab.removeAllViews();
            if(table[y-1][x]==4){
                table[y][x]=2;
                y--;
                table[y][x]=0;
                vd--;
                gemme++;
            }else if(table[y-1][x]==2){
                table[y][x]=2;
                y--;
                table[y][x]=0;
                vd--;
            }
            UpdateMap(R.drawable.link_haut01);


        }
    };

    private View.OnClickListener listenerBtGauche = new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            tab.removeAllViews();
            if(table[y][x-1]==4){
                table[y][x]=2;
                x--;
                table[y][x]=0;
                hd--;
                gemme++;
            }else if(table[y][x-1]==2){
                table[y][x]=2;
                x--;
                table[y][x]=0;
                hd--;
            }

                UpdateMap(R.drawable.link_gauche01);


        }
    };



    private View.OnClickListener listenerBtDroite = new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            tab.removeAllViews();
            if(table[y][x+1]==4){
                table[y][x]=2;
                x++;
                table[y][x]=0;
                hd++;
                gemme++;
            }else if(table[y][x+1]==2){
                table[y][x]=2;
                x++;
                table[y][x]=0;
                hd++;
            }
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
            if(nbrPotForce>0){
                force+=10;
                nbrPotForce--;
                cptForce.setText("x"+nbrPotForce);
                tvForce.setText("Force: "+ force);
            }

        }
    };

    private View.OnClickListener listenerBtRouge = new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            if(nbrPotRouge>0){
                pv+=10;
                nbrPotRouge--;
                cptRouge.setText("x"+nbrPotRouge);
                tvPv.setText("Pv: "+ pv);
            }

        }
    };


    private View.OnClickListener listenerBtBleue = new View.OnClickListener()
    {
        @Override
        public void onClick(View v){
            if(nbrPotBleue >0){
                magie+=10;
                nbrPotBleue --;
                cptBleue.setText("x"+nbrPotBleue);
                tvMagie.setText("Magie: "+ magie);
            }

        }
    };


}



