package app.tobat.fr.tobat.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import app.tobat.fr.tobat.Model.Bateau;
import app.tobat.fr.tobat.Model.Client;
import app.tobat.fr.tobat.R;

/**
 * Created by Augustin.dlt on 22/03/2018.
 */

public class ClientBateauxListAdapter extends ArrayAdapter<Bateau> {
    List<Bateau> b;
    Context c;
    int r;

    public ClientBateauxListAdapter(Context context, int resource, ArrayList<Bateau> objects) {
        super(context, resource, objects);
        this.b = objects;
        this.c = context;
        this.r = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) c).getLayoutInflater();
        View v = inflater.inflate(r, parent, false);

        Bateau el = b.get(position);

        TextView nom_bateau_list = (TextView) v.findViewById(R.id.nom_bateau_list);

        nom_bateau_list.setText(el.getNom());

        ImageView image_bateau_list = (ImageView) v.findViewById(R.id.image_bateau_list);


        Picasso.get()
                .load(el.getImg())
                .fit()
                .centerCrop()
                .into(image_bateau_list);


        return v;
    }
}
