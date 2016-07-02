package com.pszemek.betterfy.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.adapters.RecyclerOnPosClickListener;

/**
 * Created by Ciemek on 02/07/16.
 */
public class TopArtistViewHolder extends AbstractBaseViewHolder {

    public ImageView topArtistImage;
    public TextView topArtistName;
    public TextView    topArtistGenre;
    public TextView    topArtistFollowers;

    public TopArtistViewHolder(View itemView, RecyclerOnPosClickListener onPosClickListener) {
        super(itemView, onPosClickListener);
        topArtistImage = (ImageView) itemView.findViewById(R.id.recycler_item_artist_image);
        topArtistName = (TextView) itemView.findViewById(R.id.recycler_item_artist_name);
        topArtistGenre = (TextView) itemView.findViewById(R.id.recycler_item_artist_genre);
        topArtistFollowers = (TextView) itemView.findViewById(R.id.recycler_item_artist_auxtext);
    }

}
