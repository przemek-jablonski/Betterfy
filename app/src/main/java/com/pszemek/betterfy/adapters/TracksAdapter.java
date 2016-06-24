package com.pszemek.betterfy.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pszemek.betterfy.R;
import com.pszemek.betterfy.backend.models.TracksModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ciemek on 23/06/16.
 */
public class TracksAdapter extends RecyclerView.Adapter<TracksAdapter.ViewHolder>{

    private RecyclerOnPosClickListener recyclerOnPosClickListener;
    private List<TracksModel> items;


    public TracksAdapter() {
        super();
        Log.e("TracksAdapter", "constructor()");
        items = new LinkedList<>();
    }

    public TracksAdapter(List<TracksModel> items) {
        this();
        updateItems(items);
    }

    public void setRecyclerOnPosClickListener(RecyclerOnPosClickListener recyclerOnPosClickListener) {
        this.recyclerOnPosClickListener = recyclerOnPosClickListener;
    }

    public void updateItem(TracksModel item) {
        items.add(item);
        notifyItemInserted(getItemCount()-1); //todo: test this bit in parenthesis
    }

    public void updateItems(List<TracksModel> items) {
        items.clear();
        items.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.e("TracksAdapter", "oncreateViewHolder()");
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_track, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("TracksAdapter", "onBindViewHolder() for pos: " + position);
        TracksModel track = getItem(position);
        holder.artistTextView.setText(track.artists.get(0).name);
        holder.nameTextView.setText(track.name);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public List<TracksModel> getItems() {
        return items;
    }

    public TracksModel getItem(int position) {
        return items.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView    artistTextView;
        TextView    nameTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            artistTextView = (TextView) itemView.findViewById(R.id.recycler_item_track_artist);
            nameTextView = (TextView) itemView.findViewById(R.id.recycler_item_track_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (recyclerOnPosClickListener != null)
                        recyclerOnPosClickListener.onPosClick(v, getLayoutPosition());
                }
            });
        }

        @Override
        public void onClick(View v) {
            //...
        }
    }

}
