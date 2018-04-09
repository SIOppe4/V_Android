package app.tobat.fr.tobat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import app.tobat.fr.tobat.Manager.ClientManager;
import app.tobat.fr.tobat.Model.Client;

/**
 * Created by Quentin Dubuc on 05/04/2018.
 */

public class NewClientActivity extends AppCompatActivity {

    private Client client;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private String adresseLn;
    private String ville;
    private String cp;
    private String commentaire;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_new);
        setTitle("Ajout d'un client");
        Intent intent = getIntent();
        client = (Client)intent.getSerializableExtra("client");

        EditText nom_input = (EditText) findViewById(R.id.nom);
        String nom = String.valueOf(nom_input.getText());

        EditText prenom_input = (EditText) findViewById(R.id.prenom);
        String prenom = String.valueOf(prenom_input.getText());

        EditText email_input = (EditText) findViewById(R.id.mail);
        String email = String.valueOf(email_input.getText());

        EditText tel_input = (EditText) findViewById(R.id.tel);
        String tel = String.valueOf(tel_input.getText());

        EditText adresse_input = (EditText) findViewById(R.id.adresse);
        String adresse = String.valueOf(adresse_input.getText());

        EditText adresseLn_input = (EditText) findViewById(R.id.adresseLn);
        String adresseLn = String.valueOf(adresseLn_input.getText());

        EditText ville_input = (EditText) findViewById(R.id.ville);
        String ville = String.valueOf(ville_input.getText());

        EditText cp_input = (EditText) findViewById(R.id.cp);
        String cp = String.valueOf(cp_input.getText());

        EditText commentaire_input = (EditText) findViewById(R.id.commentaire);
        String commentaire = String.valueOf(commentaire_input.getText());

        Client c;
        c = new Client(0 ,nom, prenom, adresse, adresseLn, ville, cp, email, tel, commentaire);

        ClientManager.

    }
}