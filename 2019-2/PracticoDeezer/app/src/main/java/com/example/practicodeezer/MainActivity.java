package com.example.practicodeezer;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;

import com.deezer.sdk.model.Playlist;
import com.deezer.sdk.network.connect.DeezerConnect;
import com.deezer.sdk.network.request.DeezerRequest;
import com.deezer.sdk.network.request.DeezerRequestFactory;
import com.deezer.sdk.network.request.event.JsonRequestListener;
import com.deezer.sdk.network.request.event.RequestListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn_buscar;

    private EditText txt_buscar;

    private ListView listaDePlaylists;

    private AdaptadorPlaylist adaptador;

    private DeezerConnect deezerConnect;

    public static final String ID = "375864";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        deezerConnect = new DeezerConnect(this, ID);

        vistaPrincipal("rock");

        adaptador = new AdaptadorPlaylist(this);

        listaDePlaylists = findViewById(R.id.listas);
        listaDePlaylists.setAdapter(adaptador);

        listaDePlaylists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Playlist playlist = (Playlist) adaptador.getItem(position);

                Intent i = new Intent(MainActivity.this, DetallesPlaylist.class);
                i.putExtra("idLista",playlist.getId());
                startActivity(i);
            }
        });

        txt_buscar = findViewById(R.id.textBuscar);


        btn_buscar = findViewById(R.id.btn_buscar);
        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txt_buscar.getText().toString();
                vistaPrincipal(nombre);
            }
        });


    }





    public void vistaPrincipal(String query) {
        DeezerRequest deezerRequest = DeezerRequestFactory.requestSearchPlaylists(query);

        RequestListener jsonListener = new JsonRequestListener() {

            public void onResult(Object result, Object requestId) {
                List<Playlist> playlists = (List<Playlist>) result;
                adaptador.setPlaylist(playlists);
            }
            public void onUnparsedResult(String requestResponse, Object requestId) {
            }
            public void onException(Exception e, Object requestId) {
            }
        };
        deezerConnect.requestAsync(deezerRequest, jsonListener);


    }


}
