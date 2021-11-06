package com.safwa.zwadatkom.usercontrol;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.safwa.zwadatkom.Home.MainActivity;
import com.safwa.zwadatkom.Models.LoginModel;
import com.safwa.zwadatkom.Models.UserRegisterModel;
import com.safwa.zwadatkom.Network.RetroWeb;
import com.safwa.zwadatkom.Network.ServiceApi;
import com.safwa.zwadatkom.OTP.OTPActivity;
import com.safwa.zwadatkom.Utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {

    MutableLiveData<UserRegisterModel> userRegisterModelMutableLiveData=new MutableLiveData<>();

    GlobalPrefrencies globalPrefrencies;



    public void onClickRegister(String name,  String Phone,  String password, String confirmPassword, final Context context) {
        globalPrefrencies=new GlobalPrefrencies(context);

        RetroWeb.getClient().create(ServiceApi.class).onRegester(name, Phone, password, confirmPassword).enqueue(new Callback<UserRegisterModel>() {
            @Override
            public void onResponse(Call<UserRegisterModel> call, Response<UserRegisterModel> response) {
                if (response.isSuccessful()) {
                    //if (response.body().getStatus().equals("true")) {

                        userRegisterModelMutableLiveData.setValue(response.body());
                        Intent intent = new Intent(context, OTPActivity.class);
                        intent.putExtra("code", response.body().getOtp().getToken());
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                   // }else {
                        Toast.makeText(context, ""+response.body().getOtp().getToken(), Toast.LENGTH_SHORT).show();
                   // }
                }else {
                    //HANDEL ERROR HERE
                   Toast.makeText(context, ""+response.isSuccessful()+" ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserRegisterModel> call, Throwable t) {

                Log.e("Error Register ," ,t.getMessage());
            }
        });

    }


    MutableLiveData<LoginModel> userLoginModelMutableLiveData = new MutableLiveData<>();
    public void onClickLogin(String mobile, final String password, final Context context) {

        globalPrefrencies=new GlobalPrefrencies(context);
        RetroWeb.getClient().create(ServiceApi.class).onLogin(mobile, password).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                Log.e("BOodY",new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if(response.body().getSuccess()){
                        userLoginModelMutableLiveData.setValue(response.body());
                        Intent intent = new Intent(context, MainActivity.class);
                        globalPrefrencies.storePassword(password);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                    }else {
                        Toast.makeText(context, "wrong password or email", Toast.LENGTH_SHORT).show();
                    }
                }else {
//                    userLoginModelMutableLiveData.setValue(response.body());
                    Toast.makeText(context, "wrong password or email", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(context, "wrong password or email", Toast.LENGTH_SHORT).show();
                Log.e("Error Login ," ,t.getMessage());
            }
        });
    }

}
