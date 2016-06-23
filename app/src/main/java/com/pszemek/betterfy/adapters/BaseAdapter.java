package com.pszemek.betterfy.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.List;

/**
 * Created by Ciemek on 23/06/16.
 */
//public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {
//
//    public interface RecyclerOnPosClickListener {
//        void onPosClick(View v, int position);
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        public ViewHolder(View itemView) {
//            super(itemView);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (recyclerOnPosClickListener != null)
//                        recyclerOnPosClickListener.onPosClick(v, getLayoutPosition());
//                }
//            });
//        }
//
//        @Override
//        public void onClick(View v) {
//            //...
//        }
//    }
//
//    private RecyclerOnPosClickListener recyclerOnPosClickListener;
//    protected List<T> items;
//
//    public BaseAdapter() {
//        super();
//    }
//
//    public BaseAdapter(List<T> items) {
//
//    }
//
//    public void updateItems(List<T> items) {
//        this.items.clear();
//        this.items.addAll(items);
//        notifyDataSetChanged();
//    }
//
//    public void updateItem(T item) {
//        items.add(item);
//        notifyDataSetChanged();
//    }
//
//    public T getItem(int position){
//        return items.get(position);
//    }
//
//    public List<T> getItems() {
//        return items;
//    }
//
//}
