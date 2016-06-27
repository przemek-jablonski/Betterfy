package com.pszemek.betterfy.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.models.PlaylistObject;

/**
 * Created by Ciemek on 18/06/16.
 */
public class PlaylistsAdapter extends BaseAdapter<PlaylistObject> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View playlistItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_playlist, parent, false);
        return new PlaylistViewHolder(playlistItemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PlaylistViewHolder) holder).pictureView.setText("[PIC]");
        ((PlaylistViewHolder) holder).nameView.setText(getItem(position).playlistName);
        ((PlaylistViewHolder) holder).countView.setText(Integer.toString(getItem(position).tracks.totalTracks));
    }


    public class PlaylistViewHolder extends RecyclerView.ViewHolder {

        TextView    pictureView;
        TextView    nameView;
        TextView    countView;

        public PlaylistViewHolder(View itemView) {
            super(itemView);
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
    }
}
