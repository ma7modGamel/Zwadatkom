package com.safwa.zawadatkm_user.usercontrol;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.safwa.zawadatkm_user.Models.LoginModel;
import com.safwa.zawadatkm_user.Models.ProfileUserModel;
import com.safwa.zawadatkm_user.Network.RetroWeb;
import com.safwa.zawadatkm_user.Network.ServiceApi;
import com.safwa.zawadatkm_user.OTP.OTPActivity;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.home.MainActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    MutableLiveData<LoginModel> userLoginModelMutableLiveData = new MutableLiveData<>();

    GlobalPrefrencies globalPrefrencies;

    public void onClickLogin(String mobile, final String password, final Context context) {

        globalPrefrencies = new GlobalPrefrencies(context);
        RetroWeb.getClient().create(ServiceApi.class).onLogin(mobile, password,globalPrefrencies.getLanguage()).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                Log.e("BOodY", new Gson().toJson(response.body()));
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().getSuccess()) {
                        userLoginModelMutableLiveData.setValue(response.body());
                        getUserInfo(response.body().getToken());
                        Intent intent = new Intent(context, MainActivity.class);
                        globalPrefrencies.storePassword(password);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        if (response.body().getMessage().equals("Verify Your number first")) {
                            Intent intent = new Intent(context, OTPActivity.class);
                            globalPrefrencies.storePassword(password);
                            globalPrefrencies.storeApi_token(response.body().getToken());
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            context.startActivity(intent);
                        }
                    }
                } else {
//                    userLoginModelMutableLiveData.setValue(response.body());
                    Toast.makeText(context, "wrong password or email", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                try {

                    Utils.handleException(((Activity) context).getWindow().getDecorView(), t);

                } catch (Exception e) {

                    Log.e("onFailure ", t.toString());

                }
            }
        });
    }

    MutableLiveData<ProfileUserModel> profileUserModelMutableLiveData = new MutableLiveData<>();

    public void getUserInfo(String token) {
        RetroWeb.getClient().create(ServiceApi.class).onGetProfileInfo(globalPrefrencies.getLanguage(), "Bearer " + token).enqueue(new Callback<ProfileUserModel>() {
            @Override
            public void onResponse(Call<ProfileUserModel> call, Response<ProfileUserModel> response) {
                Log.e("8888", new Gson().toJson(response.body()));
                if (response.body() != null) {
                    profileUserModelMutableLiveData.setValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<ProfileUserModel> call, Throwable t) {
                Log.e("AAA", t.getMessage());
            }
        });
    }
}