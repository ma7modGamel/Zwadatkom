package com.safwa.zawadatkm_user.myorders.ui.orders.all;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.safwa.zawadatkm_user.Models.orders.OrdersDataSource;
import com.safwa.zawadatkm_user.Models.orders.OrdersDataSourceFactory;
import com.safwa.zawadatkm_user.Models.orders.OrdersListModel;

public class AllorderViewModel extends ViewModel {


    // TODO: Implement the ViewModel
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