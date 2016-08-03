package com.sara.Location;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.sara.newconcepts.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class LocationActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks, LocationListener {

    TextView tv_location;
    Button btn_get_location, btn_update_location;
    GoogleApiClient googleApiClient;
    LocationRequest locationRequest;
    Location location;
    boolean startUpdate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        SetupTools();
    }

    private void SetupTools() {
        tv_location = (TextView) findViewById(R.id.tv_location);
        btn_get_location = (Button) findViewById(R.id.btn_location);
        btn_update_location = (Button) findViewById(R.id.btn_update_location);

        Log.e("check_service", CheckPlayService() + "");
        if (CheckPlayService()) {
            BuildGoogleApiClient();
            RequestLocation();
        }

    }

    private boolean CheckPlayService() {

        int result_code = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (result_code != ConnectionResult.SUCCESS) {

            Toast.makeText(this, "Google Service Not Available", Toast.LENGTH_SHORT)
                    .show();
            return false;
        }

        return true;
    }

    private void BuildGoogleApiClient() {
        // Setup GoogleApiClient
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();

    }

    private void RequestLocation() {
        locationRequest = new LocationRequest();
        locationRequest.setFastestInterval(5000);
        locationRequest.setInterval(10000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setSmallestDisplacement(2);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected())
            googleApiClient.disconnect();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (googleApiClient != null)
            googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

        Toast.makeText(this, "Connect Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnected(Bundle bundle) {

        Toast.makeText(this, "Connect Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Connect Suspend", Toast.LENGTH_SHORT).show();
    }

    public void GetLocation(View view) {

        Log.e("get_location", "enter");
        if (googleApiClient.isConnected()) {
            DisplayLocation();
        }

    }

    private void DisplayLocation() {
        location
                = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        double lat = location.getLatitude();
        Log.e("lat", lat + "");
        double lng = location.getLongitude();
        Log.e("long", lng + "");

        tv_location.setText(lat + " , " + lng);
        DisplayName();
    }

    private void DisplayName() {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses =
                    geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            StringBuilder str = new StringBuilder();
            str.append(tv_location.getText().toString() + "\n");
            str.append(addresses.get(0).getCountryName());
            tv_location.setText(str);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void GetUpdateLocation(View view) {

        CheckUpdate();
    }

    private void CheckUpdate() {

        if (!startUpdate) {

            btn_update_location.setText(getResources().getString(R.string.stop_location));
            startUpdate = true;
            StartUpdate();

        } else {

            btn_update_location.setText(getResources().getString(R.string.start_location));
            startUpdate = false;
            StopUpdate();

        }
    }

    private void StartUpdate() {
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient,
                locationRequest, this);
    }

    private void StopUpdate() {
        LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient,
                this);
    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, "Location Changes", Toast.LENGTH_SHORT).show();
        DisplayLocation();
    }
}
