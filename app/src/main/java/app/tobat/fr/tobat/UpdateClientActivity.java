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
        prenom=prenomV.getText().toString();
        mail=mailV.getText().toString();
        tel=telV.getText().toString();
        adresse=adresseV.getText().toString();
        cp=cpV.getText().toString();
        ville=villeV.getText().toString();
        adresse_ln=adress_lnV.getText().toString();

        if (nom.isEmpty() || prenom.isEmpty() || mail.isEmpty() || tel.isEmpty() || adresse.isEmpty() || cp.isEmpty() || ville.isEmpty()){
            Toast.makeText(UpdateClientActivity.this, "Merci de remplir les champs", Toast.LENGTH_LONG).show();
        }
        else {
            client.setNom(nom);
            client.setPrenom(prenom);
            client.setEmail(mail);
            client.setTel(tel);
            client.setAdresse(adresse);
            client.setCp(cp);
            client.setVille(ville);
            client.setAdresse_ln(adresse_ln);
            envoyerValeurs();
        }
    }
}
