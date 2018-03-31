package app.tobat.fr.tobat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

import app.tobat.fr.tobat.Adapter.ClientBateauxListAdapter;
import app.tobat.fr.tobat.Manager.BateauManager;
import app.tobat.fr.tobat.Manager.ClientManager;
import app.tobat.fr.tobat.Model.Bateau;
import app.tobat.fr.tobat.Model.Client;

/**
 * Created by Augustin.dlt on 22/03/2018.
 */

public class BateauActivity extends AppCompatActivity {
    private Bateau bateau;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        setTitle("Fiche bateau");

        setContentView(R.layout.bateau_activity);

        bateau = (Bateau) intent.getSerializableExtra("bateau");

        setBateauToView();
    }

    private void setBateauToView(){

        TextView nom_bateau = (TextView) findViewById(R.id.nom_bateau);
        nom_bateau.setText(bateau.getNom());

        TextView model_bateau = (TextView) findViewById(R.id.model_bateau);
        model_bateau.setText(bateau.getModele());

        TextView annee_bateau = (TextView) findViewById(R.id.annee_bateau);
        annee_bateau.setText(String.valueOf(bateau.getAnnee()));

        TextView dimensions_bateau = (TextView) findViewById(R.id.dimensions_bateau);
        dimensions_bateau.setText(bateau.getDimentions());

        TextView prix_bateau = (TextView) findViewById(R.id.prix_bateau);
        prix_bateau.setText(bateau.getPrix() + " en état " + bateau.getEtat());

        ImageView image_bateau = (ImageView) findViewById(R.id.image_bateau);
        Picasso.get().load(bateau.getImg()).fit().centerCrop().into(image_bateau);

    }

}
