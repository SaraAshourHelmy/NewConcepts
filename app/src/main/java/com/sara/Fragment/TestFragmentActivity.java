package com.sara.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

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

    public void TestClick(View view) {
        Toast.makeText(this, "Hi you are in Click", Toast.LENGTH_SHORT).show();
    }

    private void Steps() {
        /*

        1- create obj from myFragment
        2- get FragmentManager()
        3- Create FragmentTransaction
        4- transaction.add(layout that we load in , fragment obj , fragment tag)
        4- transaction.commit()

         */

        /*
        *  transaction has many methods like
        *  1- .add : to associate fragment with activity , lifecycle
        *       onAttach - onCreate - onCreateView - onActivityCreated
        *
        *  2- .remove : to remove association with fragment and activity , lifecycle
        *     onPause - onStop - onDestroyView - onDestroy - onDetach
        *
        *  3- .replace : to replace fragment with another , lifecycle
        *     destroy the old fragment then add the second to replace it
        *
        *  4- .attach : to attach fragment already added before , it's lifecycle
        *         onCreateView - onActivityCreated , there is no attach
        *  5- .detach : to detach fragment , lifecycle is
        *        onPause - onStop - onDestroyView
        *        no destroy or detach so if attach again fragment not be destroy
        *        completely
        * */
    }
}
