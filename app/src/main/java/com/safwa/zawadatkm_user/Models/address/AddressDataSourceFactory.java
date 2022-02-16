package com.safwa.zawadatkm_user.Models.address;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;


public class AddressDataSourceFactory extends DataSource.Factory<Long, AddressListModel.Datum> {

    public MutableLiveData<AddressDataSource> userLiveDataSource = new MutableLiveData<>();

    Context context;
    public AddressDataSourceFactory(Context context) {
        this.context=context;
    }


    @Override
    public DataSource<Long, AddressListModel.Datum> create() {

        AddressDataSource categoryDataSource = new AddressDataSource(context);
        userLiveDataSource.postValue(categoryDataSource);
        return categoryDataSource;
    }
}
