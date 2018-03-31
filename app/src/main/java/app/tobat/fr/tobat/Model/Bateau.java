package app.tobat.fr.tobat.Model;

import java.io.Serializable;

/**
 * Created by Augustin.dlt on 29/03/2018.
 */

public class Bateau implements Serializable {
    private int id;
    private int annee;
    private String dimentions;
    private String etat;
    private String modele;
    private String nom;
    private String prix;
    private String img;

    public Bateau(int id, int annee, String dimentions, String etat, String modele, String nom, String prix, String img){
        this.id = id;
        this.annee = annee;
        this.dimentions = dimentions;
        this.etat = etat;
        this.modele = modele;
        this.nom = nom;
        this.prix = prix;
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getDimentions() {
        return dimentions;
    }

    public void setDimentions(String dimentions) {
        this.dimentions = dimentions;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
