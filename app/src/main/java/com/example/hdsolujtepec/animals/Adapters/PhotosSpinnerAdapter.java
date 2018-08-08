package com.example.hdsolujtepec.animals.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.hdsolujtepec.animals.R;

public class PhotosSpinnerAdapter extends BaseAdapter{

    private LayoutInflater mInflater;
    private int data[];

    public PhotosSpinnerAdapter(Context context, int[] data) {
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return data[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private static class ViewHolder {
        ImageView imageViewEmoticon;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View itemView = view;
        ViewHolder emoticonViewHolder;

        if (view == null) {
            itemView = mInflater.inflate(R.layout.spinner_row, viewGroup, false);
            emoticonViewHolder = new ViewHolder();
            emoticonViewHolder.imageViewEmoticon = (ImageView) itemView.findViewById(R.id.spinnerImage);
            itemView.setTag(emoticonViewHolder);

        } else {
            emoticonViewHolder = (ViewHolder) itemView.getTag();
        }

        emoticonViewHolder.imageViewEmoticon.setImageResource(data[position]);

        return itemView;
    }
}
