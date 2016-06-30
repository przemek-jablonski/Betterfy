package com.pszemek.betterfy.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.pszemek.betterfy.backend.models.SpotifyBaseResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ciemek on 24/06/16.
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected RecyclerOnPosClickListener  recyclerOnPosClickListener;
    protected List<T>                     items;


    /**
     * ArrayList was chosen for item list instead of LinkedList, because usually
     * there is no need to edit server's fetched response body, and there is
     * a lot of getting specific position and traversal operations, so ArrayList suits this stuff better.
     */
    public BaseAdapter() {
        super();
        items = new ArrayList<>();
        Log.e(BaseAdapter.class.getSimpleName(), "constructor()");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setRecyclerOnPosClickListener(RecyclerOnPosClickListener recyclerOnPosClickListener) {
        this.recyclerOnPosClickListener = recyclerOnPosClickListener;
    }

    public void addItem(T item) {
        items.add(item);
        notifyItemInserted(items.size()-1);
    }

    public void addItems(List<T> items) {
        items.addAll(items);
        notifyDataSetChanged();
    }

    public void updateItems(List<T> item) {
        items.clear();
        items.addAll(item);
        notifyDataSetChanged();
    }

    public void updateItems(SpotifyBaseResponse<T> response) {
        updateItems(response.items);
    }

    public List<T> getItems() {
        return items;
    }

    public T getItem(int position) {
        return items.get(position);
    }
}
