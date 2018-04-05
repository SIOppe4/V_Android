package app.tobat.fr.tobat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

import app.tobat.fr.tobat.Model.Client;

/**
 * Created by Augustin.dlt on 22/03/2018.
 */

public class ClientActivity extends AppCompatActivity {
    private Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        client = (Client)intent.getSerializableExtra("client");
        setContentView(R.layout.client_activity);

        TextView prenom = (TextView) findViewById(R.id.prenom_client);
        prenom.setText(client.getPrenom());

        TextView nom = (TextView) findViewById(R.id.nom_client);
        nom.setText(client.getNom().toUpperCase());

        TextView email = (TextView) findViewById(R.id.email_client);
        email.setText(client.getEmail());

        TextView tel = (TextView) findViewById(R.id.tel_client);
        tel.setText(client.getTel());

        TextView adresse = (TextView) findViewById(R.id.adresse_client);
        adresse.setText(client.getAdresse());

    }

    public void goToUpdateClient(View v){
        Log.i("INFO MAX", "Clicked floating button");
        Intent intent = new Intent (getApplicationContext(), updateClientActivity.class);
        intent.putExtra("client",(Serializable) client);
        startActivity(intent);
    }
}
