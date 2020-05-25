package com.example.goaapp;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        mView = itemView;

    }

    public void setDetails(Context ctx, String Title, String Location, String Image){
        TextView mTitleTv = mView.findViewById(R.id.rTitleTv);
        TextView mLocationTv = mView.findViewById(R.id.rLocationTv);
        ImageView mImageIv = mView.findViewById(R.id.rImageView);

        mTitleTv.setText(Title);
        mLocationTv.setText(Location);
        Picasso.get().load(Image).into(mImageIv);
    }
}

