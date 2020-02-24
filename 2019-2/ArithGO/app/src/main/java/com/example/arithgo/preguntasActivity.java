package com.example.arithgo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class preguntasActivity extends AppCompatActivity {

    private TextView primerito, segundito, simbolo;
    private EditText respuesta;
    private Button chequear;
    private int puntoos;
    private int operador;

    private final static int SUMA = 0;
    private final static int RESTA = 1;
    private final static int MULTIPLICACION = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);



        String puntis =  this.getIntent().getExtras().getString("puntos");
        puntoos = Integer.parseInt(puntis);
        primerito = findViewById(R.id.primero);
        segundito = findViewById(R.id.segundo);
        simbolo = findViewById(R.id.simbolo);
        respuesta = findViewById(R.id.resultado);
        chequear = findViewById(R.id.chequear_btn);
        primerito.setText(""+(int)(Math.random()*10));
        segundito.setText(""+(int)(Math.random()*10));


        operador = (int)(Math.random()*3);

        switch (operador){
            case SUMA:
                simbolo.setText("SUMADO CON");
                break;

            case RESTA:
                simbolo.setText("RESTANDO");
                break;

            case MULTIPLICACION:
                simbolo.setText("MULTIPLICADO CON");
                break;

        }



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
        if( !resuelto(Integer.parseInt(res),Integer.parseInt(primero),Integer.parseInt(segundo))  ){
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
    public boolean resuelto(int respuesta, int primero, int segundo){
        boolean correcto = false;

        if (operador == SUMA){
            if ((primero+segundo)==respuesta){
                correcto = true;
            }
        }else if (operador == RESTA){
            if ((primero-segundo)==respuesta){
                correcto = true;
            }
        }else if (operador == MULTIPLICACION){
            if ((primero*segundo)==respuesta){
                correcto = true;
            }
        }

        return correcto;
    }
}
