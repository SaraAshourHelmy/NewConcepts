package com.sara.Volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.sara.models.Category;
import com.sara.models.CategoryAdapter;
import com.sara.newconcepts.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class VolleyActivity extends AppCompatActivity implements
        Response.Listener, Response.ErrorListener {

    NetworkImageView img_volley;
    ListView lstV_category;
    RequestQueue mrequest;
    MyJsonRequest jsonRequest;
    MyStringRequest myRequest;
    String email, password;
    String url = "http://shaklakaklak.com/api/categories/all/?lastupdate=" + 0;
    String login_url;
    HashMap<String, String> params = new HashMap<>();
    String img_url = "http://shaklakaklak.com/upload/images/icon01@2X.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_volley);
        SetupStringRequest();
        //SetupJsonRequest();
        // mrequest.cancelAll("json");
        SetupImageVolley();


    }

    private void SetupStringRequest() {

        mrequest = MyCustomRequest.getInstance(this).getRequestQueue();

        myRequest = new MyStringRequest(Request.Method.GET, url, this, this, params);
        myRequest.SetPriority(Request.Priority.HIGH);
        mrequest.add(myRequest);

    }

    private void SetupImageVolley() {
        img_volley = (NetworkImageView) findViewById(R.id.img_volley);
        ImageLoader imageLoader = MyCustomRequest.getInstance(this).getImageLoader();

        // this line to set img before download
        imageLoader.get(img_url, ImageLoader.getImageListener(img_volley,
                R.drawable.img, R.drawable.img));
        img_volley.setImageUrl(img_url, imageLoader);
    }

    private void SetupJsonRequest() {

        email = "sara.ashour.89@gmail.com";
        password = "12345";

        login_url = "http://shaklakaklak.com/api/users/login?email=" + email +
                "&password=" + password;
        Log.e("login_url", login_url);
        mrequest = MyCustomRequest.getInstance(this).getRequestQueue();
        jsonRequest = new MyJsonRequest(Request.Method.POST, login_url, new JSONObject(),
                this, this);
        jsonRequest.setTag("json");
        mrequest.add(jsonRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {

        Log.e("error", error.networkResponse.statusCode + "");
    }

    @Override
    public void onResponse(Object response) {


        Log.e("response", response.toString());
        SetupListCategory(ParseCategories(response.toString()));
    }

    private void SetupListCategory(ArrayList<Category> lst_category) {

        ImageLoader imageLoader = MyCustomRequest.getInstance(this).getImageLoader();
        lstV_category = (ListView) findViewById(R.id.lstV_category);
        CategoryAdapter adapter = new CategoryAdapter(this, R.layout.adapter_category,
                lst_category, imageLoader);
        lstV_category.setAdapter(adapter);


    }

    private ArrayList<Category> ParseCategories(String json) {
        ArrayList<Category> lst_category = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray array = jsonObject.getJSONArray("update");
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);
                String name = obj.getString("cat_name_en");
                String icon_url = obj.getString("image");
                lst_category.add(new Category(name, icon_url));
            }
        } catch (Exception e) {
            Log.e("parsing_error", e + "");
        }
        return lst_category;
    }
}
