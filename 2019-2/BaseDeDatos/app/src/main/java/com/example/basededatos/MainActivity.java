package com.example.basededatos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.basededatos.model.data.CRUDTTasklist;
import com.example.basededatos.model.driver.DBDriver;
import com.example.basededatos.model.entity.TaskList;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TaskList lista1 = new TaskList(UUID.randomUUID().toString(), "Lista1", Calendar.getInstance().getTime());
        TaskList lista2 = new TaskList(UUID.randomUUID().toString(), "Lista2", Calendar.getInstance().getTime());
        TaskList lista3 = new TaskList(UUID.randomUUID().toString(), "Lista3", Calendar.getInstance().getTime());

        CRUDTTasklist.createTaskList(lista1);
        CRUDTTasklist.createTaskList(lista2);
        CRUDTTasklist.createTaskList(lista3);

        ArrayList<TaskList> grupo = CRUDTTasklist.getAllTaskList();
        for (int i = 0; i<grupo.size();i++){
            Log.e(">>"+1, grupo.get(i).getName());
        }



    }
}
