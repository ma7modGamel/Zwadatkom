package com.safwa.zawadatkm_user.single;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.safwa.zawadatkm_user.Models.BaseModel;
import com.safwa.zawadatkm_user.Models.ProductsDetailsModel;
import com.safwa.zawadatkm_user.Network.RetroWeb;
import com.safwa.zawadatkm_user.Network.ServiceApi;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SingleProductViewModel extends ViewModel {

    GlobalPrefrencies globalPrefrencies;
    MutableLiveData<ProductsDetailsModel> ProductsMutableLiveData=new MutableLiveData<>();
    public void setUpProduct(String id_, Context context){
        globalPrefrencies=new GlobalPrefrencies(context);
        RetroWeb.getClient().create(ServiceApi.class).onGetProductDetails(id_,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<ProductsDetailsModel>() {
            @Override
            public void onResponse(Call<ProductsDetailsModel> call, Response<ProductsDetailsModel> response) {
                ProductsDetailsModel model = response.body();
                if (model != null) {
                    ProductsMutableLiveData.setValue(model);
                }
            }

            @Override
            public void onFailure(Call<ProductsDetailsModel> call, Throwable t) {
                Log.e("CC", t.getMessage());
            }
        });
    }



    MutableLiveData<BaseModel> addToCartMutableLiveData=new MutableLiveData<>();
    public void AddToCartOnLine(String id, String count,String optionId, Context context) {

        RetroWeb.getClient().create(ServiceApi.class).addNewProduct(optionId,count+"",optionId,"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {

            }
        });

    }

}
