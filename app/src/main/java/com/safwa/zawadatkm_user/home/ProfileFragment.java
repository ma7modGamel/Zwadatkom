package com.safwa.zawadatkm_user.home;

import static com.safwa.zawadatkm_user.Models.orders.OrdersDataSource.CoutItem;
import static com.safwa.zawadatkm_user.home.MainActivity.layoutTpFront;
import static com.safwa.zawadatkm_user.home.MainActivity.layoutTpback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.safwa.zawadatkm_user.Adabter.OrdersAdapter;
import com.safwa.zawadatkm_user.Models.ProfileUserModel;
import com.safwa.zawadatkm_user.Models.orders.OrdersListModel;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.databinding.ProfileFragmentBinding;
import com.safwa.zawadatkm_user.myorders.OrdersActivity;
import com.safwa.zawadatkm_user.profile.ProfileActivity;
import com.safwa.zawadatkm_user.usercontrol.LoginSignupActivity;

public class ProfileFragment extends Fragment {

    private ProfileViewModel mViewModel;

    public static ProfileFragment newInstance() {
        return new ProfileFragment();
    }
    GoogleSignInClient mGoogleSignInClient;
    ProfileFragmentBinding binding;
    GoogleSignInOptions gso;

    GlobalPrefrencies globalPrefrencies;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.profile_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        binding.setProfileVmodel(mViewModel);
        binding.setLifecycleOwner(this);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
        getUserInfo();
        //getOrders();

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                globalPrefrencies=new GlobalPrefrencies(getContext());
                globalPrefrencies.storeLoginStatus(false);
                globalPrefrencies.storeApi_token("");

                        mGoogleSignInClient.signOut()
                .addOnCompleteListener((Activity) getContext(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(getContext(), LoginSignupActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        getActivity().finish();
                    }
                });

                Intent intent = new Intent(getContext(), LoginSignupActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });
        binding.layMyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() > 1) {
                    getFragmentManager().popBackStack();
                    MainActivity.bottomNavigation.show(0, true);
                    layoutTpback.setVisibility(View.VISIBLE);
                    layoutTpFront.setVisibility(View.VISIBLE);
                }
            }
        });
        binding.layProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("info", profileUserModelNew);
                startActivity(intent);
            }
        });
        binding.layMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getActivity(), OrdersActivity.class);

                startActivity(intent);
            }
        });
        return binding.getRoot();
    }

    OrdersAdapter adapter;

    private void getOrders() {
        adapter = new OrdersAdapter(getContext());
        mViewModel.getOrders(getContext());
        mViewModel.mutableLiveDataOrdersPageList.observe(getViewLifecycleOwner(), new Observer<PagedList<OrdersListModel.Datum>>() {
            @Override
            public void onChanged(PagedList<OrdersListModel.Datum> data) {
                if (data != null) {
                    adapter.submitList(data);
                    setupCount();
                }
            }

            private void setupCount() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        binding.txtNumOrder.setText(CoutItem + " ");
                        Log.e("aa", CoutItem + "");
                    }
                }, 500);
            }
        });
    }

    ProfileUserModel profileUserModelNew;

    private void getUserInfo() {

        mViewModel.getUserInfo(getContext());
        mViewModel.profileUserModelMutableLiveData.observe(getViewLifecycleOwner(), new Observer<ProfileUserModel>() {
            @Override
            public void onChanged(ProfileUserModel profileUserModel) {

                profileUserModelNew = profileUserModel;

                setupProfile(profileUserModel);

            }
        });


    }

    private void setupProfile(ProfileUserModel profileUserModel) {
        binding.txtNameUser.setText(profileUserModel.getUser().getName() + "");
        binding.txtPhone.setText(profileUserModel.getUser().getMobile() + "");
        binding.txtValueWallet.setText(profileUserModel.getUser().getWallet() + " ر.س ");
        binding.txtNumOrder.setText(profileUserModel.getUser().getOrderCount()+"");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

}