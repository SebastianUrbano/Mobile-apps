package com.example.basededatos.app;

import android.app.Application;
import android.content.Context;


public class ManejoDeDatosApp extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        ManejoDeDatosApp.context = getApplicationContext();
    }

    public static Context getAppContext(){
        return context;
    }
}


