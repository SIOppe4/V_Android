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
/*
* Update by Anthony
*/

public class BateauManager {

    public abstract static class all{
        public all(){
            final ArrayList<Bateau> bateaux_list = new ArrayList<Bateau>();
            final BateauManager.all manager = this;

            new API("bateaux", Request.Method.GET, null) {
                @Override
                public void receptData(JSONObject datas) {
                    try {
                        JSONArray bateauxJSON = datas.getJSONArray("bateaux");
                        bateaux_list.clear();

                        Bateau bateau;

                        int id, annee_bat;
                        String dimentions_bat, etat_bat, modele_bat, nom_bat, prix_bat, img_bat;
                        JSONObject bateauJson;


                        for (int i = 0; i < bateauxJSON.length(); i++){

                            bateauJson = bateauxJSON.getJSONObject(i);
                            id = bateauJson.getInt("id");
                            annee_bat = bateauJson.getInt("annee");
                            nom_bat = bateauJson.getString("nom");
                            dimentions_bat = bateauJson.getString("dimensions");
                            etat_bat = bateauJson.getString("etat");
                            modele_bat = bateauJson.getString("modele");
                            prix_bat = bateauJson.getString("prix");
                            img_bat = bateauJson.getString("img");

                            bateau = new Bateau(id, annee_bat, dimentions_bat, etat_bat, modele_bat, nom_bat, prix_bat, img_bat);

                            Log.i("CHARGE", "Bateau " + bateau.getNom());

                            bateaux_list.add(bateau);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.i("Info-Aug", "Erreur ");

                    }

                    Log.i("SIZE -Bateaux", String.valueOf(bateaux_list.size()));

                    manager.getBateaux(bateaux_list);

                }
            };

        }
        protected abstract void getBateaux(ArrayList<Bateau> b);
    };


    static Bateau JSONObjectToBateau(JSONObject bateau_json) throws JSONException {

        Client client;
        Bateau bateau;

        int id_bat, annee_bat;
        String dimentions_bat, etat_bat, modele_bat, nom_bat, prix_bat, img_bat;
        JSONObject bateauJson;

        id_bat = bateau_json.getInt("id");
        nom_bat = bateau_json.getString("nomBat");
        annee_bat = bateau_json.getInt("anneeBat");
        dimentions_bat = bateau_json.getString("dimmensionBat");
        prix_bat = bateau_json.getString("prixBat");
        etat_bat = bateau_json.getString("etatBat");
        modele_bat = bateau_json.getString("modeleBat");


        JSONArray bateauxJSON = bateau_json.getJSONArray("bateau");

        bateau = new Bateau(id_bat, nom_bat, annee_bat, dimentions_bat, prix_bat, etat_bat,modele_bat);



        for (int j = 0; j < bateauxJSON.length(); j++){

            bateauJson = bateauxJSON.getJSONObject(j);
            id_bat = bateauJson.getInt("id");
            annee_bat = bateauJson.getInt("annee");
            nom_bat = bateauJson.getString("nom");
            dimentions_bat = bateauJson.getString("dimensions");
            etat_bat = bateauJson.getString("etat");
            modele_bat = bateauJson.getString("modele");
            prix_bat = bateauJson.getString("prix");
            img_bat = bateauJson.getString("img");


            bateau = new Bateau(id_bat, annee_bat, dimentions_bat, etat_bat, modele_bat, nom_bat, prix_bat, img_bat);

            Log.i("DATA", String.valueOf(bateauJson));

            Log.i("DATA-Bat", String.valueOf(bateau.getNom()));
        }

        return bateau;
    };






    public abstract static class newBateau{

        public newBateau(Bateau b) {

            try {
                JSONObject json = BateauToJSONObject(b);
                json.put("commentaire", b.getCommentaire());

                new API("bateau/new/bateau", Request.Method.POST, json) {
                    @Override
                    public void receptData(JSONObject datas) {
                        try {
                            getBateaux(JSONObjectToBateau(datas));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("ERROR", "Erreur de parse JSON ");
                        }
                    }
                };

            }catch (JSONException e){
                e.printStackTrace();
                Log.i("ERROR", "Erreur de parse JSON ");
            }

        }

        protected abstract void getBateaux(Client c);







}
