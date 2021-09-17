package com.marvel.comicstore.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.marvel.comicstore.R;
import com.marvel.comicstore.model.ComicData;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import java.util.List;
import java.util.Locale;

public class ComicsAdapter extends RecyclerView.Adapter<ComicsAdapter.LineHolder>{
    private List<ComicData> mComic;
    private Context mContext;
    private NavController mNavController;
    public static final String COMIC = "COMIC";

    public ComicsAdapter(Context context, List<ComicData> cList) {
        mContext = context;
        mComic = cList;
    }

    @Override
    public LineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_comics_adapter_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LineHolder holder, final int position) {
        ComicData comic = mComic.get(position);
        holder.mTitle.setText(comic.getTitle());
        holder.mPrice.setText(comic.getPrice());
        Picasso.get()
                .load(comic.getThumbnail().getUrl())
                .fit()
                .placeholder(R.drawable.empty_image)
                .error(R.drawable.empty_image)
                .centerInside()
                .tag(mContext)
                .into(holder.mImage);
        holder.mMoreButton.setOnClickListener(view -> {
            showMore(comic);
        });
    }

    private void showMore(ComicData comic) {
        if (mNavController != null) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(COMIC, comic);
            mNavController.navigate(R.id.nav_comic, bundle);
        }
    }

    @Override
    public int getItemCount() {
        return mComic != null ? mComic.size() : 0;
    }

    public void updateList(List<ComicData> comic) {
        int size = getItemCount();
        mComic.addAll(comic);
        notifyItemRangeInserted(size, getItemCount());
    }

    public void setNavController(NavController mNavController) {
        this.mNavController = mNavController;
    }

    class LineHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mPrice;
        private ImageView mImage;
        private ImageButton mMoreButton;

        LineHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.list_comic_name);
            mPrice = itemView.findViewById(R.id.list_comic_price);
            mImage = itemView.findViewById(R.id.icon);
            mMoreButton = itemView.findViewById(R.id.list_comic_details);
        }
    }
}






