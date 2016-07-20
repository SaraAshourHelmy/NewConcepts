package com.sara.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sara.newconcepts.R;

public class TestFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_fragment);
        SetupFragment();
    }

    private void SetupFragment() {

        MyFragment myFragment = new MyFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.lnr_fragment, myFragment, "");
        transaction.commit();
    }

    private void Steps()
    {
        /*

        1- create obj from myFragment
        2- get FragmentManager()
        3- Create FragmentTransaction
        4- transaction.add(layout that we load in , fragment obj , fragment tag)
        4- transaction.commit()

         */
    }
}
