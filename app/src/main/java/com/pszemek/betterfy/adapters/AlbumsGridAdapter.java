package com.pszemek.betterfy.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.models.AlbumFullObject;
import com.pszemek.betterfy.backend.models.simplified.AddedAtResponse;
import com.pszemek.betterfy.viewholders.AlbumGridViewHolder;
import com.squareup.picasso.Picasso;

/**
 * Created by Ciemek on 29/06/16.
 */
public class AlbumsGridAdapter extends BaseAdapter<AddedAtResponse<AlbumFullObject>> {


    public AlbumsGridAdapter(@Nullable RecyclerOnPosClickListener onPosClickListener) {
        super(onPosClickListener);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View albumItemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycler_item_album_grid, parent, false);
        return new AlbumGridViewHolder(albumItemView, recyclerOnPosClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlbumFullObject album = getItem(position).album;
        ((AlbumGridViewHolder) holder).albumAlbumName.setText(album.name);
        Picasso
                .with(holder.itemView.getContext())
                .load(album.images.get(0).url)
                .resize(100, 100)
                .into(((AlbumGridViewHolder) holder).albumImage);
    }

}
