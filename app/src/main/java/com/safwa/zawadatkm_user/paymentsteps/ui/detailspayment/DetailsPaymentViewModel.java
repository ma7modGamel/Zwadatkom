package com.safwa.zawadatkm_user.paymentsteps.ui.detailspayment;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.google.gson.Gson;
import com.safwa.zawadatkm_user.Models.MakeOrderModel;
import com.safwa.zawadatkm_user.Models.address.AddressDataSource;
import com.safwa.zawadatkm_user.Models.address.AddressDataSourceFactory;
import com.safwa.zawadatkm_user.Models.address.AddressListModel;
import com.safwa.zawadatkm_user.Network.RetroWeb;
import com.safwa.zawadatkm_user.Network.ServiceApi;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsPaymentViewModel extends ViewModel {

    // TODO: Implement the ViewModel
    public LiveData<PagedList<AddressListModel.Datum>> mutableLiveDataOrdersPageList;
    public MutableLiveData<AddressDataSource> AddressModelDataSourceMutableLiveData;

    public void setUpAdresses(Context context) {
        AddressDataSourceFactory AddressModelDataSourceFactory = new AddressDataSourceFactory(context);
        AddressModelDataSourceMutableLiveData = AddressModelDataSourceFactory.userLiveDataSource;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(AddressDataSource.PAGE_SIZE)
                .build();
        mutableLiveDataOrdersPageList = new LivePagedListBuilder<>(AddressModelDataSourceFactory, config).build();
    }
    GlobalPrefrencies globalPrefrencies;
    public  MutableLiveData<MakeOrderModel> makeOrderModelMutableLiveData=new MutableLiveData<>();

    public void makeNewOrder(String addressId,String statusId,String typeMethod,Context context){
        globalPrefrencies=new GlobalPrefrencies(context);
        RetroWeb.getClient().create(ServiceApi.class).onMakeOrder(addressId,statusId,typeMethod,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<MakeOrderModel>() {
            @Override
            public void onResponse(Call<MakeOrderModel> call, Response<MakeOrderModel> response) {
                if(response.isSuccessful()){
                    makeOrderModelMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MakeOrderModel> call, Throwable t) {

                Log.e("SS",t.getMessage());
            }
        });
    }
}