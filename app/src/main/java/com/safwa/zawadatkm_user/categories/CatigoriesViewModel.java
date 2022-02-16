package com.safwa.zawadatkm_user.categories;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.safwa.zawadatkm_user.Models.categories.CategoriesDataSourceFactory;
import com.safwa.zawadatkm_user.Models.categories.CategoriesModel;
import com.safwa.zawadatkm_user.Models.categories.CatigoriesDataSource;

public class CatigoriesViewModel extends ViewModel {

    public LiveData<PagedList<CategoriesModel.Datum>> mutableLiveDataCatigoriesPageList;
    MutableLiveData<CatigoriesDataSource> CategoriesModelDataSourceMutableLiveData;

    public void initCat(Context context) {
        CategoriesDataSourceFactory categoriesDataSourceFactory = new CategoriesDataSourceFactory(context);
        CategoriesModelDataSourceMutableLiveData = categoriesDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(CatigoriesDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataCatigoriesPageList = new LivePagedListBuilder<>(categoriesDataSourceFactory, config).build();

    }
}

