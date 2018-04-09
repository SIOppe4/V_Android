package app.tobat.fr.tobat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import java.io.Serializable;
import java.util.ArrayList;
import app.tobat.fr.tobat.Adapter.ClientBateauxListAdapter;
import app.tobat.fr.tobat.Manager.BateauManager;
import app.tobat.fr.tobat.Manager.ClientManager;
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
    private ClientBateauxListAdapter bateaux_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        client = (Client)intent.getSerializableExtra("client");

        setContentView(R.layout.client_activity);
        setTitle("Fiche client");

        client = (Client)intent.getSerializableExtra("client");
        bateaux_list = new ArrayList<Bateau>();
        builder_list_bat = new AlertDialog.Builder(this);

        putInfoClientToView();
        putInfoBateauxToView();

    }

    protected void showCHooseListBateaux(View v){


        new BateauManager.all() {
            @Override
            protected void getBateaux(final ArrayList<Bateau> b) {

                ArrayList<String> strs = new ArrayList<String>();
                for(Bateau bat : b) {
                    strs.add(bat.getNom());
                }

                bateaux_list_char_bat = strs.toArray(new CharSequence[strs.size()]);
                builder_list_bat.setTitle("Choisir un Bateau");
                builder_list_bat.setItems(bateaux_list_char_bat, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        new ClientManager.addBateauToClient(client, b.get(which)) {
                            @Override
                            protected void getClient(Client c) {
                                client.addBateau(c.getBateaux().get(c.getBateaux().size()-1));
                                bateaux_adapter.notifyDataSetChanged();
                            }
                        };

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
        adresse.setText(client.getAdresse() + "\n" + client.getAdresse_ln() + "\n" + client.getCp() + " " + client.getVille());
        
        TextView commentaire = (TextView) findViewById(R.id.commentaire_client);
        commentaire.setText(client.getCommentaire());

    }

    private void putInfoBateauxToView(){
        GridView bateaux_grid = (GridView) findViewById(R.id.client_bateaux_list);

        bateaux_adapter = new ClientBateauxListAdapter(this, R.layout.client_bateaux_list_item, client.getBateaux());

        bateaux_grid.setAdapter(bateaux_adapter);

        bateaux_adapter.notifyDataSetChanged();

        bateaux_grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                builder_list_bat.setTitle("Que voulez vous faire ?");
                builder_list_bat.setItems(new String[]{
                        "Enlever le bateau",
                        "Consulter le bateau",
                        "Annuler"},
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // Enlever le Bateau a été selectionné
                                if (which == 0){
                                    new ClientManager.removeBateauToClient(client, client.getBateaux().get(position)) {
                                        @Override
                                        protected void supressionTermine() {
                                            client.getBateaux().remove(position);
                                            bateaux_adapter.notifyDataSetChanged();
                                        }
                                    };
                                }

                                // Consulter le bateau a été selectionné
                                if (which == 1){

                                    Intent BateauIntent = new Intent(ClientActivity.this, BateauActivity.class);

                                    BateauIntent.putExtra("bateau", (Serializable) client.getBateaux().get(position));

                                    startActivity(BateauIntent);

                                }

                            }
                        }
                );

                builder_list_bat.show();

            }
        });

    }

    protected void changeCommentToClient(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater factory = LayoutInflater.from(this);
        final View modal = (View) factory.inflate(R.layout.modal_commentaire, null);
        final EditText input = (EditText) modal.findViewById(R.id.edit_comment);

        builder.setTitle("Modification du commentaire");
        builder.setView(modal);
        input.setText(client.getCommentaire(), TextView.BufferType.EDITABLE);


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String commentaire = input.getText().toString();

                new ClientManager.updateCommentaire(client, commentaire) {
                    @Override
                    protected void getClient(Client c) {
                        client.setCommentaire(c.getCommentaire());
                        putInfoClientToView();
                    }
                };

            }
        });

        builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();

    }


    public void goToUpdateClient(View v){
        Log.i("INFO MAX", "Clicked floating button");
        Intent intent = new Intent (getApplicationContext(), UpdateClientActivity.class);
        intent.putExtra("client",(Serializable) client);
        startActivity(intent);
    }
}
