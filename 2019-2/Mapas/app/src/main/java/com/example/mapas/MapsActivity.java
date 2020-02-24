package com.example.mapas;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.PolyUtil;

import java.util.Queue;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private ImageView icesi;
    private Polygon icesiArea;
    private Marker miUbicacion;
    private TextView distanciaTv;

    private Marker[] fifo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        icesi = findViewById(R.id.icesi);
        distanciaTv = findViewById(R.id.distancia);

        fifo = new Marker[2];

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
        }, 11);


    }

    @Override
    protected void onResume() {
        super.onResume();

        String texto = PreferenceManager.getDefaultSharedPreferences(this).getString("texto", "0");
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);

        // Coordenadas de icesi.
        //SUPIZQ 3.342954,-76.530753
        //SUPDER 3.343172,-76.527303
        //INFDER 3.338695,-76.527046
        //INFIZQR 3.338635,-76.531281

        icesiArea = mMap.addPolygon(new PolygonOptions().add(
                new LatLng(3.342954,-76.530753),
        new LatLng(3.343172,-76.527303 ),
        new LatLng(3.338695,-76.527046),
        new LatLng(3.338635,-76.531281)

        ));




        // Add a marker in Sydney and move the camera
        LatLng icesiInicio = new LatLng(3.342954,-76.530753);
        miUbicacion =  mMap.addMarker(new MarkerOptions().position(icesiInicio).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(icesiInicio, 15));


        //Pedir que el sensor comience a medir
        LocationManager manager = (LocationManager) getSystemService(LOCATION_SERVICE);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0,this);
    }

    //LOCATION UPDATES

    @Override
    public void onLocationChanged(Location location) {
        LatLng coord = new LatLng(location.getLatitude(), location.getLongitude());
        miUbicacion.setPosition(coord);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coord, 15));

        boolean isInIcesi = PolyUtil.containsLocation(coord, icesiArea.getPoints(), true);
        if(isInIcesi){
            icesi.setVisibility(View.VISIBLE);

        }else{
            icesi.setVisibility(View.GONE);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }


    //Long click
    @Override
    public void onMapLongClick(LatLng latLng) {
       Marker k =  mMap.addMarker(new MarkerOptions().position(latLng));


       if(fifo[1] !=null) fifo[1].remove();
       fifo[1] = fifo[0];
       fifo[0] = k;

       if(fifo[0] !=null && fifo[1] != null){
           double latDiff = fifo[0].getPosition().latitude - fifo[1].getPosition().latitude;
           double longDiff = fifo[0].getPosition().longitude - fifo[1].getPosition().longitude;
           double distancia = Math.sqrt(Math.pow(latDiff,2)+ Math.pow(longDiff, 2));
           distancia = distancia* 111.12 * 1000;
           distanciaTv.setText("Distancia: "+ distancia);

       }

    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onPause() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("texto", distanciaTv.getText().toString());
        editor.apply();

        super.onPause();
    }

}
