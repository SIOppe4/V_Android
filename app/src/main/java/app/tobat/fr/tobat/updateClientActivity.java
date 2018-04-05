package app.tobat.fr.tobat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import app.tobat.fr.tobat.Model.Client;

/**
 * Created by Maxime BOUCHET on 29/03/2018.
 */

public class updateClientActivity extends AppCompatActivity{

    private Client client;
    private String nom;
    private String prenom;
    private String mail;
    private String telephone;
    private String adresse;
    private String ville;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_update);
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

        EditText ville = (EditText) findViewById(R.id.ville);
        ville.setText(client.getVille());

    }

    public void recupererValeurs(View v){

        EditText nom = (EditText)findViewById(R.id.nom);
        this.nom = nom.getText().toString();
        EditText prenom = (EditText)findViewById(R.id.prenom);
        this.prenom = prenom.getText().toString();
        EditText mail = (EditText)findViewById(R.id.mail);
        this.mail = mail.getText().toString();
        EditText telephone = (EditText)findViewById(R.id.tel);
        this.telephone = telephone.getText().toString();
        EditText adresse = (EditText)findViewById(R.id.adresse);
        this.adresse = nom.getText().toString();
        EditText ville = (EditText)findViewById(R.id.ville);
        this.ville = ville.getText().toString();




        Log.i("nom : ", this.nom);
        Log.i("prenom : ", this.prenom);
        Log.i("mail : ", this.mail);
        Log.i("telephone : ", this.telephone);
        Log.i("adresse : ", this.adresse);
        Log.i("ville : ", this.ville);


    }
}
