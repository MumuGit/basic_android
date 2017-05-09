package com.ybslux.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.ybslux.android.R;

import java.util.List;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.MyViewHolder> {
    private List<Class> mList;
    private Context mContext;

    public ContentAdapter(List<Class> list) {
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        MyViewHolder vh = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_name, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.name_tv.setText(mList.get(position).getSimpleName());
        holder.content_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext,mList.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView content_cv;
        TextView name_tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            content_cv = (CardView) itemView;
            name_tv= (TextView) itemView.findViewById(R.id.name_tv);

        }
    }
}
