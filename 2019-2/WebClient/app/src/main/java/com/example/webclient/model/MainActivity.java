package com.example.webclient.model;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.webclient.R;
import com.example.webclient.util.HTTPSWebUtilDomi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import javax.microedition.khronos.opengles.GL10;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Thread(
                ()->{

                    HTTPSWebUtilDomi h = new HTTPSWebUtilDomi();

                    try {


                        Gson gson = new Gson();


                        Carta u = new Carta("El Sebas :D", "yo", "domi");


                        h.POSTrequest("https://instalacion-bc3ad.firebaseio.com/comentarios.json",  gson.toJson(u));

                    }catch (IOException e){

                        e.printStackTrace();
                    }
                }
        ).start();

    }


}
