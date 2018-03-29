package app.tobat.fr.tobat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import java.io.Serializable;
import java.util.ArrayList;

import app.tobat.fr.tobat.Adapter.ClientListAdapter;
import app.tobat.fr.tobat.Manager.ClientManager;
import app.tobat.fr.tobat.Model.Client;

/**
 * Created by Augustin.dlt on 22/03/2018.
 */

public class ClientListActivity extends AppCompatActivity {

    ClientListAdapter listAdapter;
    ArrayList<Client> clients;
    String m_Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client_list_activity);

        clients = new ArrayList<Client>();

        ListView lvClients = (ListView) findViewById(R.id.list_clients);

        listAdapter = new ClientListAdapter(this, R.layout.client_list_item, clients);

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

        this.setClientsList();
    };

    public void addClient(View v){

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

    public void onResume(){
        this.setClientsList();
        super.onResume();
    }

    private void setClientsList(){

        new ClientManager.all() {
            @Override
            protected void getClients(ArrayList<Client> c) {
                clients.clear();
                clients.addAll(c);
                listAdapter.notifyDataSetChanged();
            }
        };
    }
}
