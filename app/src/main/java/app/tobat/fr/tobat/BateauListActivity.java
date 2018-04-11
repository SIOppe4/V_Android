package app.tobat.fr.tobat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

import app.tobat.fr.tobat.Adapter.BateauListAdapter;
import app.tobat.fr.tobat.Manager.BateauManager;
import app.tobat.fr.tobat.Model.Bateau;

/**
 * Created by Augustin.dlt on 22/03/2018.
 */

public class BateauListActivity extends AppCompatActivity {

    BateauListAdapter listAdapter;
    ArrayList<Bateau> bateaux;
    private ProgressDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bateau_list_activity);
        setTitle("Liste des bateaux");

        bateaux = new ArrayList<Bateau>();

        ListView lvBateaux = (ListView) findViewById(R.id.list_bateaux);

        listAdapter = new BateauListAdapter(this, R.layout.list_item, bateaux);

        lvBateaux.setAdapter(listAdapter);

        listAdapter.notifyDataSetChanged();

        lvBateaux.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                Intent BateauIntent = new Intent(BateauListActivity.this, BateauActivity.class);

                BateauIntent.putExtra("bateau", (Serializable) bateaux.get(position));

                startActivity(BateauIntent);

            }
        });

        loadingDialog = new ProgressDialog(this);

        loadingDialog.setMessage("Veuillez patienter...");

        this.setBateauxList();
    };



    public void onResume(){
        this.setBateauxList();
        super.onResume();
    }

    private void setBateauxList(){

        loadingDialog.show();

        new BateauManager.all() {
            @Override
            protected void getBateaux(ArrayList<Bateau> b) {
                bateaux.clear();
                bateaux.addAll(b);
                listAdapter.notifyDataSetChanged();
                loadingDialog.hide();
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_client, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        finish();

        return true;
    }

}
