package app.tobat.fr.tobat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.Serializable;

import app.tobat.fr.tobat.Manager.BateauManager;
import app.tobat.fr.tobat.Model.Bateau;
import app.tobat.fr.tobat.Model.Client;

/*
* créer par Anthony
*/


public class NewBateauActivity extends AppCompatActivity {

    private Bateau bateaau;
    private String nom;
    private int annee;
    private String mesure;
    private String prix;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bateau_new);
        setTitle("Ajout d'un Bateau");
    }

    protected void sendNewBateau(View v) {
        EditText nom_input = (EditText) findViewById(R.id.nom);
        String nom_bat = String.valueOf(nom_input.getText());

        EditText date_input = (EditText) findViewById(R.id.date);
        int  annee_bat = Integer.parseInt(String.valueOf(date_input.getText()));

        EditText mesure_input = (EditText) findViewById(R.id.mesure);
        String dimentions_bat = String.valueOf(mesure_input.getText());

        EditText prix_input = (EditText) findViewById(R.id.prix);
        String  prix_bat = String.valueOf(prix_input.getText());

        EditText etat_input = (EditText) findViewById(R.id.etat);
        String etat_bat = String.valueOf(etat_input.getText());

        EditText modele_input = (EditText) findViewById(R.id.modele);
        String modele_bat = String.valueOf(modele_input.getText());

        EditText img_input = (EditText) findViewById(R.id.img);
        String img_bat = String.valueOf(img_input.getText());


        final Bateau bateau = new Bateau(0, annee_bat, dimentions_bat, etat_bat, modele_bat, nom_bat, prix_bat, img_bat);

        new BateauManager.newBateau(bateau) {
            @Override
            protected void getBateaux(Bateau b) {
                Intent intent = new Intent (getApplicationContext(), BateauActivity.class);
                intent.putExtra("bateau",(Serializable) bateau);
                startActivity(intent);
            }
        };
    }
}
