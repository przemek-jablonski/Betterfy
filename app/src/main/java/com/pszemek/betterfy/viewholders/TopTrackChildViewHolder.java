package com.pszemek.betterfy.viewholders;

import android.view.View;
import android.widget.TextView;

import com.pszemek.betterfy.R;

/**
 * Created by Ciemek on 30/06/16.
 */
public class TopTrackChildViewHolder extends TopChildBaseViewHolder{

    TextView topTrackTitle;
    TextView topTrackArtists;
    TextView topTrackAlbum;
    TextView topTrackTime;
    TextView topTrackPopularity;

    public TopTrackChildViewHolder(View itemView) {
        super(itemView);
        topTrackTitle = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_title);
        topTrackArtists = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_artists);
        topTrackAlbum = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_albumname);
        topTrackTime = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_time);
        topTrackPopularity = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_hype);

    }


}
