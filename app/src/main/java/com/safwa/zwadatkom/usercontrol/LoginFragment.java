package com.safwa.zwadatkom.usercontrol;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.safwa.zwadatkom.Models.LoginModel;
import com.safwa.zwadatkom.R;
import com.safwa.zwadatkom.Utils.GlobalPrefrencies;
import com.safwa.zwadatkom.Utils.Utils;
import com.safwa.zwadatkom.databinding.LoginFragmentBinding;
import com.safwa.zwadatkom.map.MapsActivity;

public class LoginFragment extends Fragment {

    private LoginViewModel mViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    GlobalPrefrencies globalPrefrencies;
    LoginFragmentBinding loginFragmentBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        loginFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false);


       loginFragmentBinding.singuptv.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               ((LoginSignupActivity) requireActivity()).showFragment(new SignUpFragment());
           }
       });
       loginFragmentBinding.loginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               //getActivity().finish();
               if (onCheackValidation()) {
                   setUpLogin();
                   loginFragmentBinding.loginProgress.setVisibility(View.GONE);
               } else {
                   loginFragmentBinding.loginProgress.setVisibility(View.GONE);
               }
           }
       });

       return loginFragmentBinding.getRoot();
    }

    private void setUpLogin() {
        mViewModel.onClickLogin(
                loginFragmentBinding.emailId.getText().toString(),
                loginFragmentBinding.passId.getText().toString().trim()
                , getContext());
        mViewModel.userLoginModelMutableLiveData.observe(this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel userLoginModel) {
                int id = userLoginModel.getUser().getId();
                String nameAr = userLoginModel.getUser().getName();

                String phone = userLoginModel.getUser().getMobile();
                String api_token = userLoginModel.getToken();
                if (globalPrefrencies.getLanguage().equals("ar")){
                    startActivity(new Intent(getActivity(), MapsActivity.class));
                    requireActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    Toast.makeText(getContext(), "مرحبا بك " + nameAr, Toast.LENGTH_LONG).show();
                    getActivity().finish();
                }
                globalPrefrencies.storeLoginStatus(true);
                globalPrefrencies.storeUserId(id);

                globalPrefrencies.storeNameAr(nameAr);
                globalPrefrencies.storePhone(phone);
//                globalPrefrencies.storeAddress(userLoginModel.getUser().getAddress());
//                globalPrefrencies.storeLat(userLoginModel.getUser().getLat() + "");
//                globalPrefrencies.storeLong(userLoginModel.getUser().getLang() + "");
                globalPrefrencies.storeApi_token(api_token);
                globalPrefrencies.storeLoginStatus(true);

                loginFragmentBinding.loginProgress.setVisibility(View.GONE);
            }

        });

    }

    public boolean onCheackValidation() {

        if (!ValidatePassword()) {
            return false;
        }

        return ValidatePhone();
    }


    private boolean ValidatePhone() {
        if (loginFragmentBinding.emailId.getText().toString().trim().isEmpty()) {
            loginFragmentBinding.emailId.setError("من فضلك املأ هذا الحقل");
            Utils.requestFocus(loginFragmentBinding.emailId, getActivity().getWindow());
            return false;
        }
        return true;
    }

    private boolean ValidatePassword() {
        if (loginFragmentBinding.passId.getText().toString().trim().isEmpty()) {
            loginFragmentBinding.passId.setError("من فضلك املأ هذا الحقل");
            Utils.requestFocus(loginFragmentBinding.passId, getActivity().getWindow());
            return false;
        }
        return true;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        globalPrefrencies = new GlobalPrefrencies(getContext());
        Utils.setLocale(getContext(), globalPrefrencies.getLanguage());
        // TODO: Use the ViewModel
        loginFragmentBinding.setLoginViewModel(mViewModel);
        loginFragmentBinding.setLifecycleOwner(this);

        loginFragmentBinding.showRelatev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loginFragmentBinding.showPassBtn.getVisibility()==View.VISIBLE) {
                    loginFragmentBinding.passId.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    loginFragmentBinding.showPassBtn.setVisibility(View.GONE);
                    loginFragmentBinding.hidePassBtn.setVisibility(View.VISIBLE);
                } else {
                    loginFragmentBinding.passId.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    loginFragmentBinding.showPassBtn.setVisibility(View.VISIBLE);
                    loginFragmentBinding.hidePassBtn.setVisibility(View.GONE);
                }
            }
        });
    }


}