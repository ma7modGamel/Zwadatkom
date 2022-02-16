package com.safwa.zawadatkm_user.usercontrol;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.safwa.zawadatkm_user.Models.LoginModel;
import com.safwa.zawadatkm_user.Models.UserRegisterModel;
import com.safwa.zawadatkm_user.Network.RetroWeb;
import com.safwa.zawadatkm_user.Network.ServiceApi;
import com.safwa.zawadatkm_user.OTP.OTPActivity;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.home.MainActivity;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpViewModel extends ViewModel {

    MutableLiveData<UserRegisterModel> userRegisterModelMutableLiveData=new MutableLiveData<>();

    GlobalPrefrencies globalPrefrencies;



    public void onClickRegister(String name,String email,  String Phone,  String password, String confirmPassword, final Context context) {
        globalPrefrencies=new GlobalPrefrencies(context);

        RetroWeb.getClient().create(ServiceApi.class).onRegester(name, email,Phone, password, confirmPassword).enqueue(new Callback<UserRegisterModel>() {
            @Override
            public void onResponse(Call<UserRegisterModel> call, Response<UserRegisterModel> response) {
                if (response.isSuccessful()) {
                    //if (response.body().getStatus().equals("true")) {

                        userRegisterModelMutableLiveData.setValue(response.body());

                   // }else {
                        Toast.makeText(context, ""+response.body().getOtp().getToken(), Toast.LENGTH_SHORT).show();
                   // }
                }else {
                    //HANDEL ERROR HERE
                    if((response.body() != null ? response.body().getErrors().getName() : null) !=null) {
                        Toast.makeText(context, "" + response.body().getErrors().getName().get(0) + " ", Toast.LENGTH_SHORT).show();
                    }else if(Objects.requireNonNull(response.body()).getErrors().getEmail()!=null) {
                        Toast.makeText(context, "" + response.body().getErrors().getEmail().get(0) + " ", Toast.LENGTH_SHORT).show();
                    }else if (response.body().getErrors().getPassword()!=null){
                        Toast.makeText(context, "" + response.body().getErrors().getPassword().get(0) + " ", Toast.LENGTH_SHORT).show();
                    }else  if (response.body().getErrors().getMobile()!=null){
                        Toast.makeText(context, "" + response.body().getErrors().getMobile().get(0) + " ", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "0", Toast.LENGTH_SHORT).show();
                    }

                    //Log.e("err",new Gson().toJson(response.body().getErrors()));
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
        RetroWeb.getClient().create(ServiceApi.class).onLogin(mobile, password,globalPrefrencies.getLanguage()).enqueue(new Callback<LoginModel>() {
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
