package app.tobat.fr.tobat.Manager;

import android.util.Log;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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

                        String nom, prenom, email, tel, adresse, commentaire;

                        for (int i = 0; i < jsonArray.length(); i++){

                            JSONObject clientJson = jsonArray.getJSONObject(i);
                            nom = clientJson.getString("nom");
                            prenom = clientJson.getString("prenom");
                            email = clientJson.getString("email");
                            tel = clientJson.getString("tel");
                            adresse = clientJson.getString("adresse");
                            commentaire = clientJson.getString("commentaire");

                            clients_list.add(new Client(nom, prenom, adresse, email, tel, commentaire));
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
