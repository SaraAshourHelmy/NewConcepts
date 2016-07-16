package com.sara.Gson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.ExecutorDelivery;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;
import com.sara.Volley.MyCustomRequest;
import com.sara.Volley.MyStringRequest;
import com.sara.Volley.VolleyActivity;
import com.sara.newconcepts.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class GsonActivity extends AppCompatActivity {

    String url = "http://shaklakaklak.com/api/categories/all/?lastupdate=" + 0;
    Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gson);

        SetupGson();

    }

    private void SetupGson() {

        // test1
        GsonBuilder builder = new GsonBuilder();
        // this below line to check expose annotation
        builder.excludeFieldsWithoutExposeAnnotation();
        builder.serializeNulls();
        gson = builder.create();
        CategoryGson category = new CategoryGson(1, "sea food");
        Log.e("json1", gson.toJson(category));

        RequestWebService();


    }

    private void RequestWebService() {
        RequestQueue request = MyCustomRequest.getInstance(this).getRequestQueue();
        MyStringRequest stringRequest = new MyStringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray arr = object.getJSONArray("update");
                    //ParseJson(arr);
                    ParseJsonArr(arr);
                } catch (Exception e) {
                    Log.e("json_parse_error", e + "");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, null);
        request.add(stringRequest);
    }

    private void ParseJson(JSONArray arr) {
        try {
            for (int i = 0; i < arr.length(); i++) {

                Log.e("json_parse", arr.get(i).toString());
                CategoryGson cat = gson.fromJson(arr.get(i).toString(), CategoryGson.class);

                String json = cat.cat_id + "\n" + cat.cat_name_en + "\n"
                        + ((cat.cat_name_ar.equals("")) ? "empty" : cat.cat_name_ar)
                        + "\n" + cat.section_id
                        + "\n" + cat.img;
                Log.e("after_parse", json);
            }
        } catch (Exception e) {
            Log.e("parse_error", e + "");
        }
    }

    private void ParseJsonArr(JSONArray arr) {
        // Deserialization
        Type collectionType = new TypeToken<ArrayList<CategoryGson>>() {
        }.getType();
        ArrayList<CategoryGson> lst_categories = gson.fromJson(arr.toString()
                , collectionType);

        for (int i = 0; i < lst_categories.size(); i++) {

            CategoryGson cat = lst_categories.get(i);

            String json = cat.cat_id + "\n" + cat.cat_name_en + "\n"
                    + ((cat.cat_name_ar.equals("")) ? "empty" : cat.cat_name_ar)
                    + "\n" + cat.section_id
                    + "\n" + cat.img;
            Log.e("parse_arr", json);
        }
    }
}
