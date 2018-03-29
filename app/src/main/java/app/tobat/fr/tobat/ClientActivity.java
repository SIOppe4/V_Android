package app.tobat.fr.tobat;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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

        TextView commentaire = (TextView) findViewById(R.id.commentaire_client);
        commentaire.setText(client.getCommentaire());

        Button addBoat = (Button) findViewById(R.id.add_ship_btn);

        CharSequence colors[] = new CharSequence[] {"La mouette", "Le Prius", "Le Lapin Vollant", "black Kenny", "Le Lapin Vollant", "black Kenny", "Le Lapin Vollant", "black Kenny", "Le Lapin Vollant", "black Kenny", "Le Lapin Vollant", "black Kenny", "Le Lapin Vollant", "black Kenny", "Le Lapin Vollant", "black Kenny", "Le Lapin Vollant", "black Kenny", "Le Lapin Vollant", "black Kenny", "Le Lapin Vollant", "black Kenny", "Le Lapin Vollant", "black Kenny"};

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Choisir un Bateau");
        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // the user clicked on colors[which]
            }
        });

        addBoat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                builder.show();
            }
        });

    }
}
