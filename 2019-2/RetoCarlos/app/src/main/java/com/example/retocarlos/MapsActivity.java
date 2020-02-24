package com.example.retocarlos;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements LocationListener, OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    private GoogleMap mMap;
    private Geocoder geocoder;
    private LocationManager manager;

    private List<Address> direcciones;
    private Marker marcadorPrincipal;

    private EditText nombreMarcador_et;
    private Button botonAgregar_btn;
    private TextView distancia_tv;

    private Marker nuevoMarcador;
    private ArrayList<Marker> listaMarcadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        geocoder = new Geocoder(this, Locale.getDefault());
        manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        listaMarcadores = new ArrayList<Marker>();

        nombreMarcador_et = findViewById(R.id.nombreMarcador_et);
        distancia_tv = findViewById(R.id.distancia_tv);
        botonAgregar_btn = findViewById(R.id.botonAgregar_btn);
        nombreMarcador_et.setEnabled(false);
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
        }, 11);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 11);
        } else {
            manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            String closest = "";
            LatLng postAct = new LatLng(location.getLatitude(), location.getLongitude());
            marcadorPrincipal.setPosition(postAct);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(postAct, 15));
            Marker marcadorS = onClickMarker();

            try {
                if (marcadorS != null)  getDistanceToMarker();
                if(marcadorS==null) closest = showNearest();
                direcciones = geocoder.getFromLocation(marcadorPrincipal.getPosition().
                        latitude, marcadorPrincipal.getPosition().longitude, 1);
                String d = direcciones.get(0).getAddressLine(0).split(",")[0];
                marcadorPrincipal.setSnippet(d);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                double delta = Double.parseDouble(closest.split("-")[1]);
                if (delta < 100.0) {
                    distancia_tv.setText("Usted se encuentra en: " + closest.split("-")[0]);
                }
            } catch (Exception e) {
                e.printStackTrace();
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
    };



    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapLongClickListener(this);
        LatLng icesi = new LatLng(3.343050, -76.530852);
        marcadorPrincipal = mMap.addMarker(new MarkerOptions().position(icesi).title("Usted esta aqui"));
        marcadorPrincipal.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.marcador));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(icesi, 15));
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        nuevoMarcador = mMap.addMarker(new MarkerOptions().position(latLng));
        nombreMarcador_et.setEnabled(true);
        botonAgregar_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nuevoMarcador.setTitle(nombreMarcador_et.getText().toString());
                String toast= nombreMarcador_et.getText().toString();
                listaMarcadores.add(nuevoMarcador);
                nombreMarcador_et.setText("");
                nombreMarcador_et.setEnabled(false);
                Toast.makeText(MapsActivity.this, " El marcador " +toast + "ha sido agregado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Obtengo la distancia  de mi ubicación actual, a el marcador seleccionado.
    public void getDistanceToMarker() {
        Marker m = onClickMarker();
        if (m != null) {
            double distanciaPunto = distanceFromMarker(m);
            String dist = distanciaPunto + "";
            try {
                direcciones = geocoder.getFromLocation(m.getPosition().latitude, m.getPosition().longitude, 1);
                String address = direcciones.get(0).getAddressLine(0).split(",")[0];
                m.setSnippet(" Usted se encuentra aproximadamente a: " + dist.toString().substring(0, 6)
                        + " metros de distancia de" + ".\'" + address);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public Marker onClickMarker() {
        boolean clickMarker = false;
        Marker marker = null;
        for (int i = 0; i < listaMarcadores.size() && !clickMarker; i++) {
            if (listaMarcadores.get(i) != null) {
                if (listaMarcadores.get(i).isInfoWindowShown()) {
                    clickMarker = true;
                    marker = listaMarcadores.get(i);
                }
            }
        }
        return marker;
    }

    public String showNearest(){
        HashMap<Double, Marker> hashMapDistance = new HashMap<Double, Marker>();
        String resultado = "";
        ArrayList<Double> values = new ArrayList<>();
        for (int i = 0; i < listaMarcadores.size(); i++){
            if(listaMarcadores.get(i)!=null){
                double distance = distanceFromMarker(listaMarcadores.get(i));
                hashMapDistance.put(distance, listaMarcadores.get(i));
                values.add(distance);
            }
        }
        double small =0.0;
        double compare=0.0;
        for (int i = 0; i < values.size(); i++){
            compare=values.get(i);
            for (int j= 0; j < values.size(); j++){
                if(compare<values.get(j)){
                    small=compare;
                }
            }
        }
        Marker markerNear = hashMapDistance.get(small);
        String dist = small + "";
        String laDist = dist.toString().substring(0, 6);
        if(markerNear!=null){
            distancia_tv.setText("El lugar más cercano es: " + markerNear.getTitle().toString() + " ,y esta a: " + laDist + " metros de ti.");
            resultado = markerNear.getTitle().toString() + "-" + small;
        }else{
            resultado = "No hay un lugar cercano actualmente";
        }
        return resultado;
    }



    public double distanceFromMarker(Marker m) {
        double latDiff = marcadorPrincipal.getPosition().latitude - m.getPosition().latitude;
        double longDiff = marcadorPrincipal.getPosition().longitude - m.getPosition().longitude;
        double dp = Math.sqrt(Math.pow(latDiff, 2) + Math.pow(longDiff, 2));
        double d = dp * 111.12 * 1000;
        return d;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onLocationChanged(Location location) {

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
}