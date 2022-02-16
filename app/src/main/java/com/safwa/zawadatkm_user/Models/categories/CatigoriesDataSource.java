package com.safwa.zawadatkm_user.Models.categories;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.safwa.zawadatkm_user.Network.RetroWeb;
import com.safwa.zawadatkm_user.Network.ServiceApi;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CatigoriesDataSource extends PageKeyedDataSource<Long, CategoriesModel.Datum> {
    public static int PAGE_SIZE = 8;
    public static long FIRST_PAGE = 1;

    GlobalPrefrencies globalPrefrencies;
    public CatigoriesDataSource(Context context) {
        globalPrefrencies=new GlobalPrefrencies(context);
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, CategoriesModel.Datum> callback) {

        RetroWeb.getClient().create(ServiceApi.class).onGetAllCategories(FIRST_PAGE,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<CategoriesModel>() {
            @Override
            public void onResponse(Call<CategoriesModel> call, Response<CategoriesModel> response) {
                CategoriesModel body = response.body();
                if (body != null) {
                    List<CategoriesModel.Datum> data = body.getCategories().getData();
                    callback.onResult(data, null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<CategoriesModel> call, Throwable t) {

                Log.e("XXSFF0",t.getMessage());
            }
        });

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, CategoriesModel.Datum> callback) {

        RetroWeb.getClient().create(ServiceApi.class).onGetAllCategories(params.key,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<CategoriesModel>() {
            @Override
            public void onResponse(Call<CategoriesModel> call, Response<CategoriesModel> response) {
                CategoriesModel body = response.body();
                if (body != null) {
                    List<CategoriesModel.Datum> data = body.getCategories().getData();
                    long key;
                    if (params.key > 1) {
                        key = params.key - 1;
                    } else {
                        key = 0;
                    }
                    callback.onResult(data, key);
                }

            }

            @Override
            public void onFailure(Call<CategoriesModel> call, Throwable t) {
                Log.e("YYSFF0",t.getMessage());
            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, CategoriesModel.Datum> callback) {

        RetroWeb.getClient().create(ServiceApi.class).onGetAllCategories(params.key,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<CategoriesModel>() {
            @Override
            public void onResponse(Call<CategoriesModel> call, Response<CategoriesModel> response) {
                CategoriesModel body = response.body();
                if (body != null) {
                    List<CategoriesModel.Datum> data = body.getCategories().getData();
                    callback.onResult(data, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<CategoriesModel> call, Throwable t) {
                Log.e("ZZSFF0",t.getMessage());
            }
        });
    }
}

