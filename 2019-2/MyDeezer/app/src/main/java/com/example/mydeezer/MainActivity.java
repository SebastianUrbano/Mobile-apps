package com.example.mydeezer;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.content.Intent;
import android.graphics.ImageDecoder;
import android.provider.MediaStore;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.deezer.sdk.model.Album;
import com.deezer.sdk.model.Playlist;
import com.deezer.sdk.network.connect.DeezerConnect;
import com.deezer.sdk.network.request.DeezerRequest;
import com.deezer.sdk.network.request.DeezerRequestFactory;
import com.deezer.sdk.network.request.event.JsonRequestListener;
import com.deezer.sdk.network.request.event.RequestListener;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String APPLICATION_ID = "375864";

    private ListView list_Playlist;

    private PlaylistAdapter playlistAdapter;

    private DeezerConnect deezerConnect;

    private Button btn_buscar;

    private EditText txt_buscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        deezerConnect = new DeezerConnect(this, APPLICATION_ID);

        Vistaprincipal("Rock");

        playlistAdapter = new PlaylistAdapter(this);

        list_Playlist = findViewById(R.id.listas);
        list_Playlist.setAdapter(playlistAdapter);
        Log.e("TEST", "paso la 1");

        list_Playlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.e("TEST", "paso la 2");
                Playlist playlist = (Playlist) playlistAdapter.getItem(position);

                Intent intent = new Intent(MainActivity.this, PlaylistDetails.class);
                intent.putExtra("idPlaylist",playlist.getId());

                startActivity(intent);
            }
        });
        txt_buscar = findViewById(R.id.textBuscar);
        
        btn_buscar = findViewById(R.id.btn_buscar);
        
        btn_buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = txt_buscar.getText().toString();
                Vistaprincipal(nombre);
            }
        });



    }


    public void Vistaprincipal(String musicaInicial) {

        DeezerRequest respuesta = DeezerRequestFactory.requestSearchPlaylists(musicaInicial);
        RequestListener listener = new JsonRequestListener() {

            public void onResult(Object result, Object requestId) {
                List<Playlist> playlists = (List<Playlist>) result;

                playlistAdapter.setPlaylist(playlists);

            }

            public void onUnparsedResult(String requestResponse, Object requestId) {
            }

            public void onException(Exception e, Object requestId) {
            }
        };

        Log.i("Info", "inicializarLista: "+deezerConnect.isSessionValid() );
        deezerConnect.requestAsync(respuesta, listener);


    }


}
