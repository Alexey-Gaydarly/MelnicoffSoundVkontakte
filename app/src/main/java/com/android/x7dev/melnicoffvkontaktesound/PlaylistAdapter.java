package com.android.x7dev.melnicoffvkontaktesound;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.x7dev.melnicoffvkontaktesound.vk.Audio;

import java.util.ArrayList;

public class PlaylistAdapter extends BaseAdapter{

    Context ctx;
    LayoutInflater lInflater;
    ArrayList<Audio> objects;

    PlaylistAdapter(Context context, ArrayList<Audio> audio) {
        ctx = context;
        objects = audio;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.items_playlist, parent, false);
        }

        Audio audio = getProduct(position);

        ((TextView) view.findViewById(R.id.tvArtist)).setText(audio.artist);
        ((TextView) view.findViewById(R.id.tvTitle)).setText(audio.title);

        return view;
    }

    Audio getProduct(int position) {
        return ((Audio) getItem(position));
    }
}
