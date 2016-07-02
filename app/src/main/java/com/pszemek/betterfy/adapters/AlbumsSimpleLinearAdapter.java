package com.pszemek.betterfy.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.models.simplified.AlbumSimpleObject;
import com.pszemek.betterfy.viewholders.AlbumSimpleLinearViewHolder;
import com.squareup.picasso.Picasso;

/**
 * Created by Ciemek on 02/07/16.
 */
public class AlbumsSimpleLinearAdapter extends BaseAdapter<AlbumSimpleObject> {

    public AlbumsSimpleLinearAdapter(@Nullable RecyclerOnPosClickListener onPosClickListener) {
        super(onPosClickListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AlbumSimpleLinearViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_album_linear, parent, false),
                recyclerOnPosClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((AlbumSimpleLinearViewHolder) holder).albumName.setText(getItem(position).name);
        ((AlbumSimpleLinearViewHolder) holder).albumName.setText(getItem(position).albumType);
        Picasso.with(holder.itemView.getContext())
                .load(getItem(position).images.get(0).url)
                .placeholder(R.drawable.album_placeholder_001_64)
                .into(((AlbumSimpleLinearViewHolder) holder).albumImage);
    }
}
