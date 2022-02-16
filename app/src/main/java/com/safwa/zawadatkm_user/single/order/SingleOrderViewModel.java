package com.safwa.zawadatkm_user.single.order;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.safwa.zawadatkm_user.Models.detailsorders.OrderDetailsModel;
import com.safwa.zawadatkm_user.Network.RetroWeb;
import com.safwa.zawadatkm_user.Network.ServiceApi;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleOrderViewModel  extends ViewModel {

    GlobalPrefrencies globalPrefrencies;
    MutableLiveData<OrderDetailsModel> ProductsMutableLiveData=new MutableLiveData<>();
    public void setUpOrders(String id_, Context context){
        globalPrefrencies=new GlobalPrefrencies(context);
        RetroWeb.getClient().create(ServiceApi.class).onGetOrderDetails(id_,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<OrderDetailsModel>() {
            @Override
            public void onResponse(Call<OrderDetailsModel> call, Response<OrderDetailsModel> response) {
                OrderDetailsModel model = response.body();
                if (model != null) {
                    ProductsMutableLiveData.setValue(model);
                }
            }

            @Override
            public void onFailure(Call<OrderDetailsModel> call, Throwable t) {
                Log.e("CC", t.getMessage());
            }
        });
    }
}
