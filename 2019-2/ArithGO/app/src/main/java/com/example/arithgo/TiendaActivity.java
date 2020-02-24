package com.example.arithgo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class TiendaActivity extends AppCompatActivity {

    private Button BtnComprar;
    private ArrayList<Producto> productos;

    private ArrayAdapter<Producto> adapter;
    private ListView loDeTienda;
    private String puntos;
    private TextView puntaje;
    int trabajo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        trabajo = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        puntos = this.getIntent().getExtras().getString("puntos");

        productos = new ArrayList<>();

        puntaje = findViewById(R.id.puntaje);
        puntaje.setText("Cantidad puntos: "+puntos);



        productos.add(new Producto("Lapicero","2"));
        productos.add(new Producto("Cuaderno","3"));
        productos.add(new Producto("Libreta","4"));
        productos.add(new Producto("Camisa","8"));
        productos.add(new Producto("Saco","10"));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productos);


        loDeTienda = findViewById(R.id.list_vieww);
        loDeTienda.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        BtnComprar = findViewById(R.id.btn_Tiendaa);
        BtnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent z = new Intent();
                int p = 0;

                if(trabajo != 0){

                    int variable = Integer.parseInt(puntos);
                    if (variable >= trabajo){
                        p = variable - trabajo;

                        z.putExtra("resultado", "" + p);
                        setResult( RESULT_OK, z );
                        finish();
                    }else {
                        Toast.makeText(TiendaActivity.this, "No le alcanzan los puntos", Toast.LENGTH_LONG).show();
                    }


                }else{
                    Toast.makeText(TiendaActivity.this, "Debe dejar sostenido un item", Toast.LENGTH_LONG).show();
                }


            }
        });



        loDeTienda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                Toast.makeText(TiendaActivity.this, "Item seleccionado", Toast.LENGTH_LONG).show();

                String paraToast = "";
                switch (pos){
                    case 0: paraToast = "un lapicero";
                        break;

                    case 1: paraToast = "un cuaderno";
                        break;

                    case 2: paraToast = "una libreta";
                        break;

                    case 3: paraToast = "una camisa";
                        break;

                    case 4: paraToast = "un saco";
                        break;



                }
                Toast.makeText(TiendaActivity.this, "Usted ha seleccionado "+paraToast+ " click sostenido para confirmar", Toast.LENGTH_LONG).show();
            }
        });


        loDeTienda.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {


                switch (i){
                    case 0: trabajo = 2 ;
                        break;

                    case 1: trabajo = 3;
                        break;

                    case 2: trabajo = 4;
                        break;

                    case 3: trabajo = 8;
                        break;

                    case 4: trabajo = 10;
                        break;

                }
                return true;
            }
        });
    }




}

class ProductoListAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Producto> laLista;

    public ProductoListAdapter(Context context, ArrayList<Producto> laLista) {
        this.context = context;
        this.laLista = laLista;
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return laLista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        /*View v = View.inflate(context, R.layout.)

         */
        return null;
    }
}

class Producto {

    public Producto(String nombre, String precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    private String nombre, precio;



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return ""+getNombre()+" "+getPrecio();
    }
}