package app.tobat.fr.tobat.Manager;

import android.util.Log;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import app.tobat.fr.tobat.Model.Bateau;
import app.tobat.fr.tobat.Model.Client;

/**
 * Created by Augustin.dlt on 28/03/2018.
 */

public class ClientManager {

    public abstract static class all{
        public all(){
            final ArrayList<Client> clients_list = new ArrayList<Client>();
            final ClientManager.all manager = this;
            new API("clients", Request.Method.GET) {
                @Override
                public void receptData(JSONObject datas) {
                    try {
                        JSONArray jsonArray = datas.getJSONArray("clients");
                        clients_list.clear();
                        ArrayList<Bateau> bateaux_list = new ArrayList<Bateau>();

                        Client client;
                        Bateau bateau;

                        int id, id_bat, annee_bat;
                        String nom, prenom, email, tel, adresse, commentaire;
                        String dimentions_bat, etat_bat, modele_bat, nom_bat, prix_bat;
                        JSONArray bateauxJSON;
                        JSONObject clientJson;
                        JSONObject bateauJson;

                        for (int i = 0; i < jsonArray.length(); i++){

                            clientJson = jsonArray.getJSONObject(i);
                            id = clientJson.getInt("id");
                            nom = clientJson.getString("nom");
                            prenom = clientJson.getString("prenom");
                            email = clientJson.getString("email");
                            tel = clientJson.getString("tel");
                            adresse = clientJson.getString("adresse");
                            commentaire = clientJson.getString("commentaire");

                            bateauxJSON = clientJson.getJSONArray("bateau");

                            client = new Client(id, nom, prenom, adresse, email, tel, commentaire);

                            for (int j = 0; j < bateauxJSON.length(); j++){

                                bateauJson = bateauxJSON.getJSONObject(j);
                                id_bat = bateauJson.getInt("id");
                                annee_bat = bateauJson.getInt("annee");
                                nom_bat = bateauJson.getString("nom");
                                dimentions_bat = bateauJson.getString("dimensions");
                                etat_bat = bateauJson.getString("etat");
                                modele_bat = bateauJson.getString("modele");
                                prix_bat = bateauJson.getString("prix");

                                bateau = new Bateau(id_bat, annee_bat, dimentions_bat, etat_bat, modele_bat, nom_bat, prix_bat);

                                Log.i("DATA", String.valueOf(bateauJson));

                                Log.i("DATA-Bat", String.valueOf(bateau.getNom()));

                                client.addBateau(bateau);
                            }

                            clients_list.add(client);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.i("Info-Aug", "Erreur ");

                    }

                    manager.getClients(clients_list);

                }
            };

        }
        protected abstract void getClients(ArrayList<Client> c);
    };


}
