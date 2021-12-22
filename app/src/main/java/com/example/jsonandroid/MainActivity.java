package com.example.jsonandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ItemModel> items;
    ItemAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        adapter = new ItemAdapter(items);


        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        new DownLoadTask(this).execute("https://lebavui.github.io/jsons/users.json");

    }


    class DownLoadTask extends AsyncTask<String, Void, List<ItemModel>> {

        ProgressDialog progressDialog;
        Context context;

        public DownLoadTask(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Loading data...");
            progressDialog.show();
        }

        @Override
        protected List<ItemModel> doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                String line;
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = reader.readLine()) != null)
                    builder.append(line);
                reader.close();

                String jsonString = builder.toString();

                JSONArray jArr = new JSONArray(jsonString);
                for (int i = 0; i < jArr.length(); ++i) {
                    JSONObject jObj = jArr.getJSONObject(i);

                    int id = jObj.getInt("id");
                    String username = jObj.getString("username");
                    String name = jObj.getString("name");
                    String email = jObj.getString("email");
                    String phone = jObj.getString("phone");
                    String company = jObj.getJSONObject("company").getString("name");
                    String address = jObj.getJSONObject("address").getString("street") +
                                     jObj.getJSONObject("address").getString("suite") +
                                     jObj.getJSONObject("address").getString("city");
                    String thumbnail = jObj.getJSONObject("avatar").getString("thumbnail");
                    String photo = jObj.getJSONObject("avatar").getString("photo");

                    ItemModel item = new ItemModel(id, username, name, email, address, photo, thumbnail, phone, company);

                    items.add(item);
                }

                Log.v("KHANH", String.valueOf(items));
                return items;


            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<ItemModel> items) {
            progressDialog.dismiss();
            if (items != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }
}