package com.example.milos.vezba;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;


/**
 * Created by Milos on 24-Jul-17.
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private FusedLocationProviderClient mFusedLocationClient;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    @Override
    public void onMapReady(final GoogleMap gmap) {
        gmap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        gmap.setTrafficEnabled(true);
        gmap.setIndoorEnabled(true);
        gmap.setBuildingsEnabled(true);
        gmap.getUiSettings().setCompassEnabled(true);
        gmap.getUiSettings().setZoomControlsEnabled(true);
        gmap.getUiSettings().setMyLocationButtonEnabled(true);
        gmap.getUiSettings().setMapToolbarEnabled(true);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        gmap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                // create marker,ROSE color icon,
                gmap.addMarker(new MarkerOptions().position(latLng).title("Hello Maps ").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));

            }
        });

        gmap.setMyLocationEnabled(true);

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            double latitude = location.getLatitude();
                            double longitude = location.getLongitude();
                            CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(latitude, longitude)).zoom(12).build();
                            gmap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        } else {
                            Toast.makeText(MapsActivity.this, "Please turn location on", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}