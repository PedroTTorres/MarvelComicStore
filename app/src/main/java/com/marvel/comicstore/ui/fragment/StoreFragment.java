package com.marvel.comicstore.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.marvel.comicstore.R;
import com.marvel.comicstore.controller.MainController;
import com.marvel.comicstore.model.ComicData;
import com.marvel.comicstore.ui.ComicsAdapter;

import java.util.ArrayList;
import java.util.List;

public class StoreFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ComicsAdapter mAdapter;
    private MainController mController;
    private List<ComicData> mComics;
    private NavController mNavController;
    private boolean mLoading;
    private int mPastVisibleItems;
    private int mVisibleItemCount;
    private int mTotalItemCount;
    private int mOffset;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_store, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mController = new MainController();
        mRecyclerView = view.findViewById(R.id.Main_rvComics);

        init();
        setupRecycler();
        start();
    }

    private void init() {
        mComics = new ArrayList<>();
        mLoading = true;
        mOffset = 0;
    }

    private void setupRecycler() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new ComicsAdapter(getContext(), mComics);
        mRecyclerView.setAdapter(mAdapter);
        if (getActivity() != null) {
            mNavController = Navigation.findNavController(getView());
            mAdapter.setNavController(mNavController);
        }
        // Divider Decoration
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                //check for scroll down
                if (dy > 0) {
                    mVisibleItemCount = layoutManager.getChildCount();
                    mTotalItemCount = layoutManager.getItemCount();
                    mPastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (mLoading) {
                        if ((mVisibleItemCount + mPastVisibleItems) >= mTotalItemCount) {
                            mLoading = false;
                            start();
                        }
                    }
                }
            }
        });

    }

    public void start() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.msg_loading));
        progressDialog.setCancelable(false);
        progressDialog.show();

        mController.getComicData(mOffset, new MainController.ComicDataCallback() {
            @Override
            public void onSuccessResponse(List<ComicData> comics) {
                mAdapter.updateList(comics);
                mOffset = mOffset + MainController.COMIC_LIMIT;
                mLoading = true;
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                msgFailure();
                progressDialog.dismiss();
            }
        });
    }

    private void msgFailure(){
        View parentLayout = getView().findViewById(android.R.id.content);
        Snackbar.make(parentLayout, getResources().getString(R.string.erro_conexao), Snackbar.LENGTH_LONG).show();
    }
}
