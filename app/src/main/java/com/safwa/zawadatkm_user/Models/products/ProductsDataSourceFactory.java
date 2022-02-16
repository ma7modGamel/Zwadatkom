package com.safwa.zawadatkm_user.Models.products;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.safwa.zawadatkm_user.Models.Datum;


public class ProductsDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<ProductsDataSource> userLiveDataSource = new MutableLiveData<>();

    Context context;
    public ProductsDataSourceFactory(Context context) {

        this.context=context;
    }


    @Override
    public DataSource<Long, Datum> create() {

        ProductsDataSource productsDataSource = new ProductsDataSource(context);
        userLiveDataSource.postValue(productsDataSource);
        return productsDataSource;
    }
}
