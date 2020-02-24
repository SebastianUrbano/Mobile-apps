package com.example.mydeezer;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.deezer.sdk.model.Playlist;
import com.deezer.sdk.model.Track;
import com.deezer.sdk.network.connect.DeezerConnect;
import com.deezer.sdk.network.request.DeezerRequest;
import com.deezer.sdk.network.request.DeezerRequestFactory;
import com.deezer.sdk.network.request.event.JsonRequestListener;
import com.deezer.sdk.network.request.event.RequestListener;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class PlaylistDetails extends AppCompatActivity {

    public static final String APPLICATION_ID = "375864";

    private DeezerConnect deezerConnect;
    private SongAdapter songAdapter;
    private ListView lv_song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_details);

        deezerConnect = new DeezerConnect(this, APPLICATION_ID);


        songAdapter = new SongAdapter(this);
        lv_song = findViewById(R.id.list_songList);
        lv_song.setAdapter(songAdapter);

        lv_song.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Track track = (Track) songAdapter.getItem(position);

                Intent intent = new Intent(PlaylistDetails.this, SongDetail.class);
                intent.putExtra("track",track);

                //finish();
                startActivity(intent);
            }
        });

        loadSongs();

    }

    private void loadSongs() {
        long idPlaylist = 0;
        Bundle bundle = this.getIntent().getExtras();
        idPlaylist = bundle.getLong("idPlaylist");

        DeezerRequest DR = DeezerRequestFactory.requestPlaylist(idPlaylist);

        RequestListener listener = new JsonRequestListener() {

            public void onResult(Object result, Object requestId) {
                Playlist playlist = (Playlist) result;
                //Log.e("TEST", "Tamanio " + playlists.size());
                songAdapter.setValues(playlist);

                ImageView playlistPhoto = findViewById(R.id.img_playlist);
                try {
                    URL url = new URL(playlist.getBigImageUrl());
                    if (playlistPhoto == null)
                        Log.e("url", "null foto");
                    Glide.with(PlaylistDetails.this).load(url).into(playlistPhoto);

                } catch (MalformedURLException e) {
                    Log.e(this.getClass().toString(), "Error al cargar la URL de la foto de la playlist");
                } catch (IOException e) {
                    Log.e(this.getClass().toString(), "Error al abrir la conexión de la foto de la playlist");
                }

                TextView playlistName = findViewById(R.id.tv_playlistName);
                TextView playlistDescription = findViewById(R.id.tv_playlistDescription);
                TextView playlistFans = findViewById(R.id.fansNumber);
                TextView playlistSongsNumbers = findViewById(R.id.tv_songsNumber);

                playlistName.setText(playlist.getTitle());
                playlistDescription.setText(playlist.getDescription());
                playlistFans.setText(playlist.getFans()+" Seguidores");
                playlistSongsNumbers.setText(playlist.getTracks().size()+" Canciones");
                // do something with the albums
            }

            public void onUnparsedResult(String requestResponse, Object requestId) {
            }

            public void onException(Exception e, Object requestId) {
            }
        };

        deezerConnect.requestAsync(DR, listener);

    }
}
