package app.tobat.fr.tobat;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.tobat.fr.tobat.Model.Client;

/**
 * Created by Augustin.dlt on 16/01/2018.
 */

public class ClientListAdapter extends ArrayAdapter<Client> {

    List<Client> l;
    Context c;
    int r;

    public ClientListAdapter(Context context, int resource, ArrayList<Client> objects) {
        super(context, resource, objects);
        this.l = objects;
        this.c = context;
        this.r = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) c).getLayoutInflater();
        View v = inflater.inflate(r, parent, false);

        Client el = l.get(position);

        TextView nom_client = (TextView) v.findViewById(R.id.nom_client);

        nom_client.setText(el.getPrenom() + " " + el.getNom());

        return v;
    }
}
