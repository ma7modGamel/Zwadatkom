package com.safwa.zawadatkm_user.Models.products;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.safwa.zawadatkm_user.Models.Datum;
import com.safwa.zawadatkm_user.Network.RetroWeb;
import com.safwa.zawadatkm_user.Network.ServiceApi;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductsDataSource extends PageKeyedDataSource<Long, Datum> {
    public static int PAGE_SIZE = 8;
    public static long FIRST_PAGE = 1;

    GlobalPrefrencies globalPrefrencies;
    public ProductsDataSource(Context context) {
        globalPrefrencies=new GlobalPrefrencies(context);
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Datum> callback) {

        RetroWeb.getClient().create(ServiceApi.class).onGetAllProduct(FIRST_PAGE,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<ProductsModel>() {
            @Override
            public void onResponse(Call<ProductsModel> call, Response<ProductsModel> response) {
                ProductsModel body = response.body();
                if (body != null) {
                    List<Datum> data = body.getProducts().getData();
                    callback.onResult(data, null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<ProductsModel> call, Throwable t) {

                Log.e("OOSSFF0",t.getMessage());
            }
        });

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Datum> callback) {

        RetroWeb.getClient().create(ServiceApi.class).onGetAllProduct(params.key,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<ProductsModel>() {
            @Override
            public void onResponse(Call<ProductsModel> call, Response<ProductsModel> response) {
                ProductsModel body = response.body();
                if (body != null) {
                    List<Datum> data = body.getProducts().getData();
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
            public void onFailure(Call<ProductsModel> call, Throwable t) {
                Log.e("OPSSFF0",t.getMessage());
            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Datum> callback) {

        RetroWeb.getClient().create(ServiceApi.class).onGetAllProduct(params.key,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<ProductsModel>() {
            @Override
            public void onResponse(Call<ProductsModel> call, Response<ProductsModel> response) {
                ProductsModel body = response.body();
                if (body != null) {
                    List<Datum> data = body.getProducts().getData();
                    callback.onResult(data, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<ProductsModel> call, Throwable t) {
                Log.e("OCSFF0",t.getMessage());
            }
        });
    }
}

