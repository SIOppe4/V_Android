package app.tobat.fr.tobat.Model;

import java.io.Serializable;

/**
 * Created by Augustin.dlt on 17/01/2018.
 */

public class Client implements Serializable {
    private String nom;
    private String prenom;
    private String adresse;
    private String email;
    private String tel;

    public Client(String nom, String prenom, String adresse, String email, String tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.email = email;
        this.tel = tel;
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
}
