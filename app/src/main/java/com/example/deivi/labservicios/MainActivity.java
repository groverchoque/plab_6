package com.example.deivi.labservicios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import collections.Item;
import collections.ListAdapter;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
     loadComponents ();
    }

    private void loadComponents() {
      final ArrayList<Item> list_data = new ArrayList<Item> ();
      /* for (int i = 0; i < 100; i++){
           Item p = new Item ();
           p.id = i;
           p.title = "Titulo" + i;
           p.description = "Descripcion" + i;
           p.url = "image" + i;
           list_data.add (p);
       }
        ListAdapter adapter = new ListAdapter (this, list_data);

        list.setAdapter (adapter);*/
        AsyncHttpClient client = new AsyncHttpClient ();
        client.get ("http://52.67.169.228:8000",  new JsonHttpResponseHandler (){
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                try {
                    JSONArray list = response.getJSONArray ("data");
                    for (int i=0; i < list.length (); i ++){
                        Item p = new Item ();
                        JSONObject obj = list.getJSONObject (i);
                        p.setId (i);
                        p.setTitle (obj.getString ("title"));
                        p.setDescription (obj.getString ("descripcion"));
                        p.setUrl (obj.getString ("image"));
                        list_data.add (p);
                    }
                    ListAdapter adapter = new ListAdapter (MainActivity.this, list_data);
                    ListView list_ui = MainActivity.this.findViewById (R.id.list_main);
                    list_ui.setAdapter (adapter);
                } catch (JSONException e) {
                    e.printStackTrace ();
                }



            }
            public  void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse){

            }
        });



    }

}
