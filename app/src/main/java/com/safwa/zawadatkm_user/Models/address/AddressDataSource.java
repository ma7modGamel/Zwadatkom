package com.safwa.zawadatkm_user.Models.address;

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

public class AddressDataSource extends PageKeyedDataSource<Long, AddressListModel.Datum> {
    public static int PAGE_SIZE = 8;
    public static long FIRST_PAGE = 1;

    GlobalPrefrencies globalPrefrencies;
    public AddressDataSource(Context context) {
        globalPrefrencies=new GlobalPrefrencies(context);
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, AddressListModel.Datum> callback) {

        RetroWeb.getClient().create(ServiceApi.class).onGetAllAddress(FIRST_PAGE,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<AddressListModel>() {
            @Override
            public void onResponse(Call<AddressListModel> call, Response<AddressListModel> response) {
                AddressListModel body = response.body();

                if (body != null) {
                    List<AddressListModel.Datum> data = body.getData().getData();
                    callback.onResult(data, null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<AddressListModel> call, Throwable t) {

                Log.e("SSSFF",t.getMessage());
            }
        });

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, AddressListModel.Datum> callback) {

        RetroWeb.getClient().create(ServiceApi.class).onGetAllAddress(params.key,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<AddressListModel>() {
            @Override
            public void onResponse(Call<AddressListModel> call, Response<AddressListModel> response) {
                AddressListModel body = response.body();
                if (body != null) {
                    List<AddressListModel.Datum> data = body.getData().getData();
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
            public void onFailure(Call<AddressListModel> call, Throwable t) {
                Log.e("SSSFF",t.getMessage());
            }
        });
    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, AddressListModel.Datum> callback) {

        RetroWeb.getClient().create(ServiceApi.class).onGetAllAddress(params.key,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<AddressListModel>() {
            @Override
            public void onResponse(Call<AddressListModel> call, Response<AddressListModel> response) {
                AddressListModel body = response.body();
                if (body != null) {
                    List<AddressListModel.Datum> data = body.getData().getData();
                    callback.onResult(data, params.key + 1);
                }
            }

            @Override
            public void onFailure(Call<AddressListModel> call, Throwable t) {
                Log.e("SSSFF",t.getMessage());

            }
        });

    }
}
