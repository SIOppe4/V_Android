package app.tobat.fr.tobat.Manager;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NoCache;

import org.json.JSONObject;

/**
 * Created by Augustin.dlt on 22/03/2018.
 */

public abstract class API  {
    static String url = "http://tobat.cleverapps.io/";
    static String apiKey = "a46c7c91-2b3c-4392-915e-e2a548b9ed49";
    static RequestQueue requestQueue = null;

    public abstract void receptData(JSONObject datas);


    public static RequestQueue getRequestQueue(){
        if (requestQueue == null){
            requestQueue = new RequestQueue(new NoCache(), new BasicNetwork(new HurlStack()));
            requestQueue.start();
        }
        return requestQueue;
    }


    public API(String prefixUrl, int method, JSONObject jsonToSend){

        String url = API.url + prefixUrl + "?key=" + API.apiKey;

        Log.i("API-url", API.url + prefixUrl);

        final API api = this;


        JsonObjectRequest request = new JsonObjectRequest(method, url, jsonToSend,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("API", "Api recpection");
                        api.receptData(response);
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

                Log.i("Info-Aug", "Erreur co");

            }
        });

        request.setShouldCache(false);
        getRequestQueue().add(request);
    }
}
