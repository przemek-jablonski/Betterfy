package com.pszemek.betterfy.viewholders;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.pszemek.betterfy.adapters.RecyclerOnPosClickListener;

/**
 * Created by Ciemek on 01/07/16.
 */
public abstract class AbstractBaseViewHolder extends RecyclerView.ViewHolder {

    public AbstractBaseViewHolder(View itemView, @Nullable final RecyclerOnPosClickListener onPosClickListener) {
        super(itemView);
        if (onPosClickListener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onPosClickListener.onPosClick(v, getLayoutPosition());
                }
            });
        }
    }
}
