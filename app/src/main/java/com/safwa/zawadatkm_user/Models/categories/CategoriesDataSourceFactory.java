package com.safwa.zawadatkm_user.Models.categories;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;


public class CategoriesDataSourceFactory extends DataSource.Factory<Long, CategoriesModel.Datum> {

    public MutableLiveData<CatigoriesDataSource> userLiveDataSource = new MutableLiveData<>();

    Context context;
    public CategoriesDataSourceFactory(Context context) {

        this.context=context;
    }


    @Override
    public DataSource<Long, CategoriesModel.Datum> create() {

        CatigoriesDataSource categoryDataSource = new CatigoriesDataSource(context);
        userLiveDataSource.postValue(categoryDataSource);
        return categoryDataSource;
    }
}
