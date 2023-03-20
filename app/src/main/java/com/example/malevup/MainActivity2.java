package com.example.malevup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    final static String userVariableKey = "USER_VARIABLE";

    private AdapterQuote pAdapter;

    private List<MaskQuote> listQuote = new ArrayList<>();

    private AdapterFeeling dataRVAdapter;

    private List<MaskFeeling> listFeeling = new ArrayList<>();

    ImageView imageProfile;

    TextView textHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ListView ivProducts = findViewById(R.id.lvQuotes);
        pAdapter = new AdapterQuote(MainActivity2.this, listQuote);
        ivProducts.setAdapter(pAdapter);
        new GetQuotes().execute();

        RecyclerView rvFeeling = findViewById(R.id.recyclerView);
        rvFeeling.setHasFixedSize(true);
        rvFeeling.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        dataRVAdapter = new AdapterFeeling(listFeeling, MainActivity2.this);

        rvFeeling.setAdapter(dataRVAdapter);

        new GetFeeling().execute();

        imageProfile = findViewById(R.id.ivProfile);

        new AdapterQuote.DownloadImageTask((ImageView) imageProfile).execute(LoginActivity.User.getAvatar());

        textHello = findViewById(R.id.hello);
        textHello.setText(textHello.getText().toString() + LoginActivity.User.getNickName() + "!");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        outState.putString(userVariableKey, "fgfggf");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        String str = savedInstanceState.getString(userVariableKey);
        textHello.setText(str);
    }

    private class GetQuotes extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids)
        {
            try
            {
                URL url = new URL("http://mskko2021.mad.hakta.pro/api/quotes");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }

                return result.toString();
            }

            catch (Exception exception)
            {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try
            {
                listQuote.clear();
                pAdapter.notifyDataSetInvalidated();

                JSONObject object = new JSONObject(s);
                JSONArray tempArray  = object.getJSONArray("data");

                for (int i = 0;i<tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);
                    MaskQuote tempProduct = new MaskQuote(
                            productJson.getInt("id"),
                            productJson.getString("title"),
                            productJson.getString("image"),
                            productJson.getString("description")
                    );
                    listQuote.add(tempProduct);
                    pAdapter.notifyDataSetInvalidated();
                }

            }

            catch (Exception exception)
            {
                Toast.makeText(MainActivity2.this, "При выводе данных возникла ошибка", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class GetFeeling extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("http://mskko2021.mad.hakta.pro/api/feelings");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null)
                {
                    result.append(line);
                }

                return result.toString();
            }

            catch (Exception exception)
            {
                return null;
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(String s)
        {
            super.onPostExecute(s);

            try
            {
                listFeeling.clear();
                dataRVAdapter.notifyDataSetChanged();

                JSONObject object = new JSONObject(s);
                JSONArray tempArray  = object.getJSONArray("data");

                for (int i = 0;i<tempArray.length();i++)
                {
                    JSONObject productJson = tempArray.getJSONObject(i);

                    MaskFeeling tempProduct = new MaskFeeling(
                            productJson.getInt("id"),
                            productJson.getString("title"),
                            productJson.getString("image"),
                            productJson.getInt("position")
                    );

                    listFeeling.add(tempProduct);
                    dataRVAdapter.notifyDataSetChanged();
                }

                listFeeling.sort(Comparator.comparing(MaskFeeling::getPosition));
                dataRVAdapter.notifyDataSetChanged();
            }

            catch (Exception exception)
            {
                Toast.makeText(MainActivity2.this, "При выводе данных возникла ошибка", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public  void nextMenu(View v)
    {
        startActivity(new Intent(this, MenuActivity.class));
    }

    public  void nextProfile(View v)
    {
        startActivity(new Intent(this, ProfileActivity.class));
    }

    public void nextListen(View v)
    {
        startActivity(new Intent(this, MusicActivity.class));
    }
}