package com.pszemek.betterfy.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.models.PlaylistObject;
import com.pszemek.betterfy.misc.Utils;

/**
 * Created by Ciemek on 18/06/16.
 */
public class PlaylistsAdapter extends BaseAdapter<PlaylistObject> {

    public PlaylistsAdapter(@Nullable RecyclerOnPosClickListener onPosClickListener) {
        super(onPosClickListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View playlistItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_playlist, parent, false);
        return new PlaylistViewHolder(playlistItemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PlaylistObject playlist = getItem(position);
//        ((PlaylistViewHolder) holder).image
        ((PlaylistViewHolder) holder).row1text.setText(playlist.playlistName);
        ((PlaylistViewHolder) holder).row2text.setText(Utils.createStringPlaylistAuxiliary(playlist));
    }


    public class PlaylistViewHolder extends RecyclerView.ViewHolder {

        ImageView   image;
        TextView    row1text;
        TextView    row2text;

        public PlaylistViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.recycler_item_playlist_image);
            row1text = (TextView) itemView.findViewById(R.id.recycler_item_playlist_text_main);
            row2text = (TextView) itemView.findViewById(R.id.recycler_item_playlist_text_aux);
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
