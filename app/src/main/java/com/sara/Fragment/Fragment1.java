package com.sara.Fragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sara.newconcepts.R;

/**
 * Created by Bassem on 7/21/2016.
 */
public class Fragment1 extends Fragment implements View.OnClickListener {

    Button btn_open;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frag1, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        btn_open = (Button) getActivity().findViewById(R.id.btn_open);
        btn_open.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == btn_open) {

            FragmentB fragmentB = new FragmentB();
            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.rltv_group, fragmentB, "B");
            transaction.addToBackStack("B");
            transaction.commit();
        }
    }
}
