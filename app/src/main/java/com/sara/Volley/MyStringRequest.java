package com.sara.Volley;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Bassem on 7/12/2016.
 */
public class MyStringRequest extends StringRequest {

    HashMap<String, String> params;
    Priority priority;

    public MyStringRequest(int method, String url, Response.Listener<String> listener
            , Response.ErrorListener errorListener, HashMap<String, String> params) {

        super(method, url, listener, errorListener);
        this.params = params;
    }

    public void SetPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {

        Log.e("header_string", "done");
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; charset=utf-8");
        return headers;
    }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Log.e("param_string", "done");
        HashMap<String, String> param = new HashMap<>();
        param.put("lastupdate", "0");
        return param;
    }

    @Override
    public Priority getPriority() {


        return priority;
    }

    @Override
    public RetryPolicy getRetryPolicy() {
        return super.getRetryPolicy();
    }
}
