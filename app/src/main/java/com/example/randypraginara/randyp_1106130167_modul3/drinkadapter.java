package com.example.randypraginara.randyp_1106130167_modul3;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.ArrayList;

public class drinkadapter extends RecyclerView.Adapter<drinkadapter.AirViewHolder> {

    private GradientDrawable mGradient;
    private ArrayList<drink> mAirData;
    private Context mContext;

    drinkadapter(Context context, ArrayList<drink> airData){
        this.mAirData = airData;
        this.mContext = context;

        mGradient = new GradientDrawable();
        mGradient.setColor(Color.GRAY);

        Drawable drawable = ContextCompat.getDrawable(mContext, R.drawable.img_ades);
        if (drawable!=null){
            mGradient.setSize(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        }
    }


    @Override
    public AirViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new AirViewHolder(mContext, LayoutInflater.from(mContext).inflate(R.layout.activity_list_item, parent, false), mGradient);
    }

    @Override
    public void onBindViewHolder(drinkadapter.AirViewHolder holder, int position) {
        drink currentAir = mAirData.get(position);

        holder.bindTo(currentAir);
        Glide.with(mContext).load(currentAir.getImage()).into(holder.mAirImage);
    }

    @Override
    public int getItemCount() {
        return mAirData.size();
    }

    class AirViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView mAirImage;
        private TextView mTitle;
        private TextView mInfo;
        private Context mContext;
        private drink mCurrentAir;
        private GradientDrawable mGradient;
        private String txtTitle;

        AirViewHolder(Context context, View itemView, GradientDrawable gradientDrawable){
            super(itemView);

            mAirImage = (ImageView) itemView.findViewById(R.id.drinkImage);
            mTitle = (TextView) itemView.findViewById(R.id.labelTitle);
            mInfo = (TextView) itemView.findViewById(R.id.infoTitle);

            mContext = context;
            mGradient = gradientDrawable;

            itemView.setOnClickListener(this);

        }

        void bindTo(drink currentAir){
            mTitle.setText(currentAir.getTitle());
            mInfo.setText(currentAir.getInfo());

            mCurrentAir = currentAir;
            txtTitle = mTitle.getText().toString();
            Glide.with(mContext).load(currentAir.getImage()).placeholder(mGradient).into(mAirImage);
        }

        @Override
        public void onClick(View view) {
            Intent detail = drink.starter(mContext, mCurrentAir.getTitle(), mCurrentAir.getImage());
            detail.putExtra("title", txtTitle);
            mContext.startActivity(detail);
        }
    }
}

