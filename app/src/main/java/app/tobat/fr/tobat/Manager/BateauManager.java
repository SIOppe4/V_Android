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

                            bateaux_list.add(bateau);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.i("ERROR", "Erreur de parse JSON");

                    }

                    manager.getBateaux(bateaux_list);

                }
            };

        }
        protected abstract void getBateaux(ArrayList<Bateau> b);
    };


}
