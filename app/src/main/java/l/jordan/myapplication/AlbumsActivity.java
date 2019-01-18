package l.jordan.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AlbumsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    int userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        Intent data = getIntent();
        userId = data.getIntExtra("userId",0);

        AlbumRecyclerAdapter aAdapter = new AlbumRecyclerAdapter(listAlbums(), R.layout.item_album);
        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(aAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private List<Album> listAlbums(){
        final List<Album> list = new ArrayList<Album>();
        Fuel.get("https://jsonplaceholder.typicode.com/albums").responseString(new Handler<String>() {
            @Override
            public void failure(Request request, Response response, FuelError error) {
                //do something when it is failure
                list.clear();
                list.add(new Album(1, 1, "lala"));
                list.add(new Album(1, 1, "lili"));
                recyclerView.getAdapter().notifyDataSetChanged();
            }
            @Override
            public void success(Request request, Response response, String data) {
                //do something when it is successful
                try {
                    JSONArray arr = new JSONArray(new String(response.getData()));
                    for (int i=0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        if(obj.getInt("userId") == userId){
//                        list.add(new Album(obj.getInt("id"),obj.getInt("userId"), obj.getString("title")));
                            Gson gson = new Gson();
                            Album a = gson.fromJson(obj.toString(), Album.class);
                            list.add(a);
                        }
                    }
                    recyclerView.getAdapter().notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return list;
    }
}
