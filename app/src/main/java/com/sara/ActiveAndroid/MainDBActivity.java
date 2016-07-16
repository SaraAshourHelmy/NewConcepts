package com.sara.ActiveAndroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.sara.newconcepts.R;

import java.util.List;

public class MainDBActivity extends AppCompatActivity {

    CategoryDB categoryDB1;
    TextView tv_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_db);
        tv_items = (TextView) findViewById(R.id.tv_items);
        SetupDb();
        GetData();
    }

    private void SetupDb() {

        try {
            // set category and insert them in database
            categoryDB1 = new CategoryDB(1, "SeaFood");
            categoryDB1.save();
            CategoryDB categoryDB2 = new CategoryDB(2, "Chicken");
            categoryDB2.save();

            // set items and insert them in database
            // cat1
            Item item = new Item(1, "fish", categoryDB1, "red");
            item.save();
            item = new Item(2, "tuna", categoryDB1, "yellow");
            item.save();
            item = new Item(3, "salmon", categoryDB1, "blue");
            item.save();

            //cat2
            item = new Item(4, "fried chicken", categoryDB2,"red");
            item.save();
            item = new Item(5, "grill", categoryDB2,"red");
            item.save();

            Log.e("db", "done");
        } catch (Exception e) {
            Log.e("db_error", e + "");
        }


    }

    private void GetData() {
        List<Item> items = categoryDB1.getItem();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {

            stringBuilder.append(items.get(i).remote_id + " - " +
                    items.get(i).name + " - " + items.get(i).color + "\n");
        }

        tv_items.setText(stringBuilder);
    }


}
