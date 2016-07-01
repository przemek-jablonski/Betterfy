package com.pszemek.betterfy.adapters;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pszemek.betterfy.DoubleStringSeparatorObject;
import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.models.ArtistFullObject;
import com.pszemek.betterfy.backend.models.TopObject;
import com.pszemek.betterfy.backend.models.TrackFullObject;
import com.pszemek.betterfy.misc.Utils;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;

/**
 * Created by Ciemek on 30/06/16.
 */
public class TopAdapter extends BaseAdapter<TopObject> {


    private final int TOPARTIST = 0;
    private final int TOPTRACK = 1;
    private final int SEPARATOR = 2;


    public void addItems(DoubleStringSeparatorObject separator, List<ArtistFullObject> items) {
        addItem(separator);
        for (int i=0; i < items.size(); ++i){
            addItem(items.get(i));
        }

        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TOPARTIST) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_artist, parent, false);
            itemView.setBackground(parent.getContext().getResources().getDrawable(R.drawable.button_onclick_colors_betterfy_transparent));
            return new TopArtistViewHolder(itemView);
        }

        if (viewType == TOPTRACK) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_track_withalbum, parent, false);
            itemView.setBackground(parent.getContext().getResources().getDrawable(R.drawable.button_onclick_colors_betterfy_transparent));
            return new TopTrackViewHolder(itemView);
        }

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_doublestring_separator, parent, false);
        return new TopSeparatorViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TopObject topObject = getItem(position);
        if (topObject instanceof ArtistFullObject) {

            ((TopArtistViewHolder) holder).topArtistName.setText(((ArtistFullObject)topObject).name);
            ((TopArtistViewHolder) holder).topArtistGenre.setText(Utils.createStringGenre((ArtistFullObject)topObject));
            ((TopArtistViewHolder) holder).topArtistFollowers.setText(Utils.createStringFollowers((ArtistFullObject)topObject));
            Picasso
                    .with(((TopArtistViewHolder) holder).topArtistImage.getContext())
                    .load(((ArtistFullObject) topObject).images.get(0).url)
                    .resize(64, 64)
                    .into(((TopArtistViewHolder) holder).topArtistImage);

        } else if (topObject instanceof TrackFullObject) {

            ((TopTrackViewHolder) holder).topTrackTitle.setText(((TrackFullObject)topObject).name);
            ((TopTrackViewHolder) holder).topTrackArtists.setText(Utils.createStringTrackArtists(((TrackFullObject)topObject).artists));
            ((TopTrackViewHolder) holder).topTrackAlbum.setText(((TrackFullObject)topObject).album.name);
            ((TopTrackViewHolder) holder).topTrackTime.setText(Utils.convertMsToDurationString(((TrackFullObject)topObject).durationMs));
            ((TopTrackViewHolder) holder).topTrackPopularity.setText(Utils.createStringHype(((TrackFullObject) topObject).popularity));

        } else {

            ((TopSeparatorViewHolder) holder).textLeft.setText(((DoubleStringSeparatorObject) topObject).leftString);
            ((TopSeparatorViewHolder) holder).textRight.setText(((DoubleStringSeparatorObject) topObject).rightString);
        }

        //todo: exception here
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof ArtistFullObject) {
            return TOPARTIST;
        } else if (getItem(position) instanceof TrackFullObject) {
            return TOPTRACK;
        }
        return SEPARATOR;
    }


    public class TopTrackViewHolder extends RecyclerView.ViewHolder {

        TextView topTrackTitle;
        TextView topTrackArtists;
        TextView topTrackAlbum;
        TextView topTrackTime;
        TextView topTrackPopularity;

        public TopTrackViewHolder(View itemView) {
            super(itemView);
            topTrackTitle = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_title);
            topTrackArtists = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_artists);
            topTrackAlbum = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_albumname);
            topTrackTime = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_time);
            topTrackPopularity = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_hype);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerOnPosClickListener.onPosClick(v, getLayoutPosition());
                }
            });
        }
    }

    public class TopArtistViewHolder extends RecyclerView.ViewHolder {

        ImageView   topArtistImage;
        TextView    topArtistName;
        TextView    topArtistGenre;
        TextView    topArtistFollowers;

        public TopArtistViewHolder(View itemView) {
            super(itemView);
            topArtistImage = (ImageView) itemView.findViewById(R.id.recycler_item_artist_image);
            topArtistName = (TextView) itemView.findViewById(R.id.recycler_item_artist_name);
            topArtistGenre = (TextView) itemView.findViewById(R.id.recycler_item_artist_genre);
            topArtistFollowers = (TextView) itemView.findViewById(R.id.recycler_item_artist_auxtext);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerOnPosClickListener.onPosClick(v, getLayoutPosition());
                }
            });
        }
    }

    public class TopSeparatorViewHolder extends RecyclerView.ViewHolder {

        TextView    textLeft;
        TextView    textRight;

        public TopSeparatorViewHolder(View itemView) {
            super(itemView);
            textLeft = (TextView) itemView.findViewById(R.id.recycler_item_doublestring_separator_left);
            textRight = (TextView) itemView.findViewById(R.id.recycler_item_doublestring_separator_right);
        }
    }


}
