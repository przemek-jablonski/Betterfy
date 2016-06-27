package com.pszemek.betterfy.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.models.PlaylistTrackObject;

/**
 * Created by Ciemek on 23/06/16.
 */
public class TracksAdapter extends BaseAdapter<PlaylistTrackObject> {


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View trackItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_track, parent, false);
        return new TrackViewHolder(trackItemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((TrackViewHolder) holder).nameView.setText(getItem(position).track.name);
        ((TrackViewHolder) holder).artistView.setText(getItem(position).track.artists.get(0).name);
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder {

        TextView    artistView;
        TextView    nameView;

        public TrackViewHolder(View itemView) {
            super(itemView);
            artistView = (TextView) itemView.findViewById(R.id.recycler_item_track_artist);
            nameView = (TextView) itemView.findViewById(R.id.recycler_item_track_name);
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
