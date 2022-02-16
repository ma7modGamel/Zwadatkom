package com.safwa.zawadatkm_user.home;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.safwa.zawadatkm_user.Models.Datum;
import com.safwa.zawadatkm_user.Models.categories.CategoriesDataSourceFactory;
import com.safwa.zawadatkm_user.Models.categories.CategoriesModel;
import com.safwa.zawadatkm_user.Models.categories.CatigoriesDataSource;
import com.safwa.zawadatkm_user.Models.offer.OffersModel;
import com.safwa.zawadatkm_user.Models.products.ProductsDataSource;
import com.safwa.zawadatkm_user.Models.products.ProductsDataSourceFactory;
import com.safwa.zawadatkm_user.Network.RetroWeb;
import com.safwa.zawadatkm_user.Network.ServiceApi;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    MutableLiveData<OffersModel> OffersModelDataSourceMutableLiveData=new MutableLiveData<>();

    public HomeViewModel(Context context) {
        init(context);
        initCat(context);
        initPro(context);
    }

    GlobalPrefrencies globalPrefrencies;
    public void init(Context context) {
        globalPrefrencies=new GlobalPrefrencies(context);
//        OfferDataSourceFactory offersModelDataSourceFactory = new OfferDataSourceFactory(context);
//        OffersModelDataSourceMutableLiveData = offersModelDataSourceFactory.userLiveDataSource;
//        PagedList.Config config = new PagedList.Config.Builder()
//                .setEnablePlaceholders(false)
//                .setPageSize(OfferDataSource.PAGE_SIZE)
//                .build();
//        mutableLiveDataOrdersPageList = new LivePagedListBuilder<>(offersModelDataSourceFactory, config).build();

        RetroWeb.getClient().create(ServiceApi.class).onGetAllOffer(globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<OffersModel>() {
            @Override
            public void onResponse(Call<OffersModel> call, Response<OffersModel> response) {
                OffersModel body = response.body();

                if (body != null) {
                    OffersModelDataSourceMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<OffersModel> call, Throwable t) {

                Log.e("SSSFF84",t.getMessage());
            }
        });
    }

    ///////////////////////////////////cat////////////////////////////////
    public LiveData<PagedList<CategoriesModel.Datum>> mutableLiveDataCatigoriesPageList;
    MutableLiveData<CatigoriesDataSource> CategoriesModelDataSourceMutableLiveData;

    private void initCat(Context context) {
        CategoriesDataSourceFactory categoriesDataSourceFactory = new CategoriesDataSourceFactory(context);
        CategoriesModelDataSourceMutableLiveData = categoriesDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(CatigoriesDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataCatigoriesPageList = new LivePagedListBuilder<>(categoriesDataSourceFactory, config).build();

    }
    /////////////////////////////////////////////////////////////////////////////////////
    public LiveData<PagedList<Datum>> mutableLiveDataProductsPageList;
    MutableLiveData<ProductsDataSource> productsModelDataSourceMutableLiveData;

    private void initPro(Context context) {
        ProductsDataSourceFactory productsDataSourceFactory = new ProductsDataSourceFactory(context);
        productsModelDataSourceMutableLiveData = productsDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(ProductsDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataProductsPageList = new LivePagedListBuilder<>(productsDataSourceFactory, config).build();

    }

}
