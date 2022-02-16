package com.safwa.zawadatkm_user.usercontrol;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.gson.Gson;
import com.safwa.zawadatkm_user.Models.LoginModel;
import com.safwa.zawadatkm_user.Models.UserRegisterModel;
import com.safwa.zawadatkm_user.OTP.OTPActivity;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.databinding.FragmentSignUpBinding;
import com.safwa.zawadatkm_user.map.MapsActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignUpFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignUpFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignUpFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignUpFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignUpFragment newInstance(String param1, String param2) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    FragmentSignUpBinding fragmentSignUpBinding;
    SignUpViewModel mViewModel;
    GlobalPrefrencies globalPrefrencies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentSignUpBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false);
        mViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        globalPrefrencies = new GlobalPrefrencies(getContext());
        Utils.setLocale(getContext(), "ar");// globalPrefrencies.getLanguage());
        // TODO: Use the ViewModel
        fragmentSignUpBinding.setSignUpViewModel(mViewModel);
        fragmentSignUpBinding.setLifecycleOwner(this);

        setupShowHidePassword();

        fragmentSignUpBinding.lognTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LoginSignupActivity) requireActivity()).showFragment(new LoginFragment());
            }
        });
        fragmentSignUpBinding.RegisterId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCheackValidation()) {
                    RegisterNow();

                    fragmentSignUpBinding.loginProgress.setVisibility(View.GONE);
                } else {
                    fragmentSignUpBinding.loginProgress.setVisibility(View.GONE);
                }
            }
        });
        return fragmentSignUpBinding.getRoot();
    }

    private void RegisterNow() {
        mViewModel.onClickRegister(fragmentSignUpBinding.nameId.getText().toString(),
                fragmentSignUpBinding.emailId.getText().toString(),
                fragmentSignUpBinding.phoneId.getText().toString(),
                fragmentSignUpBinding.passId.getText().toString(),
                fragmentSignUpBinding.repassId.getText().toString(), getContext());
        mViewModel.userRegisterModelMutableLiveData.observe(this, new Observer<UserRegisterModel>() {
            @Override
            public void onChanged(UserRegisterModel userRegisterModel) {

                if (userRegisterModel.getOtp() != null) {
                   // setUpLogin();
                    globalPrefrencies.storeApi_token(userRegisterModel.getToken());
                    globalPrefrencies.storeNameAr(userRegisterModel.getToken());
                    Intent intent = new Intent(getContext(), OTPActivity.class);
                    //intent.putExtra("code", userRegisterModel.getOtp().getToken());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getContext().startActivity(intent);
                }
            }
        });

    }

    private void setUpLogin() {
        mViewModel.onClickLogin(
                fragmentSignUpBinding.emailId.getText().toString(),
                fragmentSignUpBinding.passId.getText().toString().trim()
                , getContext());
        mViewModel.userLoginModelMutableLiveData.observe(this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel userLoginModel) {
                int id = userLoginModel.getUser().getId();
                String nameAr = userLoginModel.getUser().getName();

                String phone = userLoginModel.getUser().getMobile();
                String api_token = userLoginModel.getToken();
                if (globalPrefrencies.getLanguage().equals("ar")) {
                    startActivity(new Intent(getActivity(), MapsActivity.class));
                    requireActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    Toast.makeText(getContext(), "مرحبا بك " + nameAr, Toast.LENGTH_LONG).show();
                    getActivity().finish();
                }
                globalPrefrencies.storeLoginStatus(true);
                globalPrefrencies.storeUserId(id);
                globalPrefrencies.storeNameAr(nameAr);
                globalPrefrencies.storePhone(phone);
                globalPrefrencies.storeAddress(userLoginModel.getUser().getAddress().get(0).toString() + " ");
                globalPrefrencies.storeLat(userLoginModel.getUser().getLatitude() + "");
                globalPrefrencies.storeLong(userLoginModel.getUser().getLongitude() + "");
                globalPrefrencies.storeApi_token(api_token);
                globalPrefrencies.storeLoginStatus(true);
                fragmentSignUpBinding.loginProgress.setVisibility(View.GONE);
            }

        });


    }


    private void setupShowHidePassword() {
        fragmentSignUpBinding.showRelatev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentSignUpBinding.showPassBtn.getVisibility() == View.VISIBLE) {
                    fragmentSignUpBinding.passId.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    fragmentSignUpBinding.showPassBtn.setVisibility(View.GONE);
                    fragmentSignUpBinding.hidePassBtn.setVisibility(View.VISIBLE);
                } else {
                    fragmentSignUpBinding.passId.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    fragmentSignUpBinding.showPassBtn.setVisibility(View.VISIBLE);
                    fragmentSignUpBinding.hidePassBtn.setVisibility(View.GONE);
                }
            }
        });

        fragmentSignUpBinding.showRelatev2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fragmentSignUpBinding.showPassBtn2.getVisibility() == View.VISIBLE) {
                    fragmentSignUpBinding.repassId.setTransformationMethod(PasswordTransformationMethod.getInstance());

                    fragmentSignUpBinding.showPassBtn2.setVisibility(View.GONE);
                    fragmentSignUpBinding.hidePassBtn2.setVisibility(View.VISIBLE);
                } else {
                    fragmentSignUpBinding.repassId.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                    fragmentSignUpBinding.showPassBtn2.setVisibility(View.VISIBLE);
                    fragmentSignUpBinding.hidePassBtn2.setVisibility(View.GONE);
                }
            }
        });
    }


    public boolean onCheackValidation() {

        if (!ValidateName()) {
            return false;
        }
        if (!ValidatePhone()) {
            return false;
        }
        if (!ValidatePassword()) {
            return false;
        }


        return ValidateRePassword();
    }

    private boolean ValidatePhone() {
        if (fragmentSignUpBinding.emailId.getText().toString().trim().isEmpty()) {
            fragmentSignUpBinding.emailId.setError("من فضلك املأ هذا الحقل");
            Utils.requestFocus(fragmentSignUpBinding.emailId, getActivity().getWindow());
            return false;
        }
        return true;
    }

    private boolean ValidateName() {
        if (fragmentSignUpBinding.nameId.getText().toString().trim().isEmpty()) {
            fragmentSignUpBinding.nameId.setError("من فضلك املأ هذا الحقل");
            Utils.requestFocus(fragmentSignUpBinding.nameId, getActivity().getWindow());
            return false;
        }
        return true;
    }

    private boolean ValidatePassword() {
        if (fragmentSignUpBinding.passId.getText().toString().trim().isEmpty()) {
            fragmentSignUpBinding.passId.setError("من فضلك املأ هذا الحقل");
            Utils.requestFocus(fragmentSignUpBinding.passId, getActivity().getWindow());
            return false;
        }
        return true;
    }

    private boolean ValidateRePassword() {
        if (fragmentSignUpBinding.repassId.getText().toString().trim().isEmpty()) {
            fragmentSignUpBinding.repassId.setError("من فضلك املأ هذا الحقل");
            Utils.requestFocus(fragmentSignUpBinding.repassId, getActivity().getWindow());
            return false;
        }
        return true;
    }
}