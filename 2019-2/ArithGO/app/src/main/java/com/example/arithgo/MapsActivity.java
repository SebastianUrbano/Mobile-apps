package com.example.arithgo;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.PolyUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int REQUEST_CODE = 11;
    private Geocoder geocoder;
    private LocationManager manager;
    private Marker personalMarker, customPositionMarker;
    private LatLng personalPosition,customPosition;
    private ArrayList<LatLng> customPositions;
    private List<Address> personalAddress,customAddress;
    private Location userLocation,customLocation;
    private Button btn_Tienda;
    private ArrayList<Marker> savedMarkers;
    private ImageView icesi;

    boolean yaApareció, repiteBiblioteca = false,repiteL = false, repiteD = false;
    Polygon icesiArea, bibliotecaArea , lArea, dArea;


    public static final int STATE_FIRST = 1;

    public static final int STATE_RECEPTION = 2;

    public static final int STATE_DESTROY = 3;

    int state = STATE_FIRST;


    TextView points;



    //public interface listener

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        yaApareció = false;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        points = findViewById(R.id.points);



        geocoder = new Geocoder(this, Locale.getDefault());
        btn_Tienda = findViewById(R.id.btn_Tiendaa);
        btn_Tienda.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent i = new Intent(MapsActivity.this, TiendaActivity.class);
                        i.putExtra( "puntos",  ""+points.getText() );
                        startActivityForResult(i, 11);
                    }
                }
        );



        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        }, REQUEST_CODE);
        //Eliminar el if y solo dejar el else
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 11);
        } else {
            //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
        }



    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (personalMarker==null){

                personalPosition = new LatLng(location.getLatitude(), location.getLongitude());

                boolean isInbiblioteca = PolyUtil.containsLocation(personalPosition, bibliotecaArea.getPoints(), true);
                boolean isInl= PolyUtil.containsLocation(personalPosition, lArea.getPoints(), true);
                boolean isInd = PolyUtil.containsLocation(personalPosition, dArea.getPoints(), true);



                if(isInbiblioteca && repiteBiblioteca == false){
                   /* icesi.setVisibility(View.VISIBLE);

                    */


                    repiteD = false;
                    repiteL = false;
                    repiteBiblioteca = true;
                    Intent i = new Intent(MapsActivity.this, preguntasActivity.class);
                    i.putExtra( "puntos",  ""+points.getText() );
                    startActivityForResult(i, 11);



                } else if( isInd && repiteD == false){

                    repiteD = true;
                    repiteL = false;
                    repiteBiblioteca = false;
                    Intent i = new Intent(MapsActivity.this, preguntasActivity.class);
                    i.putExtra( "puntos",  ""+points.getText() );
                    startActivityForResult(i, 11);


                } else if(isInl && repiteL == false){
                    repiteD = false;
                    repiteL = true;
                    repiteBiblioteca = false;
                    Intent i = new Intent(MapsActivity.this, preguntasActivity.class);
                    i.putExtra( "puntos",  ""+points.getText() );
                    startActivityForResult(i, 11);



                }else{

                }
                try {
                    personalAddress = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                    String addres = personalAddress.get(0).getAddressLine(0);

                    personalMarker = mMap.addMarker(new MarkerOptions().position(personalPosition).title("Su posición actual es "+addres).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(personalPosition));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                userLocation=location;
                personalPosition = null;

                personalPosition = new LatLng(location.getLatitude(), location.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(personalPosition,15));
                personalMarker.setPosition(personalPosition);
                try {
                    personalAddress = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                    String addres = personalAddress.get(0).getAddressLine(0).split(",")[0];
                    personalMarker.setTitle("Su posición actual es "+addres);

                } catch (IOException e) {
                    Toast.makeText(MapsActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }



        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        bibliotecaArea = mMap.addPolygon(new PolygonOptions().add(
                new LatLng(3.341867, -76.530104),
                new LatLng(3.341687, -76.530111),
                new LatLng(3.341690, -76.529816),
                new LatLng(3.341847, -76.529811)

        ));
        lArea = mMap.addPolygon(new PolygonOptions().add(
                new LatLng(3.342488, -76.530467),
                new LatLng(3.342436, -76.530079),
                new LatLng(3.342318, -76.530084),
                new LatLng(3.342342, -76.530451)

        ));
        dArea = mMap.addPolygon(new PolygonOptions().add(
                new LatLng(3.341012, -76.530491),
                new LatLng(3.340806, -76.530513),
                new LatLng(3.340793, -76.529985),
                new LatLng(3.340985, -76.529960)


        ));

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 11) {
            //locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, locationListener);
        }
    }




    @Override
    protected void onPause() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("texto",points.getText().toString());
        editor.apply();

        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();

        if(state == STATE_FIRST){

            String texto = PreferenceManager.getDefaultSharedPreferences(this).getString("texto", "10");
            points.setText(texto);
        }else{

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("GH","On Activity Result");

        state = STATE_RECEPTION;
        if(requestCode == 11&& resultCode == RESULT_OK){
            String pun = data.getExtras().getString("resultado");
            points.setText(pun);

        }
    }


}
