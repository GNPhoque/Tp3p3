package l.jordan.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

public class UsersActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        UserRecyclerAdapter uAdapter = new UserRecyclerAdapter(listUsers(), R.layout.item_user);
        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(uAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private List<User> listUsers(){
        final List<User> list = new ArrayList<User>();
        Fuel.get("https://jsonplaceholder.typicode.com/users").responseString(new Handler<String>() {
            @Override
            public void failure(Request request, Response response, FuelError error) {
                //do something when it is failure
                list.clear();
                list.add(new User(1, "lala", "lala"));
                list.add(new User(1, "lili", "lili"));
                recyclerView.getAdapter().notifyDataSetChanged();
            }
            @Override
            public void success(Request request, Response response, String data) {
                //do something when it is successful
                try {
                    JSONArray arr = new JSONArray(new String(response.getData()));
                    for (int i=0; i < arr.length(); i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        User u = new User(obj.getInt("id"),obj.getString("name"), obj.getString("username"));
                        list.add(u);
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
