package app.tobat.fr.tobat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

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

    }


    protected void sendNewClient(View v) {
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

        if (nom.isEmpty() || prenom.isEmpty() || email.isEmpty() || tel.isEmpty() || adresse.isEmpty() || cp.isEmpty() || ville.isEmpty()){
            Toast.makeText(NewClientActivity.this, "Merci de remplir les champs", Toast.LENGTH_LONG).show();
        }
        else {
            Client client = new Client(0, nom,  prenom,  adresse, adresseLn, email, tel, commentaire, ville , cp);
        }


        new ClientManager.newClient(client) {
            @Override
            protected void getClient(Client c) {
                Intent intent = new Intent (getApplicationContext(), ClientActivity.class);
                intent.putExtra("client",(Serializable) c);
                startActivity(intent);
            }
        };
    }
}