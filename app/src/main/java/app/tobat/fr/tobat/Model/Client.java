package app.tobat.fr.tobat.Model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import app.tobat.fr.tobat.Manager.API;

/**
 * Created by Augustin.dlt on 22/03/2018.
 */

public class Client implements Serializable {

    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String tel;
    private String commentaire;

    private ArrayList<Bateau> bateaux;

    public Client(int id,String nom, String prenom, String adresse, String email, String tel, String commentaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.tel = tel;
        this.commentaire = commentaire;
        this.bateaux = new ArrayList<Bateau>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public ArrayList<Bateau> getBateaux() { return this.bateaux; }

    public void setBateaux(ArrayList<Bateau> bateaux) { this.bateaux = bateaux; }

    public void addBateau(Bateau b) { this.bateaux.add(b); }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
