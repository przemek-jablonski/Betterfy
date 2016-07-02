package com.pszemek.betterfy.viewholders;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.adapters.RecyclerOnPosClickListener;

/**
 * Created by Ciemek on 02/07/16.
 */
public class AlbumSimpleLinearViewHolder extends AbstractBaseViewHolder {

    public ImageView    albumImage;
    public TextView     albumName;
    public TextView     albumType;

    public AlbumSimpleLinearViewHolder(View itemView, @Nullable RecyclerOnPosClickListener onPosClickListener) {
        super(itemView, onPosClickListener);
        albumImage = (ImageView) itemView.findViewById(R.id.recycler_item_album_linear_image);
        albumName = (TextView) itemView.findViewById(R.id.recycler_item_album_linear_name);
        albumType = (TextView) itemView.findViewById(R.id.recycler_item_album_linear_type);
    }
}
