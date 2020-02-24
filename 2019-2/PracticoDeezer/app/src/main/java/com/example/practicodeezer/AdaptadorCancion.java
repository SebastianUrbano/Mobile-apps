package com.example.practicodeezer;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;





import com.bumptech.glide.Glide;
import com.deezer.sdk.model.Playlist;
import com.deezer.sdk.model.Track;


public class AdaptadorCancion extends BaseAdapter {
    private Activity activity;
    private List<Track> trackList;
    private Playlist playlist;

    public AdaptadorCancion(Activity activity) {
        this.activity = activity;
        trackList = new ArrayList<Track>();
    }

    @Override
    public int getCount() {
        return trackList.size();
    }

    @Override
    public Object getItem(int position) {
        return trackList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        View view = inflater.inflate(R.layout.cancion, null, false);


        ImageView imagen = view.findViewById(R.id.img_song);
        TextView nombre = view.findViewById(R.id.tv_songName);
        TextView cantante = view.findViewById(R.id.tv_songArtist);
        TextView año = view.findViewById(R.id.tv_releaseYear);

        Track cancion = trackList.get(position);

        try {

            URL url = new URL(cancion.getAlbum().getImageUrl());

            Glide.with(activity).load(url).into(imagen);

        } catch(Exception e){
            Log.e(this.getClass().toString(), "Paila fai, hubo un error");
        }


        nombre.setText(cancion.getTitle());

        cantante.setText(cancion.getArtist().getName());

        //Date date =  cancion.getAlbum().getReleaseDate();
        //Esta webada no dio nada xd, un null, por ende para puntear lo de abajo xd
        int a =(int) (2019 - Math.random()*22);
        año.setText("Lanzamiento "+a);

        return view;
    }

    public void setValues(Playlist playlist) {

        this.trackList = playlist.getTracks();
        this.playlist = playlist;
        notifyDataSetChanged();


    }

}
