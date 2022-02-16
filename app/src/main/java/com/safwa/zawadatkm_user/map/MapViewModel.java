package com.safwa.zawadatkm_user.map;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.safwa.zawadatkm_user.Models.CreateNewAddressModel;
import com.safwa.zawadatkm_user.Network.RetroWeb;
import com.safwa.zawadatkm_user.Network.ServiceApi;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapViewModel extends ViewModel {


    GlobalPrefrencies globalPrefrencies;
    MutableLiveData<CreateNewAddressModel> userAddAddressModelMutableLiveData = new MutableLiveData<>();

    public void onClickAddNewAddress(String lat, String lng, String contry, String city, String address, String typeAddress, final Context context) {

        globalPrefrencies = new GlobalPrefrencies(context);
        Log.e("BOodYff", city+"-XXX "+ address+" XXX-"+ null+"-"+ "Home");
        RetroWeb.getClient().create(ServiceApi.class)
                .onClickAddNewAddress
                (lat, lng, contry, city, address, null+"", "Home","Bearer " +globalPrefrencies.getApi_token())
                .enqueue(new Callback<CreateNewAddressModel>() {
                    @Override
                    public void onResponse(Call<CreateNewAddressModel> call, Response<CreateNewAddressModel> response) {
                       // Log.e("BOodY", new Gson().toJson(response.body()));
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            if (response.body().getSuccess()) {
                                userAddAddressModelMutableLiveData.setValue(response.body());
                            } else {
                                Toast.makeText(context, " ", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.e("XX",response.body()+"0");

                            Toast.makeText(context, "Some data is valid .. ", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CreateNewAddressModel> call, Throwable t) {

                        Log.e("AAQQ",t.getMessage());
                        Utils.handleException(((Activity) context).getWindow().getDecorView(), t);
                    }
                });
    }
}
