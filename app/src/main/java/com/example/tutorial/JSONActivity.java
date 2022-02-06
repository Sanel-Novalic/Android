package com.example.tutorial;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONActivity extends AppCompatActivity {
    String jsonStr;
    final static String urlContacts = "https://api.androidhive.info/contacts/";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonfile);

        jsonStr = loadJSONFromRaw();

        if (jsonStr != null) {
            createList(jsonStr);
            showList();
        }

        new GetContacts().execute();
    }

    private void showList() {
        TextView tv = findViewById(R.id.jsonText);
        for(int i=1;i<10;i++){
            tv.append(contactList.get(i).get("name") + "\n");
        }
    }

    ArrayList<HashMap<String, String>> contactList;

    private String loadJSONFromRaw() {
        String json = "";
        try {
            InputStream is = getResources().openRawResource(R.raw.contacts);
            InputStreamReader ir = new InputStreamReader(is);
            BufferedReader buffer = new BufferedReader(ir);
            String line;
            while ((line = buffer.readLine()) != null) {
                json += line;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    private void createList(String jsonstr) {
        contactList = new ArrayList<>();
        if (jsonStr != null) {
            try {
                JSONObject jsonobj = new JSONObject(jsonStr);
                JSONArray contacts = jsonobj.getJSONArray("contacts");
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);
                    String id = c.getString("id");
                    String name = c.getString("name");
                    String email = c.getString("email");
                    JSONObject phone = c.getJSONObject("phone");
                    String mobile = phone.getString("mobile");
                    HashMap<String, String> contact = new HashMap<>();
                    contact.put("id", id);
                    contact.put("name", name);
                    contact.put("email", email);
                    contact.put("mobile", mobile);
                    contactList.add(contact);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(this, "Couldn't get json from file.",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public class GetContacts extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            if(jsonStr!=null){
                createList(jsonStr);
                showList();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpURLConnection conn = null;
            InputStream inputStream = null;
            InputStreamReader isReader = null;
            BufferedReader reader = null;

            try {

                URL url = new URL(urlContacts);

                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                inputStream = conn.getInputStream();

                StringBuilder buffer = new StringBuilder();
                if(inputStream == null){
                    return null;
                }
                isReader = new InputStreamReader(inputStream);
                reader = new BufferedReader(isReader);
                String line;
                while((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }

                if(buffer.length() == 0) return null;

                jsonStr = buffer.toString();
            } catch (MalformedURLException e) {}
            catch(IOException e) { e.printStackTrace();}
            finally {
                if(conn!=null) { conn.disconnect(); }
                if(reader!=null){
                    try{
                        reader.close();
                    } catch (final IOException e) {}
                }
            }
            return null;
        }
    }

}
