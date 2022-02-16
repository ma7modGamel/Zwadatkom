package com.safwa.zawadatkm_user.home.chat;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.safwa.zawadatkm_user.Models.ChatListModel;
import com.safwa.zawadatkm_user.Models.offer.OffersModel;
import com.safwa.zawadatkm_user.Network.RetroWeb;
import com.safwa.zawadatkm_user.Network.ServiceApi;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    MutableLiveData<ChatListModel> chatListModelMutableLiveData=new MutableLiveData<>();

    GlobalPrefrencies globalPrefrencies;
    public void getMessage(Context context) {
        globalPrefrencies=new GlobalPrefrencies(context);

        RetroWeb.getClient().create(ServiceApi.class).onGetChatList("Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<ChatListModel>() {
            @Override
            public void onResponse(Call<ChatListModel> call, Response<ChatListModel> response) {
                ChatListModel body = response.body();

                if (body != null) {
                    chatListModelMutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ChatListModel> call, Throwable t) {

                Log.e("SSSFF84",t.getMessage());
            }
        });
    }

}