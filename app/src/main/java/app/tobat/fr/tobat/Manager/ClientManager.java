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

            new API("clients", Request.Method.GET, null) {
                @Override
                public void receptData(JSONObject datas) {
                    try {
                        JSONArray jsonArray = datas.getJSONArray("clients");
                        clients_list.clear();

                        for (int i = 0; i < jsonArray.length(); i++){

                            clients_list.add(JSONObjectToClient(jsonArray.getJSONObject(i)));
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.i("Info-Aug", "Erreur ");

                    }

                    getClients(clients_list);

                }
            };

        }

        protected abstract void getClients(ArrayList<Client> c);

    };

    public abstract static class addBateauToClient{
        public addBateauToClient(Client c, Bateau b){

            new API("client/" + c.getId() + "/ajout/bateau/" + b.getId(), Request.Method.GET, null) {
                @Override
                public void receptData(JSONObject datas) {
                    try {
                        getClient(JSONObjectToClient(datas));
                    }catch (JSONException e) {
                        e.printStackTrace();
                        Log.i("Info-Aug", "Erreur ");
                    }

                }
            };

        }

        protected abstract void getClient(Client c);
    }

    public abstract static class removeBateauToClient{

        public removeBateauToClient(Client c, Bateau b){

            new API("client/" + c.getId() + "/supression/bateau/" + b.getId(), Request.Method.GET, null) {
                @Override
                public void receptData(JSONObject datas) {

                    supressionTermine();

                }
            };

        }

        protected abstract void supressionTermine();

    }

    public abstract static class updateCommentaire{

        public updateCommentaire(Client c, String commentaire) {

            try {
                JSONObject json = new JSONObject();
                json.put("commentaire", commentaire);

                new API("client/" + c.getId() + "/ajout/commentaire", Request.Method.POST, json) {
                    @Override
                    public void receptData(JSONObject datas) {
                        try {
                            getClient(JSONObjectToClient(datas));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("Info-Aug", "Erreur ");
                        }
                    }
                };

            }catch (JSONException e){
                e.printStackTrace();
                Log.i("Info-Aug", "Erreur ");
            }

        }

        protected abstract void getClient(Client c);

    }

    public abstract static class updateClient{

        public updateClient(Client c) {

            try {
                JSONObject json = ClientManager.ClientToJSONObject(c);

                new API("client/" + c.getId() + "/update", Request.Method.POST, json) {
                    @Override
                    public void receptData(JSONObject datas) {
                        try {
                            getClient(JSONObjectToClient(datas));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("Info-Aug", "Erreur ");
                        }
                    }
                };

            }catch (JSONException e){
                e.printStackTrace();
                Log.i("Info-Aug", "Erreur ");
            }

        }

        protected abstract void getClient(Client c);

    }

    static Client JSONObjectToClient(JSONObject client_json) throws JSONException {

        Client client;
        Bateau bateau;

        int id_bat, annee_bat;
        String dimentions_bat, etat_bat, modele_bat, nom_bat, prix_bat, img_bat;
        JSONObject bateauJson;

        int id = client_json.getInt("id");
        String nom = client_json.getString("nom");
        String prenom = client_json.getString("prenom");
        String email = client_json.getString("email");
        String tel = client_json.getString("tel");
        String adresse = client_json.getString("adresse");
        String ville = client_json.getString("ville");
        String commentaire = client_json.getString("commentaire");
        String cp = client_json.getString("cp");
        String adresse_ln = client_json.getString("adresseLn");

        JSONArray bateauxJSON = client_json.getJSONArray("bateau");

        client = new Client(id, nom, prenom, adresse, adresse_ln, email, tel, commentaire, ville, cp);

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

            client.addBateau(bateau);
        }

        return client;
    };

    static JSONObject ClientToJSONObject(Client c) throws JSONException {
        JSONObject client_json = new JSONObject();
        client_json.put("nom", c.getNom());
        client_json.put("prenom", c.getPrenom());
        client_json.put("email", c.getEmail());
        client_json.put("tel", c.getTel());
        client_json.put("ville", c.getVille());
        client_json.put("adresse", c.getAdresse());
        client_json.put("cp", c.getCp());
        client_json.put("adresseLn", c.getAdresse_ln());

        return client_json;
    }


}
