package com.safwa.zawadatkm_user.products.ui.main;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.safwa.zawadatkm_user.Models.Datum;
import com.safwa.zawadatkm_user.Models.categories.CategoriesDataSourceFactory;
import com.safwa.zawadatkm_user.Models.categories.CategoriesModel;
import com.safwa.zawadatkm_user.Models.categories.CatigoriesDataSource;
import com.safwa.zawadatkm_user.Models.productwithcat.ProductsCatDataSource;
import com.safwa.zawadatkm_user.Models.productwithcat.ProductsCatDataSourceFactory;

public class ProducViewModel extends ViewModel {
    // TODO: Implement the ViewModel
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

    public LiveData<PagedList<Datum>> mutableLiveDataProductsPageList;
    MutableLiveData<ProductsCatDataSource> productsModelDataSourceMutableLiveData;

    public void initPro(Context context,String id) {
        ProductsCatDataSourceFactory productsDataSourceFactory = new ProductsCatDataSourceFactory(context,id);
        productsModelDataSourceMutableLiveData = productsDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(ProductsCatDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataProductsPageList = new LivePagedListBuilder<>(productsDataSourceFactory, config).build();

    }


}