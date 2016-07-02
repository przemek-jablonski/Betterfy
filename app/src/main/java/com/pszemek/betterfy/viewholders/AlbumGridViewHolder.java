package com.pszemek.betterfy.viewholders;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.adapters.RecyclerOnPosClickListener;

/**
 * Created by Ciemek on 02/07/16.
 */
public class AlbumGridViewHolder extends AbstractBaseViewHolder {

    public ImageView albumImage;
    public TextView albumAlbumName;

    public AlbumGridViewHolder(View itemView, @Nullable RecyclerOnPosClickListener recyclerOnPosClickListener) {
        super(itemView, recyclerOnPosClickListener);

        albumImage = (ImageView)itemView.findViewById(R.id.recycler_item_album_image);
        albumAlbumName = (TextView)itemView.findViewById(R.id.recycler_item_album_textview);
    }
}
