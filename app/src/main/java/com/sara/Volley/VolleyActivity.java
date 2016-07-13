package com.sara.Volley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.sara.models.Category;
import com.sara.models.CategoryAdapter;
import com.sara.newconcepts.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class VolleyActivity extends AppCompatActivity implements
        Response.Listener, Response.ErrorListener {

    // NetworkImageView set only url -- if want get bitmap from volley
    // set it in ImageView
    ImageView img_volley;
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

        img_volley = (ImageView) findViewById(R.id.img_volley);
        SetupImageVolley();
        // GetBitmapVolley();


    }

    private void SetupStringRequest() {

        mrequest = MyCustomRequest.getInstance(this).getRequestQueue();

        myRequest = new MyStringRequest(Request.Method.GET, url, this, this, params);
        myRequest.SetPriority(Request.Priority.HIGH);
        mrequest.add(myRequest);

    }

    private void SetupImageVolley() {

        ImageLoader imageLoader = MyCustomRequest.getInstance(this).getImageLoader();

        //ImageLoader.getImageListener(img_volley,
        // R.drawable.img, R.drawable.img)
        // this line to set img before download
        imageLoader.get(img_url, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
                img_volley.setImageBitmap(response.getBitmap());
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        // below line in case of NetworkImageView
        //img_volley.setImageUrl(img_url, imageLoader);
    }

    private void GetBitmapVolley() {

        ImageRequest request = new ImageRequest(img_url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {

                Log.e("img", "done");
                img_volley.setImageBitmap(response);
            }
        }, 0, 0, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("img_error", error + "");
            }
        });
        mrequest.add(request);
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
