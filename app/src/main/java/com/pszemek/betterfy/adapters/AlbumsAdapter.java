package com.pszemek.betterfy.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.models.AlbumFullObject;
import com.pszemek.betterfy.backend.models.simplified.AddedAtResponse;

/**
 * Created by Ciemek on 29/06/16.
 */
public class AlbumsAdapter extends BaseAdapter<AddedAtResponse<AlbumFullObject>> {


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View albumItemView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.recycler_item_album, parent, false);
        return new AlbumViewHolder(albumItemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        AlbumFullObject album = getItem(position).album;
        ((AlbumViewHolder) holder).albumImage.setImageResource(R.drawable.album_placeholder_001_300);
        ((AlbumViewHolder) holder).albumAlbumName.setText(album.name);
    }


    public class AlbumViewHolder extends RecyclerView.ViewHolder {

        ImageView   albumImage;
        TextView    albumAlbumName;

        public AlbumViewHolder(View itemView) {
            super(itemView);

            albumImage = (ImageView)itemView.findViewById(R.id.recycler_item_album_image);
            albumAlbumName = (TextView)itemView.findViewById(R.id.recycler_item_album_textview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerOnPosClickListener.onPosClick(v, getLayoutPosition());
                }
            });
        }
    }

}
