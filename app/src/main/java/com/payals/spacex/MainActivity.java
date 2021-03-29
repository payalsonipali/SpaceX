package com.payals.spacex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Database;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String baseurl="https://api.spacexdata.com/v4";
    CardView refresh,delete;
    List<User> users;
    AppDatabase db;
    UserDao userDao;
    Recycler_Adapter recycler_adapter;
    RecyclerView recyclerView;
    ArrayList<ParseUser> parseUserArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        delete = findViewById(R.id.delete);
        refresh = findViewById(R.id.refresh);
        recyclerView = findViewById(R.id.recycler);
        parseUserArrayList=new ArrayList<>();
        recycler_adapter = new Recycler_Adapter(MainActivity.this,parseUserArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycler_adapter);

         db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "user").build();

        new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object[] objects) {
                userDao = db.userDao();
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                changeuser();
            }
        }.execute();


//        prepareList();
//        roomstore();
//        roomget();
//        fill_array_list();
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                fill_array_list();


            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AsyncTask<Object, Object, Object>() {
                    @Override
                    protected Object doInBackground(Object[] objects) {
                        userDao.deleteAll();
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Object o) {
                        super.onPostExecute(o);
                        users.clear();
                        parseUserArrayList.clear();
                        recycler_adapter.notifyDataSetChanged();
                    }
                }.execute();
            }
        });
    }




    private void fill_array_list() {
        JsonArrayRequest request = new JsonArrayRequest(baseurl+"/crew", new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                traversethrough(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("JSONerror", "onErrorResponse: "+error);
                Toast.makeText(MainActivity.this, "Please try again later", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(request);

    }

    private void changeuser(){

        new AsyncTask<Object, Object, Object>() {
            @Override
            protected Object doInBackground(Object[] objects) {

                if(users!=null) {
                    users.clear();
                    parseUserArrayList.clear();
                }
                users = userDao.getAll();
                return null;

            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);

                for(int i =0;i<users.size();++i){
                    ParseUser user = new ParseUser();
                    user.setAgency(users.get(i).agency);
                    user.setName(users.get(i).name);
                    user.setId((users.get(i).id));
                    user.setWikipedia(users.get(i).wikipedia);
                    user.setImage(users.get(i).image);
                    user.setStatus(users.get(i).status);
                    parseUserArrayList.add(user);
                }
                recycler_adapter.notifyDataSetChanged();

            }
        }.execute();

    }

    private void traversethrough(final JSONArray response) {

        new AsyncTask<Object, Object, Object>(){
            @Override
            protected Object doInBackground(Object[] objects) {
                try {
                    for(int i=0;i<response.length();++i){
                        JSONObject object = (JSONObject) response.get(i);
                        User u = new User();
                        u.agency = object.getString("agency");
                        u.status = object.getString("status");
                        u.id = (object.getString("id"));
                        u.image = object.getString("image");
                        u.name = object.getString("name");
                        Log.d("anananan", "doInBackground: "+u.id+"  "+u.name);
                        u.wikipedia = "<a href='"+object.getString("wikipedia")+"'> Wikipedia Link </a>";
                        if(userDao.findById(u.id).size()==0){
                            userDao.insertAll(u);
                            Log.d("ananan", "doInBackground: "+123213);
                        }

                    }
                }catch (Exception e){
                    Log.d("error", "parseresponse: "+e);
                }

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {

                super.onPostExecute(o);


                changeuser();

            }
        }.execute();


    }
}