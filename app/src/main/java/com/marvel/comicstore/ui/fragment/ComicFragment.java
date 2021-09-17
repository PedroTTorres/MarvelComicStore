package com.marvel.comicstore.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.marvel.comicstore.R;
import com.marvel.comicstore.model.ComicData;
import com.marvel.comicstore.model.Image;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


public class ComicFragment extends Fragment {
    public static final String COMIC = "COMIC";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ComicData comic = (ComicData) getArguments().getSerializable(COMIC);
        TextView title = view.findViewById(R.id.Title);
        ImageView image = view.findViewById(R.id.Comic);
        TextView description = view.findViewById(R.id.Description);
        Button addToCart = view.findViewById(R.id.AddtoCart);

        title.setText(comic.getTitle());
        Picasso.get()
                .load(comic.getThumbnail().getUrl())
                .fit()
                .placeholder(R.drawable.empty_image)
                .error(R.drawable.empty_image)
                .centerInside()
                .tag(view)
                .into(image);

        description.setText(comic.getmDescription());

        //Adiciona revista ao carrinho
        addToCart.setOnClickListener(CartFragment.cartAdd(comic));

    }
}
