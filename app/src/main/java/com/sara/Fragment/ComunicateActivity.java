package com.sara.Fragment;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sara.newconcepts.R;

public class ComunicateActivity extends AppCompatActivity implements
        Communicator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comunicate);
    }

    @Override
    public void response(String data) {

        FragmentManager fragmentManager = getFragmentManager();
        FragmentB fragmentB = (FragmentB)
                fragmentManager.findFragmentById(R.id.fragment_b);
        fragmentB.SetData(data);
        
        // Create ArrayAdapter direct from resource array
        // ArrayAdapter.createFromResource(this, R.array.items,
        //       android.R.layout.simple_expandable_list_item_1);
    }
}
