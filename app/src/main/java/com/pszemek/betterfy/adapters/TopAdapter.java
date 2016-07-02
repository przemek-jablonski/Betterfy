package com.pszemek.betterfy.adapters;

import android.animation.ObjectAnimator;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pszemek.betterfy.DoubleStringSeparatorObject;
import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.models.ArtistFullObject;
import com.pszemek.betterfy.backend.models.TopObject;
import com.pszemek.betterfy.backend.models.TrackFullObject;
import com.pszemek.betterfy.misc.Utils;
import com.pszemek.betterfy.viewholders.DoubleStringSeparatorViewHolder;
import com.pszemek.betterfy.viewholders.TopArtistViewHolder;
import com.pszemek.betterfy.viewholders.TopTrackViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

/**
 * Created by Ciemek on 30/06/16.
 */
public class TopAdapter extends BaseAdapter<TopObject> {


    private final int TOPARTIST = 0;
    private final int TOPTRACK = 1;
    private final int SEPARATOR = 2;

    public TopAdapter(@Nullable RecyclerOnPosClickListener onPosClickListener) {
        super(onPosClickListener);
    }


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
            return new TopArtistViewHolder(itemView, recyclerOnPosClickListener);
        }

        if (viewType == TOPTRACK) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_track_withalbum, parent, false);
            itemView.setBackground(parent.getContext().getResources().getDrawable(R.drawable.button_onclick_colors_betterfy_transparent));
            return new TopTrackViewHolder(itemView, recyclerOnPosClickListener);
        }

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_doublestring_separator, parent, false);
        return new DoubleStringSeparatorViewHolder(itemView);
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

            ((DoubleStringSeparatorViewHolder) holder).textLeft.setText(((DoubleStringSeparatorObject) topObject).leftString);
            ((DoubleStringSeparatorViewHolder) holder).textRight.setText(((DoubleStringSeparatorObject) topObject).rightString);
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

}
