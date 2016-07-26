package com.sara.Map;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.sara.newconcepts.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity {

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SetupMap();
    }

    private void SetupMap() {


        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
        // map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        double latitude = 30.0179962;
        double longitude = 31.4174193;
        LatLng latLng = new LatLng(latitude, longitude);

        final MarkerOptions options = new MarkerOptions();
        // options.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
        //       .draggable(true);


        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(latLng);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(12);
        options.position(latLng);
        options.draggable(true);
        options.title("Map Test");
        options.snippet("New Cairo");
        options.flat(true);


        //  map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
        map.addMarker(options);
        // map.animateCamera(zoom);


        map.setOnMarkerDragListener(new GoogleMap.OnMarkerDragListener() {
            @Override
            public void onMarkerDragStart(Marker marker) {

            }

            @Override
            public void onMarkerDrag(Marker marker) {

                LatLng latLng1 = marker.getPosition();

                marker.setSnippet(latLng1.latitude + " , " + latLng1.longitude);
            }

            @Override
            public void onMarkerDragEnd(Marker marker) {

                LatLng latLng1 = marker.getPosition();

                //marker.setSnippet(latLng1.latitude + " , " + latLng1.longitude);
                marker.setSnippet(GetAddressName(latLng1));

            }
        });
        CameraPosition cameraPosition = CameraPosition.builder()
                .target(latLng)
                .zoom(13)
                .build();
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18),
                2000, null);
    }

    private String GetAddressName(LatLng latLng) {

        String addressName = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        try {
            List<Address> lst_address =
                    geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

            if (lst_address.size() > 0) {
                Address address = lst_address.get(0);
                if (address != null) {
                    addressName = address.getCountryName() + " , "
                            + address.getAdminArea() + ""
                            + address.getSubAdminArea();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressName;
    }
}
