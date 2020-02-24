package com.example.telecracksapp.izimath;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterOptionsActivity  extends AppCompatActivity {


    private Button usuario_bt;
    private Button colaborador_bt;
    private Button atras_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_options_activity);
        usuario_bt.findViewById(R.id.userB_bt);
        colaborador_bt.findViewById(R.id.helperB_bt);
        atras_bt.findViewById(R.id.BackOption_bt);


        usuario_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterOptionsActivity.this, RegisterUserActivity.class);
                startActivity(i);
            }
        });
        colaborador_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterOptionsActivity.this, RegisterHelperActivity.class);
                startActivity(i);
            }
        });
        atras_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterOptionsActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

    }


}
