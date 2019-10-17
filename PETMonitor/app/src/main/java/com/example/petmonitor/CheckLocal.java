package com.example.petmonitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class CheckLocal extends AppCompatActivity {

    private TextView textLatitude;
    private TextView textLongitude;
    private TextView textResultado;





    private Location location;
    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_local);



        textLatitude = (TextView) findViewById(R.id.textLatitude);
        textLongitude = (TextView) findViewById(R.id.textLongitude);
        textResultado = (TextView) findViewById(R.id.textResultado);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){

        }else {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
        double longitudePet = -48.965691 ;
        double latitudePet = -16.322351 ;

        double resultado = gps2m(latitudePet, longitudePet,latitude,longitude);



        textLatitude.setText("Latidute: "+ latitude);
        textLongitude.setText("Longitude: "+ longitude);
        textResultado.setText("Distância do PET" + resultado +"metros" );

        if (resultado >20){
            Toast.makeText(getApplicationContext(),"Seu PET está Longe, va busca-lo!",Toast.LENGTH_LONG).show();

        }


    }


    private double gps2m(double lat_a, double lng_a, double lat_b, double lng_b)    {

        double pk = (180/3.14169);

        double a1 = lat_a / pk;

        double a2 = lng_a / pk;

        double b1 = lat_b / pk;

        double b2 = lng_b / pk;

        double t1 = Math.cos(a1)*Math.cos(a2)*Math.cos(b1)*Math.cos(b2);

        double t2 = Math.cos(a1)*Math.sin(a2)*Math.cos(b1)*Math.sin(b2);

        double t3 = Math.sin(a1)*Math.sin(b1);

        double tt = Math.acos(t1 + t2 + t3);

        return 6366000*tt;

    }

    }









