package com.hundredvisions.discover.discovernewberg;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by winikkc on 2/28/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter {
    private ArrayList<Model> dataset;
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<Model> mlist, Context context) {
        this.dataset = mlist;
        this.mContext = context;
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        TextView title, subtitle;
        ImageView imageView;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.title = (TextView) itemView.findViewById(R.id.title);
            this.subtitle = (TextView) itemView.findViewById(R.id.subtitle);
            this.imageView = (ImageView) itemView.findViewById(R.id.Icon);
        }
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_details, parent, false);
        return new ImageTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Model object = dataset.get(position);

        ( (ImageTypeViewHolder) holder).title.setText( object.title);
        ( (ImageTypeViewHolder) holder).subtitle.setText(object.subtitle);
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


}
