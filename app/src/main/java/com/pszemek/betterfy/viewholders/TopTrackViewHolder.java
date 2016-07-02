package com.pszemek.betterfy.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.adapters.RecyclerOnPosClickListener;

/**
 * Created by Ciemek on 02/07/16.
 */
public class TopTrackViewHolder extends AbstractBaseViewHolder {

    public TextView topTrackTitle;
    public TextView topTrackArtists;
    public TextView topTrackAlbum;
    public TextView topTrackTime;
    public TextView topTrackPopularity;

    public TopTrackViewHolder(View itemView, RecyclerOnPosClickListener onPosClickListener) {
        super(itemView, onPosClickListener);
        topTrackTitle = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_title);
        topTrackArtists = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_artists);
        topTrackAlbum = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_albumname);
        topTrackTime = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_time);
        topTrackPopularity = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_hype);
    }
}
