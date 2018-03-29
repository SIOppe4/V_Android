package app.tobat.fr.tobat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import app.tobat.fr.tobat.Manager.BateauManager;
import app.tobat.fr.tobat.Model.Bateau;
import app.tobat.fr.tobat.Model.Client;

/**
 * Created by Augustin.dlt on 22/03/2018.
 */

public class ClientActivity extends AppCompatActivity {
    private Client client;
    private ArrayList<Bateau> bateaux_list;
    private AlertDialog.Builder builder_list_bat;
    private CharSequence bateaux_list_char_bat[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        client = (Client)intent.getSerializableExtra("client");
        bateaux_list = new ArrayList<Bateau>();
        builder_list_bat = new AlertDialog.Builder(this);

        setContentView(R.layout.client_activity);

        putInfoClientToView();

    }

    protected void showCHooseListBateaux(View v){

        Log.i("TEXT", "ON CLICK");

        new BateauManager.all() {
            @Override
            protected void getBateaux(ArrayList<Bateau> b) {

                ArrayList<String> strs = new ArrayList<String>();
                for(Bateau bat : b) {
                    strs.add(bat.getNom());
                }

                bateaux_list_char_bat = strs.toArray(new CharSequence[strs.size()]);
                Log.i("BATEAUX", String.valueOf(b.size()));
                builder_list_bat.setTitle("Choisir un Bateau");
                builder_list_bat.setItems(bateaux_list_char_bat, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder_list_bat.show();
            }
        };

    }

    private void putInfoClientToView(){
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
    }
}
