package com.marvel.comicstore.controller;

import android.content.Context;

import com.marvel.comicstore.apisetup.Request;
import com.marvel.comicstore.config.RetrofitConfig;
import com.marvel.comicstore.model.ComicData;
import com.marvel.comicstore.model.DataWrapper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainController {

    public static final int COMIC_LIMIT = 30;
    public static final String IMG_NOT_AVAILABLE = "image_not_available";

    Context mContext;
    RetrofitConfig mRetrofitConfig;

    public MainController() {
        mRetrofitConfig = new RetrofitConfig();
    }

    public void getComicData(int offset, ComicDataCallback callback) {
        Request request = new Request();
        request.setLimit(COMIC_LIMIT);
        request.setOffset(offset);

        Call<DataWrapper<ComicData>> call = mRetrofitConfig.getComicsService().ListofComics(
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
                             if (result != null && result.getData() != null) {
                                 List<ComicData> comics = filter(result.getData().getResults());
                                 callback.onSuccessResponse(comics);
                             } else {
                                 callback.onSuccessResponse(new ArrayList<>());
                             }
                         }

                         @Override
                         public void onFailure(Call<DataWrapper<ComicData>> call, Throwable t) {
                             callback.onFailure(t);
                         }
                     }

        );
    }

    private List<ComicData> filter(List<ComicData> comics) {
        List<ComicData> filteredComics = new ArrayList<>();
        for (ComicData comic : comics) {
            if (!comic.getThumbnail().getUrl().contains(IMG_NOT_AVAILABLE)) {
                filteredComics.add(comic);
            }
        }
        return filteredComics;
    }


    public interface ComicDataCallback {
        void onSuccessResponse(List<ComicData> comics);
        void onFailure(Throwable t);
    }
}
