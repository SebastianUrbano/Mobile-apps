package edu.co.icesi.appmoviles.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AreaPost extends AppCompatActivity {

    private ListView listViewTopics;
    private ArrayList<String> topicsNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_post);

        topicsNames = new ArrayList<>();
        topicsNames.add("EigenValores y Vectores");
        topicsNames.add("¿Cómo multiplicar vectores y matrices?");
        topicsNames.add("¿Qué es un plano tangente?");
        topicsNames.add("¿Qué es la matriz identidad?");
        topicsNames.add("¿Qué es una transformación lineal?");


        listViewTopics = findViewById(R.id.listview_topics);



        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.listview_topic_item, topicsNames);
        listViewTopics.setAdapter(adapter);

        listViewTopics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(AreaPost.this,listViewTopics.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT ).show();

                Intent myIntent = new Intent(AreaPost.this, QuestionsActivity.class);


                myIntent.putExtra("pregunta", listViewTopics.getItemAtPosition(i).toString());
//TRABAJAR AQUIIIIIIIIIIIIIIIII----------------------------------------------------------------------------------
                startActivity(myIntent);


            }
        });
    }
}
