package app.tobat.fr.tobat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import app.tobat.fr.tobat.Model.Client;

/**
 * Created by Augustin.dlt on 16/01/2018.
 */

public class ClientListActivity extends AppCompatActivity {

    ClientListAdapter listAdapter;
    ArrayList <Client> clients;
    String m_Text;
    private RequestQueue requestQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_list_activity);

        requestQ = Volley.newRequestQueue(this);

        clients = new ArrayList<Client>();

        ListView lvClients = (ListView) findViewById(R.id.list_clients);

        listAdapter = new ClientListAdapter(this, R.layout.item, clients);

        lvClients.setAdapter(listAdapter);

        listAdapter.notifyDataSetChanged();

        lvClients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                TextView text = (TextView)view.findViewById(R.id.nom_client);

                Intent ClientIntent = new Intent(ClientListActivity.this, ClientActivity.class);

                ClientIntent.putExtra("client", (Serializable) clients.get(position));

                startActivity(ClientIntent);

            }
        });

        this.parseJSON();
    };

    public void addClient(View v){
        parseJSON();
    }

    public void addClients(View v){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ajout d'un Client");

        // Set up the input
        final EditText input = new EditText(this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                m_Text = input.getText().toString();

                //clients.add(new Client(m_Text, " "));

                //listAdapter.notifyDataSetChanged();
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

    private void parseJSON() {
        String url = "http://augustin.jpage.eu/tobat/clients.json";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("clients");


                            for (int i = 0; i < jsonArray.length(); i++){

                                JSONObject clientJson = jsonArray.getJSONObject(i);
                                String nom = clientJson.getString("nom");
                                String prenom = clientJson.getString("prenom");
                                String email = clientJson.getString("email");
                                String tel = clientJson.getString("tel");
                                String adresse = clientJson.getString("adresse");

                                clients.add(new Client(nom, prenom, adresse, email, tel));
                                listAdapter.notifyDataSetChanged();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("Info-Aug", "Erreur ");

                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                        Log.i("Info-Aug", "Erreur co");

                    }
        });

        requestQ.add(request);
    }
}
