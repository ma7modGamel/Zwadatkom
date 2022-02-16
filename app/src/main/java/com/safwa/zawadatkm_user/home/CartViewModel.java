package com.safwa.zawadatkm_user.home;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.safwa.zawadatkm_user.Models.BaseModel;
import com.safwa.zawadatkm_user.Models.CartListModel;
import com.safwa.zawadatkm_user.Models.ProductsDetailsModel;
import com.safwa.zawadatkm_user.Network.RetroWeb;
import com.safwa.zawadatkm_user.Network.ServiceApi;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartViewModel extends ViewModel {

    GlobalPrefrencies globalPrefrencies;
    MutableLiveData<CartListModel> cartMutableLiveData=new MutableLiveData<>();
    public void setUpListCart(Context context, String text){
        globalPrefrencies=new GlobalPrefrencies(context);
        RetroWeb.getClient().create(ServiceApi.class).onGetCartList(text ,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<CartListModel>() {
            @Override
            public void onResponse(Call<CartListModel> call, Response<CartListModel> response) {
                if(response.isSuccessful()){
                    cartMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<CartListModel> call, Throwable t) {

                Log.e("AMAM",t.getMessage()+" ");
                try {

                    Utils.handleException(((Activity)context).getWindow().getDecorView(),t);

                }catch (Exception e){

                    Log.e("onFailure ",t.toString());

                }
            }
        });
    }

    MutableLiveData<BaseModel> cartUpdateMutableLiveData=new MutableLiveData<>();
    public void upDateCart(String updateCartModel, Context context){
        globalPrefrencies=new GlobalPrefrencies(context);
        Log.e("GSOOOOOO",new Gson().toJson(updateCartModel));


        RetroWeb.getClient().create(ServiceApi.class).onUpdateCart(updateCartModel ,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                if(response.isSuccessful()){
                    cartUpdateMutableLiveData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {

                Log.e("AMAM",t.getMessage()+" ");
                try {

                    Utils.handleException(((Activity)context).getWindow().getDecorView(),t);

                }catch (Exception e){

                    Log.e("onFailure ",t.toString());

                }
            }
        });
    }

    MutableLiveData<BaseModel> checkPromotionMutableLiveData=new MutableLiveData<>();
    public void setUpPromotion(String txt, Context context){
        globalPrefrencies=new GlobalPrefrencies(context);


        RetroWeb.getClient().create(ServiceApi.class).onAddPromotion(txt ,globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                if(response.isSuccessful()){
                    checkPromotionMutableLiveData.setValue(response.body());

                }
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {

                Log.e("AMAM",t.getMessage()+" ");
                try {

                    Utils.handleException(((Activity)context).getWindow().getDecorView(),t);

                }catch (Exception e){

                    Log.e("onFailure ",t.toString());

                }
            }
        });
    }
}
