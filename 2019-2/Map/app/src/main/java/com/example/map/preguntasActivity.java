package com.example.map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class preguntasActivity extends AppCompatActivity {

    private TextView primerito, segundito;
    private EditText respuesta;
    private Button chequear;
    private int puntoos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        String puntos = (String) this.getIntent().getExtras().getString("puntos");
        puntoos = Integer.parseInt(puntos);
        primerito = findViewById(R.id.primero);
        segundito = findViewById(R.id.segundo);
        respuesta = findViewById(R.id.resultado);
        chequear = findViewById(R.id.chequear_btn);
        primerito.setText(""+(int)(Math.random()*10));
        segundito.setText(""+(int)(Math.random()*10));



        chequear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
            }
        });

    }

    private void validar() {
        String primero = primerito.getText().toString().trim();
        String segundo = segundito.getText().toString().trim();

        String res = respuesta.getText().toString().trim();
        if(res.isEmpty()){
            Toast.makeText(this, "No ha respondido la pregunta", Toast.LENGTH_SHORT).show();
            return;
        }
        if( (Integer.parseInt(primero)*Integer.parseInt(segundo)) != Integer.parseInt(res) ){
            Toast.makeText(this, "No coinciden", Toast.LENGTH_SHORT).show();

            if(puntoos != 0){
                puntoos = puntoos - 1;
            }

            //Enviar CallBack
            Intent i = new Intent();

            i.putExtra("resultado", "" + puntoos);
            setResult( RESULT_OK, i );
            finish();
        }

        //Enviar CallBack
        puntoos = puntoos + 1;
        Intent i = new Intent();
        i.putExtra("resultado", "" + puntoos);
        setResult( RESULT_OK, i );
        finish();

    }
}
