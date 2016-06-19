package com.pszemek.betterfy.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.models.PlaylistsModel;
import com.pszemek.betterfy.backend.models.auxiliary.Playlist;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ciemek on 18/06/16.
 */
public class PlaylistsAdapter extends RecyclerView.Adapter<PlaylistsAdapter.ViewHolder> {

    List<Playlist> playlists;


    public PlaylistsAdapter() {
        super();
        Log.e("PlaylistsAdapter", "constructor()");
        playlists = new LinkedList<>();
    }

    public PlaylistsAdapter(PlaylistsModel playlistsItem) {
        this();
        updateData(playlistsItem);
    }

    public void updateData(PlaylistsModel item) {
        Log.e("PlaylistsAdapter", "updateData()");
        playlists.clear();
        playlists.addAll(item.getPlaylists());
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("PlaylistsAdapter", "onCreateViewHolder()");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.playlists_recycler_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("PlaylistsAdapter", "onBindViewHolder() " + "pos: " + position);

        holder.pictureView.setText("[PIC]");
        holder.nameView.setText(playlists.get(position).getName());
        holder.countView.setText(Integer.toString(playlists.get(position).getTracks().getTotal()));
    }

    @Override
    public int getItemCount() {
        return playlists.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView    pictureView;
        TextView    nameView;
        TextView    countView;



        public ViewHolder(View itemView) {
            super(itemView);
            Log.e("PlaylistsAdapter.VH", "constructor()");
            pictureView = (TextView) itemView.findViewById(R.id.playlist_recycler_item_picture);
            nameView = (TextView) itemView.findViewById(R.id.playlist_recycler_item_name);
            countView = (TextView) itemView.findViewById(R.id.playlist_recycler_item_count);
        }
    }



}
