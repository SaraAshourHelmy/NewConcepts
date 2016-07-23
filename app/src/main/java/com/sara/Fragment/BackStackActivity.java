package com.sara.Fragment;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.sara.newconcepts.R;

/**
 * Created by Bassem on 7/21/2016.
 */
public class BackStackActivity extends Activity implements
        FragmentManager.OnBackStackChangedListener {

    TextView tv_message;
    FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_stack);
        tv_message = (TextView) findViewById(R.id.tv_message);
        manager = getFragmentManager();
        manager.addOnBackStackChangedListener(this);

    }

    public void addA(View view) {
        FragmentTransaction transaction = manager.beginTransaction();
        FragmentA fragmentA = new FragmentA();
        transaction.add(R.id.lnr_group, fragmentA, "A");
        transaction.addToBackStack("addA");
        transaction.commit();

    }

    public void removeA(View view) {

        FragmentTransaction transaction = manager.beginTransaction();
        FragmentA fragmentA = (FragmentA) manager.findFragmentByTag("A");
        if (fragmentA != null) {

            transaction.remove(fragmentA);
            transaction.addToBackStack("removeA");
            transaction.commit();
        }
    }

    public void addB(View view) {

        FragmentTransaction transaction = manager.beginTransaction();
        FragmentB fragmentB = new FragmentB();
        transaction.add(R.id.lnr_group, fragmentB, "B");
        transaction.addToBackStack("addB");
        transaction.commit();

    }

    public void removeB(View view) {

        FragmentTransaction transaction = manager.beginTransaction();
        FragmentB fB = (FragmentB) manager.findFragmentByTag("B");
        if (fB != null) {

            transaction.remove(fB);
            transaction.addToBackStack("removeB");
            transaction.commit();
        }
    }

    public void replaceWithB(View view) {

        FragmentTransaction transaction = manager.beginTransaction();
        FragmentB fragmentB = new FragmentB();
        transaction.replace(R.id.lnr_group, fragmentB);
        transaction.addToBackStack("replaceB");
        transaction.commit();
    }

    public void back(View view) {

        manager.popBackStack();
    }

    public void popB(View view) {

        // FragmentManager.POP_BACK_STACK_INCLUSIVE : this flag delete addB also from back
        // stack , but flag 0 delete all after addB and back to addB not delete it
        manager.popBackStack("addB",FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

    @Override
    public void onBackStackChanged() {

        String txt = "";
        int count = manager.getBackStackEntryCount();
        for (int i = count - 1; i >= 0; i--) {
            FragmentManager.BackStackEntry entry = manager.getBackStackEntryAt(i);
            txt += entry.getName() + "\n";
        }

        tv_message.setText(txt);
    }
}
