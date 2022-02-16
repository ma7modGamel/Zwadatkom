package com.safwa.zawadatkm_user.Models.productwithcat;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.safwa.zawadatkm_user.Models.Datum;


public class ProductsCatDataSourceFactory extends DataSource.Factory<Long, Datum> {

    public MutableLiveData<ProductsCatDataSource> userLiveDataSource = new MutableLiveData<>();

    String id;
    Context context;
    public ProductsCatDataSourceFactory(Context context,String id) {

        this.id=id;
        this.context=context;
    }


    @Override
    public DataSource<Long, Datum> create() {

        ProductsCatDataSource ProductsCatDataSource = new ProductsCatDataSource(context,id);
        userLiveDataSource.postValue(ProductsCatDataSource);
        return ProductsCatDataSource;
    }
}
