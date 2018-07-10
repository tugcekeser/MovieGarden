package com.example.rabia.moviegarden.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rabia.moviegarden.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rabia on 7/9/18.
 */

public class ViewHolderTrailers extends RecyclerView.ViewHolder {

    @BindView(R.id.ivPlay)
    ImageView ivPlay;

    @BindView(R.id.ivPoster)
    ImageView ivPoster;

    @BindView(R.id.tvTitle)
    TextView tvTitle;

    @BindView(R.id.tvDesc)
    TextView tvDesc;

    public ViewHolderTrailers(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public ImageView getIvPlay() {
        return ivPlay;
    }

    public ImageView getIvPoster() {
        return ivPoster;
    }

    public TextView getTvDesc() {
        return tvDesc;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }
}
