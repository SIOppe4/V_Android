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
 * Created by Maxime BOUCHET on 29/03/2018.
 */

public class UpdateClientActivity extends AppCompatActivity{

    private Client client;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_update);
        setTitle("Modification d'un client");
        Intent intent = getIntent();
        client = (Client)intent.getSerializableExtra("client");

        EditText nom = (EditText) findViewById(R.id.nom);
        nom.setText(client.getNom());

        EditText prenom = (EditText) findViewById(R.id.prenom);
        prenom.setText(client.getPrenom());

        EditText mail = (EditText) findViewById(R.id.mail);
        mail.setText(client.getEmail());

        EditText tel = (EditText) findViewById(R.id.tel);
        tel.setText(client.getTel());

        EditText adresse = (EditText) findViewById(R.id.adresse);
        adresse.setText(client.getAdresse());

        EditText adresse_ln = (EditText) findViewById(R.id.adresse_ln);
        adresse_ln.setText(client.getAdresse_ln());

        EditText ville = (EditText) findViewById(R.id.ville);
        ville.setText(client.getVille());

        EditText cp = (EditText) findViewById(R.id.cp);
        cp.setText(client.getCp());

    }

    public void envoyerValeurs(){

        new ClientManager.updateClient(client) {
            @Override
            protected void getClient(Client c) {
                Intent intent = new Intent (getApplicationContext(), ClientActivity.class);
                intent.putExtra("client",(Serializable) c);
                startActivity(intent);
            }
        };

    }

    public void verifierValeurs(View v){

        String nom, prenom, mail, tel, adresse, cp, ville, adresse_ln;

        EditText nomV = (EditText) findViewById(R.id.nom);
        EditText prenomV = (EditText) findViewById(R.id.prenom);
        EditText mailV = (EditText) findViewById(R.id.mail);
        EditText telV = (EditText) findViewById(R.id.tel);
        EditText adresseV = (EditText) findViewById(R.id.adresse);
        EditText cpV = (EditText) findViewById(R.id.cp);
        EditText villeV = (EditText) findViewById(R.id.ville);
        EditText adress_lnV = (EditText) findViewById(R.id.adresse_ln);

        nom=nomV.getText().toString();
        client.setNom(nom);
        prenom=prenomV.getText().toString();
        client.setPrenom(prenom);
        mail=mailV.getText().toString();
        client.setEmail(mail);
        tel=telV.getText().toString();
        client.setTel(tel);
        adresse=adresseV.getText().toString();
        client.setAdresse(adresse);
        cp=cpV.getText().toString();
        client.setCp(cp);
        ville=villeV.getText().toString();
        client.setVille(ville);
        adresse_ln=adress_lnV.getText().toString();
        client.setAdresse_ln(adresse_ln);

        envoyerValeurs();
    }
}
