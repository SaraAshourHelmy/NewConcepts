package com.sara.Map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sara.newconcepts.R;

public class MapActivity extends AppCompatActivity {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SetupMap();
    }

    private void SetupMap() {

        map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();
        LatLng latLng = new LatLng(33.54587, 32.411125);
        MarkerOptions options = new MarkerOptions();
        options.title("test");
        options.snippet("map");
        options.position(latLng);

        map.addMarker(options);

    }
}
