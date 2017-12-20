package com.example.maxim.test03;

import java.io.Serializable;

/**
 * Created by maxim on 18/12/2017.
 */

public class Personnage implements Serializable {
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

    private int direction;

    public Personnage(int x,int y,int hd,int vd,int force, int magie , int pv , int def ,
                      int gemme, int niveau, int skillPoint,
                      int nbrPotForce, int nbrPotRouge, int nbrPotBleue,int direction){
        this.x=x;
        this.y=y;
        this.hd=hd;
        this.vd=vd;
        this.force=force;
        this.magie=magie;
        this.pv=pv;
        this.def=def;
        this.gemme=gemme;
        this.niveau=niveau;
        this.skillPoint=skillPoint;
        this.nbrPotForce=nbrPotForce;
        this.nbrPotRouge=nbrPotRouge;
        this.nbrPotBleue=nbrPotBleue;
        this.direction=direction;
    }




    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHd() {
        return hd;
    }

    public void setHd(int hd) {
        this.hd = hd;
    }

    public int getVd() {
        return vd;
    }

    public void setVd(int vd) {
        this.vd = vd;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public int getMagie() {
        return magie;
    }

    public void setMagie(int magie) {
        this.magie = magie;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getGemme() {
        return gemme;
    }

    public void setGemme(int gemme) {
        this.gemme = gemme;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getSkillPoint() {
        return skillPoint;
    }

    public void setSkillPoint(int skillPoint) {
        this.skillPoint = skillPoint;
    }

    public int getNbrPotForce() {
        return nbrPotForce;
    }

    public void setNbrPotForce(int nbrPotForce) {
        this.nbrPotForce = nbrPotForce;
    }

    public int getNbrPotRouge() {
        return nbrPotRouge;
    }

    public void setNbrPotRouge(int nbrPotRouge) {
        this.nbrPotRouge = nbrPotRouge;
    }

    public int getNbrPotBleue() {
        return nbrPotBleue;
    }

    public void setNbrPotBleue(int nbrPotBleue) {
        this.nbrPotBleue = nbrPotBleue;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}
