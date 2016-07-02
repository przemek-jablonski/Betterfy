package com.pszemek.betterfy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.models.ArtistFullObject;
import com.pszemek.betterfy.misc.Utils;
import com.pszemek.betterfy.viewholders.TopParentObject;

import java.util.List;

/**
 * Created by Ciemek on 30/06/16.
 */
public class TopArtistsExpandableAdapter extends ExpandableRecyclerAdapter<TopArtistsExpandableAdapter.TopParentViewHolder, TopArtistsExpandableAdapter.TopArtistViewHolder> {


    public TopArtistsExpandableAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
    }

    @Override
    public TopParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        return new TopParentViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_top_parent, viewGroup, false));
    }

    @Override
    public TopArtistViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        return new TopArtistViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item_artist_linear, viewGroup, false));
    }

    @Override
    public void onBindParentViewHolder(TopParentViewHolder topParentViewHolder, int i, Object parentObject) {
        TopParentObject parent = (TopParentObject) parentObject;
        topParentViewHolder.parentText.setText(parent.parentText);
    }

    @Override
    public void onBindChildViewHolder(TopArtistViewHolder topArtistViewHolder, int i, Object childObject) {
        ArtistFullObject artist = (ArtistFullObject) childObject;
        //        topArtistViewHolder.topArtistImage.
        topArtistViewHolder.topArtistName.setText(artist.name);
        topArtistViewHolder.topArtistGenre.setText(Utils.createStringFollowers(artist));
        topArtistViewHolder.topArtistFollowers.setText(Utils.createStringFollowers(artist));
    }


    public class TopParentViewHolder extends ParentViewHolder {

        public TextView     parentText;
        public ImageView    parentRightIcon;

        public TopParentViewHolder(View itemView) {
            super(itemView);
            parentText = (TextView) itemView.findViewById(R.id.recycler_item_top_parent_text);
            parentRightIcon = (ImageView) itemView.findViewById(R.id.recycler_item_top_parent_righticon);
        }
    }

    public class TopArtistViewHolder extends ChildViewHolder {

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

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    recyclerOnPosClickListener.onPosClick(v, getLayoutPosition());
//                }
//            });
        }

    }

//    public class TopTrackViewHolder extends TopChildViewHolder {
//
//        TextView topTrackTitle;
//        TextView topTrackArtists;
//        TextView topTrackAlbum;
//        TextView topTrackTime;
//        TextView topTrackPopularity;
//
//        public TopTrackViewHolder(View itemView) {
//            super(itemView);
//            topTrackTitle = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_title);
//            topTrackArtists = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_artists);
//            topTrackAlbum = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_albumname);
//            topTrackTime = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_time);
//            topTrackPopularity = (TextView) itemView.findViewById(R.id.recycler_item_track_withalbum_hype);
//
////            itemView.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    recyclerOnPosClickListener.onPosClick(v, getLayoutPosition());
////                }
////            });
//        }
//
//    }



}
