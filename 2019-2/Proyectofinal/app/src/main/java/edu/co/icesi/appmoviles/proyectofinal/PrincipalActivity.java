package edu.co.icesi.appmoviles.proyectofinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class PrincipalActivity extends AppCompatActivity {

    private ImageButton searchBtn;
    private ImageButton burgerMenuBtn;

    private ImageButton calculoBtn;
    private ImageButton algebraLinealBtn;
    private ImageButton algebraBtn;
    private ImageButton calculoMultivariableBtn;
    private ImageButton geometriaBtn;
    private ImageButton ecuacionesDiferencialesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        searchBtn = findViewById(R.id.search_button);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PONER ACCION---------------------------------------------
            }
        });
        burgerMenuBtn = findViewById(R.id.burger_menu_button);
        burgerMenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PONER ACCION---------------------------------------------
            }
        });

        calculoBtn = findViewById(R.id.imageButton_Calculo);
        calculoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(PrincipalActivity.this, AreaPost.class);
                startActivity(i);
                //PONER ACCION------------------------------------------------------------------------------------------
            }
        });

        algebraBtn = findViewById(R.id.imageButton_Algebra);
        algebraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PONER ACCION---------------------------------------------
            }
        });
        algebraLinealBtn = findViewById(R.id.imageButton_lineal);
        algebraLinealBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PONER ACCION---------------------------------------------
            }
        });

        calculoMultivariableBtn = findViewById(R.id.imageButton_CalculoM);
        calculoMultivariableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PONER ACCION---------------------------------------------
            }
        });

        geometriaBtn = findViewById(R.id.imageButton_geometria);
        geometriaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PONER ACCION---------------------------------------------
            }
        });

        ecuacionesDiferencialesBtn = findViewById(R.id.imageButton_Ecua);
        ecuacionesDiferencialesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PONER ACCION---------------------------------------------
            }
        });



    }
}
