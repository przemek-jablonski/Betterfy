package com.pszemek.betterfy.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.models.SpotifyBaseResponse;
import com.pszemek.betterfy.backend.models.simplified.Playlist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ciemek on 18/06/16.
 */
public class PlaylistsAdapter extends RecyclerView.Adapter<PlaylistsAdapter.ViewHolder> {

    private RecyclerOnPosClickListener recyclerOnPosClickListener;
    List<Playlist> playlists;


    public PlaylistsAdapter() {
        super();
        Log.e("PlaylistsAdapter", "constructor()");
        playlists = new ArrayList<>();
    }

    public void setRecyclerOnPosClickListener(RecyclerOnPosClickListener recyclerOnPosClickListener) {
        this.recyclerOnPosClickListener = recyclerOnPosClickListener;
    }

//    public void updateData(PlaylistsModel item) {
//        Log.e("PlaylistsAdapter", "updateData()");
//        playlists.clear();
//        playlists.addAll(item.getPlaylists());
//        notifyDataSetChanged();
//    }


    public void updateModel(SpotifyBaseResponse<Playlist> response) {
        playlists.clear();
        playlists.addAll(response.getItems());
        notifyDataSetChanged();
    }

    public void updateItems(List<Playlist> playlists) {
        playlists.clear();
        playlists.addAll(playlists);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("PlaylistsAdapter", "onCreateViewHolder()");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_playlist, parent, false);

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

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public Playlist getPlaylist(int position) {
        return playlists.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView    pictureView;
        TextView    nameView;
        TextView    countView;
        private int touchedPosition;

        public ViewHolder(View itemView) {
            super(itemView);
            Log.e("PlaylistsAdapter.VH", "constructor()");
            pictureView = (TextView) itemView.findViewById(R.id.recycler_item_playlist_image);
            nameView = (TextView) itemView.findViewById(R.id.recycler_item_playlist_name);
            countView = (TextView) itemView.findViewById(R.id.recycler_item_playlist_count);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerOnPosClickListener != null)
                        recyclerOnPosClickListener.onPosClick(v, getLayoutPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            touchedPosition = getLayoutPosition();
            Log.e("PlaylistsAdapter", "ViewHolder.onClick(): " + "pos: " + touchedPosition + " (songs: " + countView.getText() + ").") ;
        }
    }

}
