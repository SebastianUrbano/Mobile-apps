package com.example.practicodeezer;


import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;


import java.net.URL;

import com.bumptech.glide.Glide;
import com.deezer.sdk.model.Track;
import com.deezer.sdk.network.connect.DeezerConnect;



public class DetallesCancion extends AppCompatActivity {


    public static final String APPLICATION_ID = "375864";

    private DeezerConnect deezerConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_cancion);


        deezerConnect = new DeezerConnect(this, APPLICATION_ID);

        ImageView trackImage = this.findViewById(R.id.imagenCancion);

        final Track track = (Track) getIntent().getExtras().get("t");

        Toast.makeText(getApplicationContext(), "Error"+ track.getTitle(), Toast.LENGTH_LONG);
        Log.i("aqui", ""+track.getTitle());
        TextView name = this.findViewById(R.id.tv_songName);
        name.setText(track.getTitle());
        TextView album = this.findViewById(R.id.vistaAlbumNombre);
        album.setText(track.getAlbum().getTitle());
        TextView artist = this.findViewById(R.id.tv_artistName);
        artist.setText(track.getArtist().getName());
        TextView duration = this.findViewById(R.id.tv_songDuration);
        int minutes = track.getDuration() / 60;
        int seconds = track.getDuration() - minutes * 60;
        if ((seconds + "").length() == 1)
            duration.setText(minutes + ":0" + seconds);
        else
            duration.setText(minutes + ":" + seconds);

        try {
            URL url = new URL(track.getAlbum().getImageUrl());
            Glide.with(this).load(url).into(trackImage);

        } catch(Exception e){
            Log.e(this.getClass().toString(), "Paila fai, hubo un error");
        }

        Button open = this.findViewById(R.id.btn_open);
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(track.getLink()));
                startActivity(intent);
            }
        });
    }
}
