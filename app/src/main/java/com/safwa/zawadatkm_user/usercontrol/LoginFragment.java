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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.safwa.zawadatkm_user.Models.LoginModel;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.databinding.LoginFragmentBinding;
import com.safwa.zawadatkm_user.home.MainActivity;
import com.safwa.zawadatkm_user.map.MapsActivity;

public class LoginFragment extends Fragment implements GoogleApiClient.OnConnectionFailedListener {

    GoogleSignInOptions gso;
    GoogleSignInClient mGoogleSignInClient;

    private static final int RC_SIGN_IN =7;
    private static final String TAG = MainActivity.class.getSimpleName();

    private LoginViewModel mViewModel;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    GlobalPrefrencies globalPrefrencies;
    GoogleSignInAccount account;
    LoginFragmentBinding loginFragmentBinding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        loginFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false);
        Utils.setLocale(getContext(),"ar");//globalPrefrencies.getLanguage());

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

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        // Build a GoogleSignInClient with the options specified by gso.


        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
        account = GoogleSignIn.getLastSignedInAccount(getContext());

        loginFragmentBinding.logiGoogle.setSize(SignInButton.SIZE_STANDARD);
        loginFragmentBinding.logiGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGoogleLogin(v);

            }
        });
        loginFragmentBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickGoogleLogin(v);
            }
        });
       return loginFragmentBinding.getRoot();
    }

    private void clickGoogleLogin(View v) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            String personName = account.getDisplayName();
            String personPhotoUrl = account.getPhotoUrl().toString();
            String email = account.getEmail();

            Log.e("WWW1",new Gson().toJson(account));
            Log.e("WWW",email+" "+ personName+" "+personPhotoUrl);
//            Glide.with(getContext()).load(personPhotoUrl)
//                    .thumbnail(0.5f)
//                    .crossFade()
//                    .diskCacheStrategy(DiskCacheStrategy.ALL)
//                    .into(imgProfilePic);
            // Signed in successfully, show authenticated UI.

            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e(TAG, "signInResult:failed code=" + e.getStatusCode()+"\n"+e.getLocalizedMessage()+"\nMEssg: "+e.getMessage());
            updateUI(null);
        }
    }
    private void updateUI(GoogleSignInAccount account) {

        if(account==null)
        {
            // Toast.makeText(this, "not signed in", Toast.LENGTH_SHORT).show();

            Toast.makeText(getContext(),"please sign in",Toast.LENGTH_SHORT).show();
        }
        else {

            Toast.makeText(getContext(),"you are signed in",Toast.LENGTH_SHORT).show();
        }

    }
//    private void signOut() {
//        mGoogleSignInClient.signOut()
//                .addOnCompleteListener(getContext(), new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        updateUI(null);
//                    }
//                });
//    }
    @Override
    public void onStart() {

        super.onStart();
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        //account = GoogleSignIn.getLastSignedInAccount(getContext());
        //updateUI(account);
    }



    private void setUpLogin() {
        mViewModel.onClickLogin(
                loginFragmentBinding.emailId.getText().toString(),
                loginFragmentBinding.passId.getText().toString().trim()
                , getContext());
        mViewModel.userLoginModelMutableLiveData.observe(this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel userLoginModel) {

                StoreUserLogin(userLoginModel);
                if(userLoginModel.getUser().getAddresses().size()==0){
                   if (globalPrefrencies.getLanguage().equals("ar")){
                       startActivity(new Intent(getActivity(), MapsActivity.class));
                       requireActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                       Toast.makeText(getContext(), "مرحبا بك " + userLoginModel.getUser().getName(), Toast.LENGTH_LONG).show();
                       getActivity().finish();
                   }
               }else {
                   if(userLoginModel.getUser().getAddress().size()>0||userLoginModel.getUser().getAddresses().size()>0) {

                       globalPrefrencies.storeAddress(userLoginModel.getUser().getAddress().get(0).getCountry() + " , " + userLoginModel.getUser().getAddress().get(0).getCity());
                       startActivity(new Intent(getActivity(), MainActivity.class));
                       requireActivity().overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                       Toast.makeText(getContext(), "مرحبا بك " + userLoginModel.getUser().getName(), Toast.LENGTH_LONG).show();
                       getActivity().finish();
                   }else {

                   }
               }


            }

        });

    }

    private void StoreUserLogin(LoginModel userLoginModel) {

        int id = userLoginModel.getUser().getId();
        String nameAr = userLoginModel.getUser().getName();
        String phone = userLoginModel.getUser().getMobile();
        String api_token = userLoginModel.getToken();
        globalPrefrencies.storeLoginStatus(true);
        globalPrefrencies.storeUserId(id);
        globalPrefrencies.storeNameAr(nameAr);
        globalPrefrencies.storePhone(phone);
        globalPrefrencies.storeLat(userLoginModel.getUser().getLatitude() + "");
        globalPrefrencies.storeLong(userLoginModel.getUser().getLongitude() + "");
        globalPrefrencies.storeApi_token(api_token);
        globalPrefrencies.storeLoginStatus(true);
        loginFragmentBinding.loginProgress.setVisibility(View.GONE);
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


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}