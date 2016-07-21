package com.sara.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sara.newconcepts.R;

/**
 * Created by Bassem on 7/20/2016.
 */
public class FragmentB extends Fragment {

    TextView tv_count;
    String data = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            data = "";

        } else {
            data = savedInstanceState.getString("data");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_b, container, false);
        tv_count = (TextView) view.findViewById(R.id.tv_count);
        tv_count.setText(data);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
      //  tv_count = (TextView) getActivity().findViewById(R.id.tv_count);
       // tv_count.setText(data);

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("data", data);
    }

    public void SetData(String data) {
        tv_count.setText(data);
        this.data = data;
    }
}
