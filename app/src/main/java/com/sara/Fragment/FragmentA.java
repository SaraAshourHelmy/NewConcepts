package com.sara.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sara.newconcepts.R;

/**
 * Created by Bassem on 7/20/2016.
 */
public class FragmentA extends Fragment implements View.OnClickListener {

    Button btn_click;
    int count = 0;
    Communicator com;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            count = 0;
        } else {
            count = savedInstanceState.getInt("count", 0);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_a, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        com = (Communicator) getActivity();
        btn_click = (Button) getActivity().findViewById(R.id.btn_click);
        btn_click.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("count", count);
    }

    @Override
    public void onClick(View v) {

        if (v == btn_click) {

            count++;
            com.response("you Clicked " + count + " time");
        }
    }
}
