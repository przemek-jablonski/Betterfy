package com.pszemek.betterfy.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.models.PlaylistsModel;

import butterknife.BindView;

/**
 * Created by Ciemek on 18/06/16.
 */
public class PlaylistsAdapter extends RecyclerView.Adapter<PlaylistsAdapter.ViewHolder> {

    PlaylistsModel playlistsItem;

    public PlaylistsAdapter() {
        super();
    }

    public PlaylistsAdapter(PlaylistsModel playlistsItem) {
        super();
        Log.e("PlaylistsAdapter", "constructor()");
        this.playlistsItem = playlistsItem;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("PlaylistsAdapter", "onCreateViewHolder()");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.playlist_recycler_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("PlaylistsAdapter", "onBindViewHolder() " + "pos: " + position);
        holder.playlistNameView.setText(playlistsItem.getPlaylists().get(position).getName());
        holder.playlistCountView.setText(playlistsItem.getPlaylists().get(position).getTracks().getTotal());
    }

    @Override
    public int getItemCount() {
        return playlistsItem.getPlaylists().size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView playlistPictureView;
        public TextView playlistNameView;
        public TextView playlistCountView;


        public ViewHolder(View itemView) {
            super(itemView);

            playlistPictureView = (TextView) itemView.findViewById(R.id.playlist_recycler_item_picture);
            playlistNameView = (TextView) itemView.findViewById(R.id.playlist_recycler_item_name);
            playlistCountView = (TextView) itemView.findViewById(R.id.playlist_recycler_item_count);
        }
    }



}
