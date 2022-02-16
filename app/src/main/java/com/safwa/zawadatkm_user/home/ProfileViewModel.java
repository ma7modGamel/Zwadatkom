package com.safwa.zawadatkm_user.home;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.google.gson.Gson;
import com.safwa.zawadatkm_user.Models.ProfileUserModel;
import com.safwa.zawadatkm_user.Models.orders.OrdersDataSource;
import com.safwa.zawadatkm_user.Models.orders.OrdersDataSourceFactory;
import com.safwa.zawadatkm_user.Models.orders.OrdersListModel;
import com.safwa.zawadatkm_user.Network.RetroWeb;
import com.safwa.zawadatkm_user.Network.ServiceApi;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    GlobalPrefrencies globalPrefrencies;
    MutableLiveData<ProfileUserModel> profileUserModelMutableLiveData=new MutableLiveData<>();
    public void getUserInfo(Context context){
        globalPrefrencies=new GlobalPrefrencies(context);
        getOrders(context);
        RetroWeb.getClient().create(ServiceApi.class).onGetProfileInfo(globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<ProfileUserModel>() {
            @Override
            public void onResponse(Call<ProfileUserModel> call, Response<ProfileUserModel> response) {
                Log.e("8888",new Gson().toJson(response.body()));
                if (response.body() != null) {
                    profileUserModelMutableLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<ProfileUserModel> call, Throwable t) {

                Log.e("AAA",t.getMessage());
            }
        });
    }

    public LiveData<PagedList<OrdersListModel.Datum>> mutableLiveDataOrdersPageList;
    MutableLiveData<OrdersDataSource> ordersModelDataSourceMutableLiveData;

    public void getOrders(Context context) {
        OrdersDataSourceFactory OrdersModelDataSourceFactory = new OrdersDataSourceFactory(context);
        ordersModelDataSourceMutableLiveData = OrdersModelDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(OrdersDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataOrdersPageList = new LivePagedListBuilder<>(OrdersModelDataSourceFactory, config).build();

    }
}