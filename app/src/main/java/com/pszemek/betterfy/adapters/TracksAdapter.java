package com.pszemek.betterfy.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.models.PlaylistTrackObject;
import com.pszemek.betterfy.misc.Utils;

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
        PlaylistTrackObject playlistTrack = getItem(position);
        ((TrackViewHolder) holder).trackTitle.setText(playlistTrack.track.name);
        ((TrackViewHolder) holder).trackArtists.setText(Utils.createStringTrackArtists(playlistTrack));
        ((TrackViewHolder) holder).trackTime.setText(Utils.convertMsToDurationString(playlistTrack.track.durationMs));
        ((TrackViewHolder) holder).trackHype.setText(Utils.createStringHype(playlistTrack.track.popularity));
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder {

        TextView    trackTitle;
        TextView    trackArtists;
        TextView    trackTime;
        TextView    trackHype;

        public TrackViewHolder(View itemView) {
            super(itemView);
            trackTitle = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_title);
            trackArtists = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_artists);
            trackTime = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_time);
            trackHype = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_hype);
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
