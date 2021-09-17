package com.marvel.comicstore.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.marvel.comicstore.R;
import com.marvel.comicstore.model.ComicData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartComicAdapter extends RecyclerView.Adapter<CartComicAdapter.ViewHolder> {


    private int listItemLayout;
    private ArrayList<ComicData> itemList;
    public CartComicAdapter(int layoutId, ArrayList<ComicData> itemList) {
        listItemLayout = layoutId;
        this.itemList = itemList;
    }


    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView item = holder.title;
        TextView price = holder.price;
        Picasso.get()
                .load(itemList.get(listPosition).getThumbnail().getUrl())
                .fit()
                .placeholder(R.drawable.empty_image)
                .error(R.drawable.empty_image)
                .centerInside()
                .tag(holder)
                .into(holder.image);

        item.setText(itemList.get(listPosition).getTitle());
        price.setText(itemList.get(listPosition).getPrice());
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public ImageView image;
        public TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.list_comic_name);
            image = (ImageView) itemView.findViewById(R.id.icon);
            price = (TextView) itemView.findViewById(R.id.list_comic_price);
        }

    }
}