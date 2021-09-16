package com.marvel.comicstore.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marvel.comicstore.R;
import com.marvel.comicstore.model.ComicData;

import java.util.List;

public class ComicsAdapter extends RecyclerView.Adapter<ComicsAdapter.LineHolder>{

    private List<ComicData> mComic;
    private Context mContext;

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
        holder.mPrice.setText("U$ 50,00"); //TODO: Get from comic
        holder.mMoreButton.setOnClickListener(view -> {
            showMore(comic);
        });
    }

    private void showMore(ComicData Comic) {
        Intent intent = new Intent(mContext, ComicActivity.class);
        intent.putExtra(MainActivity.COMIC, Comic);
        mContext.startActivity(intent);
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

    class LineHolder extends RecyclerView.ViewHolder {

        private TextView mTitle;
        private TextView mPrice;
        private ImageButton mMoreButton;

        LineHolder(View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.list_comic_name);
            mPrice = itemView.findViewById(R.id.list_comic_price);
            mMoreButton = itemView.findViewById(R.id.list_comic_details);
        }
    }
}



