package com.marvel.comicstore.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.marvel.comicstore.R;
import com.marvel.comicstore.controller.MainController;
import com.marvel.comicstore.model.ComicData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String COMIC = "COMIC";


    private RecyclerView mRecyclerView;
    private ComicsAdapter mAdapter;
    private MainController mController;

    private List<ComicData> mComics;
    private boolean mLoading;
    private int mPastVisibleItems;
    private int mVisibleItemCount;
    private int mTotalItemCount;
    private int mOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mController = new MainController();
        mRecyclerView = findViewById(R.id.Main_rvComics);

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
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new ComicsAdapter(MainActivity.this, mComics);
        mRecyclerView.setAdapter(mAdapter);

        // Divider Decoration
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
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
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage(getResources().getString(R.string.msg_loading));
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
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, getResources().getString(R.string.erro_conexao), Snackbar.LENGTH_LONG).show();
    }

}
