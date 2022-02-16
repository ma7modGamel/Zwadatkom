package com.safwa.zawadatkm_user.Models.orders;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

public class OrdersDataSourceFactory extends DataSource.Factory<Long, OrdersListModel.Datum> {

    public MutableLiveData<OrdersDataSource> userLiveDataSource = new MutableLiveData<>();

    Context context;
    public OrdersDataSourceFactory(Context context) {

        this.context=context;
    }


    @Override
    public DataSource<Long, OrdersListModel.Datum> create() {

        OrdersDataSource categoryDataSource = new OrdersDataSource(context);
        userLiveDataSource.postValue(categoryDataSource);
        return categoryDataSource;
    }
}
