package com.marvel.comicstore.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.marvel.comicstore.R;

import com.marvel.comicstore.model.ComicData;
import com.marvel.comicstore.ui.CartComicAdapter;


import java.util.ArrayList;


    public class CartFragment extends Fragment {

    static ArrayList<ComicData> comicsInCart = new ArrayList<>();
    CartComicAdapter itemArrayAdapter = new CartComicAdapter(R.layout.list_comics_adapter_view, comicsInCart);
    private RecyclerView mRecyclerView;
    private Button mBuy;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


        mRecyclerView = view.findViewById(R.id.ComicsInCart);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(itemArrayAdapter);

    }


    public View.OnClickListener buy(){
        //TODO: Metodo para esvaziar o carrinho

        return null;
    }

    static public View.OnClickListener cartAdd(ComicData comic){
        comicsInCart.add(comic);
        return null;
    }

}
