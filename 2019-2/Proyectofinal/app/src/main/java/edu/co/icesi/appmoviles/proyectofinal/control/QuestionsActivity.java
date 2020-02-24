package edu.co.icesi.appmoviles.proyectofinal.control;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import edu.co.icesi.appmoviles.proyectofinal.R;


public class QuestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);


        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            String   question = bundle.getString("pregunta");

//TRABAJAR AQUIIIIIIIIIIIIIIIII----------------------------------------------------------------------------------



        }





    }
}
