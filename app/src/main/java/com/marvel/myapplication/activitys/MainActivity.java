package com.marvel.myapplication.activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;
import com.marvel.myapplication.R;
import com.marvel.myapplication.adapters.ComicsAdapter;
import com.marvel.myapplication.apisetup.Request;
import com.marvel.myapplication.config.RetrofitConfig;
import com.marvel.myapplication.interfaces.Comics;
import com.marvel.myapplication.modules.ComicData;
import com.marvel.myapplication.modules.ComicList;
import com.marvel.myapplication.modules.DataWrapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String COMIC = "COMIC";
    private int mLimit = 20;

    private RecyclerView mRecyclerView;
    private ComicsAdapter mAdapter;

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

            Request request = new Request();
            request.setLimit(mLimit);
            request.setOffset(mOffset);

            Call<DataWrapper<ComicData>> call = new RetrofitConfig().getComicsService().ListofComics(
                    request.getLimit(),
                    request.getOffset(),
                    request.getTs(),
                    request.getPublicKey(),
                    request.getHash(),
                    request.getName());
            call.enqueue(new Callback<DataWrapper<ComicData>>() {
                @Override
                public void onResponse(Call<DataWrapper<ComicData>> call, Response<DataWrapper<ComicData>> response) {
                    DataWrapper<ComicData> result = response.body();
                    mComics = result.getData().getResults();
                    mAdapter.updateList(mComics);
                    mOffset = mOffset + mLimit;
                    mLoading = true;
                    progressDialog.dismiss();
                }


                @Override
                public void onFailure(Call<DataWrapper<ComicData>> call, Throwable t) {
                    msgFailure();
                    progressDialog.dismiss();

                }
            }

            );

        }

        private void msgFailure(){
            View parentLayout = findViewById(android.R.id.content);
            Snackbar.make(parentLayout, getResources().getString(R.string.erro_conexao), Snackbar.LENGTH_LONG).show();
        }

}