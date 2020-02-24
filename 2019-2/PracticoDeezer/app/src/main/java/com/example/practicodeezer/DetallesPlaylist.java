package com.example.practicodeezer;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.net.URL;

import com.bumptech.glide.Glide;
import com.deezer.sdk.model.Playlist;
import com.deezer.sdk.model.Track;
import com.deezer.sdk.network.connect.DeezerConnect;
import com.deezer.sdk.network.request.DeezerRequest;
import com.deezer.sdk.network.request.DeezerRequestFactory;
import com.deezer.sdk.network.request.event.JsonRequestListener;














import com.deezer.sdk.network.request.event.RequestListener;
public class DetallesPlaylist extends AppCompatActivity {

    public static final String ID = "375864";

    private DeezerConnect deezerConnect;
    
    private AdaptadorCancion adaptador;
    
    private ListView vista;

    private Button btn_buscar;

    private EditText txt_buscar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_playlist);

        deezerConnect = new DeezerConnect(this, ID);
        adaptador = new AdaptadorCancion(this);


        vista = findViewById(R.id.list_songList);
        vista.setAdapter(adaptador);

        vista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Track can = (Track) adaptador.getItem(position);


                Intent i = new Intent(DetallesPlaylist.this, DetallesCancion.class);
                i.putExtra("t",can);
                startActivity(i);
            }
        });

        loadSongs();

    }

    private void loadSongs() {
        long idPlaylist;

        Bundle bundle = this.getIntent().getExtras();
        idPlaylist = bundle.getLong("idLista");

        DeezerRequest deezeerRequest = DeezerRequestFactory.requestPlaylist(idPlaylist);

        RequestListener jsonListener = new JsonRequestListener() {

            public void onResult(Object result, Object requestId) {
                Playlist playlist = (Playlist) result;


                adaptador.setValues(playlist);

                ImageView laFoto = findViewById(R.id.img_playlist);

                try {

                    URL url = new URL(playlist.getBigImageUrl());
                    if (laFoto == null) {
                        Glide.with(DetallesPlaylist.this).load(url).into(laFoto);
                    }
                } catch(Exception e){
                    Log.e(this.getClass().toString(), "Paila fai, hubo un error");
                }



                TextView playlistName = findViewById(R.id.tv_playlistName);
                TextView playlistDescription = findViewById(R.id.tv_playlistDescription);
                TextView playlistFans = findViewById(R.id.fansNumber);
                TextView playlistSongsNumbers = findViewById(R.id.tv_songsNumber);

                playlistName.setText(playlist.getTitle());
                playlistDescription.setText(playlist.getDescription());
                playlistFans.setText(playlist.getFans()+" seguidores");
                playlistSongsNumbers.setText(playlist.getTracks().size()+" canciones");

            }

            public void onUnparsedResult(String requestResponse, Object requestId) {
            }

            public void onException(Exception e, Object requestId) {
            }
        };

        deezerConnect.requestAsync(deezeerRequest, jsonListener);

    }
}
