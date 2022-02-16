package com.safwa.zawadatkm_user.Models.orders;

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

public class OrdersDataSource extends PageKeyedDataSource<Long, OrdersListModel.Datum> {
    public static int PAGE_SIZE = 8;
    public static long FIRST_PAGE = 1;
    public static long CoutItem=0;
    GlobalPrefrencies globalPrefrencies;
    public OrdersDataSource(Context context) {
        globalPrefrencies=new GlobalPrefrencies(context);
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, OrdersListModel.Datum> callback) {

        RetroWeb.getClient().create(ServiceApi.class).onGetAllOrders(FIRST_PAGE,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<OrdersListModel>() {
            @Override
            public void onResponse(Call<OrdersListModel> call, Response<OrdersListModel> response) {
                OrdersListModel body = response.body();

                Log.e("IIIII",body.getOrders().getTotal()+"");

                if (body != null) {
                    List<OrdersListModel.Datum> data = body.getOrders().getData();
                    CoutItem=body.getOrders().getTotal();
                    callback.onResult(data, null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<OrdersListModel> call, Throwable t) {

                Log.e("SSSFF",t.getMessage());
            }
        });

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, OrdersListModel.Datum> callback) {

        RetroWeb.getClient().create(ServiceApi.class).onGetAllOrders(params.key,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<OrdersListModel>() {
            @Override
            public void onResponse(Call<OrdersListModel> call, Response<OrdersListModel> response) {
                OrdersListModel body = response.body();
                if (body != null) {
                    List<OrdersListModel.Datum> data = body.getOrders().getData();
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
            public void onFailure(Call<OrdersListModel> call, Throwable t) {
                Log.e("SSSFF",t.getMessage());
            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, OrdersListModel.Datum> callback) {

        RetroWeb.getClient().create(ServiceApi.class).onGetAllOrders(params.key,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<OrdersListModel>() {
            @Override
            public void onResponse(Call<OrdersListModel> call, Response<OrdersListModel> response) {
                OrdersListModel body = response.body();
                if (body != null) {
                    List<OrdersListModel.Datum> data = body.getOrders().getData();
                    callback.onResult(data, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<OrdersListModel> call, Throwable t) {
                Log.e("SSSFF",t.getMessage());

            }
        });

    }
}
