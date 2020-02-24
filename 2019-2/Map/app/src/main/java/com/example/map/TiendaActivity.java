package com.example.map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class TiendaActivity extends AppCompatActivity {

    private Button BtnComprar;
    private ArrayList<Producto> productos;
    private ArrayList<String> stringProductos;
    private ArrayAdapter<String> adapter;
    private ListView loDeTienda;
    private String puntos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tienda);

        puntos = this.getIntent().getExtras().getString("puntos");

        stringProductos = new ArrayList<>();
        productos = new ArrayList<>();




        productos.add(new Producto("Lapicero","20"));
        productos.add(new Producto("Cuaderno","30"));
        productos.add(new Producto("Libreta","40"));
        productos.add(new Producto("Camisa","80"));
        productos.add(new Producto("Saco","100"));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, stringProductos);
        stringProductos.add("Lapicero   20");
        stringProductos.add("Cuaderno   30");
        stringProductos.add("Libreta    40");
        stringProductos.add("Camisa     80");
        stringProductos.add("Saco      100");

        loDeTienda = findViewById(R.id.list_vieww);
        loDeTienda.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        BtnComprar = findViewById(R.id.btn_Tiendaa);
        BtnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("resultado", "" + puntos);
                setResult( RESULT_OK, i );
                finish();
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
}