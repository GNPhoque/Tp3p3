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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PhotosActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    int albumId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        Intent data = getIntent();
        albumId = data.getIntExtra("albumId",0);

        PhotoRecyclerAdapter pAdapter = new PhotoRecyclerAdapter(listPhotos(), R.layout.item_photo);
        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(pAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private List<Photo> listPhotos(){
        final List<Photo> list = new ArrayList<Photo>();
        Fuel.get("https://jsonplaceholder.typicode.com/photos").responseString(new Handler<String>() {
            @Override
            public void failure(Request request, Response response, FuelError error) {
                //do something when it is failure
                list.clear();
                list.add(new Photo(1, 1, "lala", "lala"));
                list.add(new Photo(1, 1, "lili", "lili"));
                recyclerView.getAdapter().notifyDataSetChanged();
            }
            @Override
            public void success(Request request, Response response, String data) {
                //do something when it is successful
                try {
                    JSONArray arr = new JSONArray(new String(response.getData()));
                    for (int i=0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        if(obj.getInt("albumId") == albumId)
                            list.add(new Photo(obj.getInt("id"),obj.getInt("albumId"), obj.getString("title"), obj.getString("url")));
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
