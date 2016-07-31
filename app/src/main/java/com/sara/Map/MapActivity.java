package com.sara.Map;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.sara.newconcepts.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity {

    GoogleMap map;
    StreetViewPanoramaFragment viewPanoramaFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);


        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        //SetupMap();
        //SetPolyLines();

        SetupStreetView();
        SetupFlatMode();
    }

    private void SetupStreetView() {


        viewPanoramaFragment =
                (StreetViewPanoramaFragment) getFragmentManager()
                        .findFragmentById(R.id.streetviewpanorama);
        viewPanoramaFragment.
                getStreetViewPanoramaAsync(new OnStreetViewPanoramaReadyCallback() {
                    @Override
                    public void onStreetViewPanoramaReady(StreetViewPanorama streetViewPanorama) {


                        streetViewPanorama.setPosition(new
                                LatLng(29.9774614, 31.1329645));
                        //streetViewPanorama.animateTo(new StreetViewPanoramaCamera(7, 5, 3000), 3000);
                    }


                });


    }

    private void SetupFlatMode() {

        map.setBuildingsEnabled(true);
        map.setIndoorEnabled(true);
        map.getUiSettings().setCompassEnabled(true);

        map.getUiSettings().setZoomControlsEnabled(true);
        LatLng latLng = new LatLng(-33.866, 151.195);
        map.addMarker(new MarkerOptions()

                .position(latLng)
                .flat(true));
        //.rotation(245));

        CameraPosition cameraPosition = CameraPosition.builder()
                .target(latLng)
                .zoom(13)
                .bearing(90)
                .build();

        // Animate the change in camera view over 2 seconds
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
                2000, null);
    }

    public void StreetView(View view) {
        Uri gmmIntentUri = Uri.parse
                ("google.streetview:cbll=29.9774614,31.1329645&cbp=0,30,0,0,-15");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    private void SetPolyLines() {
        map.addMarker(new MarkerOptions().position(new LatLng(-18.142, 178.431)));

        map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(-18.142, 178.431), 2), 2000, null);

        map.addPolyline(new PolylineOptions()
                .add(new LatLng(-33.866, 151.195))  // Sydney
                .add(new LatLng(-18.142, 178.431))  // Fiji
                .add(new LatLng(21.291, -157.821))  // Hawaii
                .add(new LatLng(37.423, -122.091))); // Mountain Vi
    }

    private void SetupMap() {


        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        double latitude = 30.0179962;
        double longitude = 31.4174193;
        LatLng latLng = new LatLng(latitude, longitude);

        final MarkerOptions options = new MarkerOptions();
        // options.icon(BitmapDescriptorFactory.fromResource(R.drawable.marker))
        //       .draggable(true);


        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(latLng);
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(12);
        options.position(latLng);
        options.draggable(true).anchor(0, 1);
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
                        //.bearing(60)
                .tilt(70)
                .build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition)
                , 3000, null);

        /*
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {

                marker.setSnippet(marker.getPosition().latitude + " , "
                        + marker.getPosition().longitude);

                Log.e("marker_pos", marker.getPosition().latitude + " , "
                        + marker.getPosition().longitude);
            }
        });*/

        map.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {

                Log.e("location", location.getLatitude() + " , "
                        + location.getLongitude());
            }
        });
                /*
        map.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

                options.snippet(cameraPosition.target.latitude + " , " +
                        cameraPosition.target.longitude);

                Log.e("camera_pos", cameraPosition.target.latitude +
                        " , " + cameraPosition.target.longitude);
            }
        });*/
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
