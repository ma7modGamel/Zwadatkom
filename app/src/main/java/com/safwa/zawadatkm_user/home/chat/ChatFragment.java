package com.safwa.zawadatkm_user.home.chat;


import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.google.android.gms.nearby.messages.Message;
import com.google.gson.Gson;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.PrivateChannel;
import com.pusher.client.channel.PrivateChannelEventListener;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.client.util.HttpAuthorizer;
import com.safwa.zawadatkm_user.Adabter.AddressesAdapter;
import com.safwa.zawadatkm_user.Adabter.ChatAdapter;
import com.safwa.zawadatkm_user.Adabter.ProductsAdapter;
import com.safwa.zawadatkm_user.Models.BaseModel;
import com.safwa.zawadatkm_user.Models.ChatListModel;
import com.safwa.zawadatkm_user.Models.Datum;
import com.safwa.zawadatkm_user.Network.RetroWeb;
import com.safwa.zawadatkm_user.Network.ServiceApi;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.databinding.ChatFragmentBinding;
import com.safwa.zawadatkm_user.home.CartViewModel;
import com.safwa.zawadatkm_user.home.MainActivity;

import net.mrbin99.laravelechoandroid.Echo;
import net.mrbin99.laravelechoandroid.EchoCallback;
import net.mrbin99.laravelechoandroid.EchoOptions;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends Fragment {

    private ChatViewModel mViewModel;
    ChatFragmentBinding binding;

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.chat_fragment, container, false);

        return binding.getRoot();
    }

    GlobalPrefrencies globalPrefrencies;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        globalPrefrencies = new GlobalPrefrencies(getContext());
        Utils.setLocale(getContext(), "ar");//globalPrefrencies.getLanguage());
        binding.setChatVmodel(mViewModel);
        binding.setLifecycleOwner(this);

      //  setupPusher();

        setUp2();
        // TODO: Use the ViewModel
        
        getListMessage();


        binding.SendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendNewMessage(binding.etMessage.getText().toString().trim());
            }
        });
        binding.imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.rlShowText.setVisibility(View.INVISIBLE);
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() > 1) {
                    getFragmentManager().popBackStack();

                }else {
                    getActivity().finish();
                }
            }
        });
        
    }

    private void setUp2() {

        EchoOptions options = new EchoOptions();

// Setup host of your Laravel Echo Server
        options.host = "https://test.dnbscy.com/api/broadcasting/auth";//http://{my-host}:{my-port}";

        /*
         * Add headers for authorizing your users (private and presence channels).
         * This line can change matching how you have configured
         * your guards on your Laravel application
         */
        options.headers.put("Authorization", "Bearer "+globalPrefrencies.getApi_token());

// Create the client
        Echo echo = new Echo(options);
        echo.connect(new EchoCallback() {
            @Override
            public void call(Object... args) {
                // Success connect
                Log.e("ccc","11");

            }
        }, new EchoCallback() {
            @Override
            public void call(Object... args) {
                // Error connect
                Log.e("ddd","2");
            }
        });
    }

    private void setupPusher() {



        HttpAuthorizer authorizer = new HttpAuthorizer("https://test.dnbscy.com/api/broadcasting/auth");
        PusherOptions options = new PusherOptions().setCluster("eu").setAuthorizer(authorizer);
        Pusher pusher = new Pusher("43b8579d4dba7578e3ee", options);

        pusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {
                Log.e("Pusher", "State changed from " + change.getPreviousState() +
                        " to " + change.getCurrentState());
            }

            @Override
            public void onError(String message, String code, Exception e) {
                Log.e("Pusher", "There was a problem connecting! " +
                        "\ncode: " + code +
                        "\nmessage: " + message +
                        "\nException: " + e
                );
            }
        }, ConnectionState.ALL);

        PrivateChannel channel = pusher.subscribePrivate("private-chat");


//        channel.bind("newMessage", new SubscriptionEventListener() {
//            @Override
//            public void onEvent(PusherEvent event) {
//                Log.e("Pusher", "Received event with data: " + event.toString());
//            }
//        });
    }


    private void SendNewMessage(String msg) {
        RetroWeb.getClient().create(ServiceApi.class).onSendMessage(msg,"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                if(response.isSuccessful()){

                    String messageText = binding.etMessage.getText().toString();
                    if (TextUtils.isEmpty(messageText)) {
                        return;
                    }



                    binding.etMessage.setText("");

                  //  displayMessage(chatMessage);
                }
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {

            }
        });


    }
    public void displayMessage(ChatListModel.Message message) {
        adapter.add(message);
        adapter.notifyDataSetChanged();
     //   scroll();
    }

    ArrayList<ChatListModel.Message> messages ;
    ChatAdapter adapter;
    private void getListMessage() {


        messages=new ArrayList<>();
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridLayoutManager.setStackFromEnd(false);
        gridLayoutManager.setSmoothScrollbarEnabled(false);
        gridLayoutManager.setReverseLayout(false);
        binding.chatrv.setLayoutManager(gridLayoutManager);
        binding.chatrv.setHasFixedSize(false);
        mViewModel.getMessage(getContext());
        mViewModel.chatListModelMutableLiveData.observe(getViewLifecycleOwner(), new Observer<ChatListModel>() {
            @Override
            public void onChanged(ChatListModel chatListModel) {
                if(chatListModel.getSuccess()) {
                    messages.addAll(chatListModel.getMessages().getMessages());
                    adapter = new ChatAdapter(getContext(), messages);
                    binding.chatrv.setAdapter(adapter);
                }
            }
        });


    }

}