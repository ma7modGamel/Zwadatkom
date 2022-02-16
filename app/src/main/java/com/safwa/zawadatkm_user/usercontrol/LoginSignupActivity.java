package com.safwa.zawadatkm_user.usercontrol;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.databinding.ActivityLoginSignupBinding;

public class LoginSignupActivity extends AppCompatActivity {

    GlobalPrefrencies globalPrefrencies;
    ActivityLoginSignupBinding activityUserBinding;
    LoginSignupViewModel loginSignupViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalPrefrencies = new GlobalPrefrencies(this);
        activityUserBinding= DataBindingUtil.setContentView(this,R.layout.activity_login_signup);
        loginSignupViewModel= ViewModelProviders.of(this).get(LoginSignupViewModel.class);
        activityUserBinding.setLifecycleOwner(this);
        Utils.setLocale(this,"ar");//globalPrefrencies.getLanguage());

        showFragment(new LoginFragment());


    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        return super.dispatchTouchEvent(ev);
    }
    public void showFragment(Fragment fragment) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.item_anim_fall_down, R.anim.fadeout, R.anim.item_anim_fall_down, R.anim.anim_fall_down);
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }
    AlertDialog.Builder builder;
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {

            builder = new AlertDialog.Builder(this);


            //Setting message manually and performing action on button click
            builder.setMessage("هل تريد الخروج من التطبيق ؟")
                    .setCancelable(false)
                    .setPositiveButton("نعم", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();

                        }
                    })
                    .setNegativeButton("لا", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //  Action for 'NO' Button
                            dialog.cancel();

                        }
                    });
            //Creating dialog box
            AlertDialog alert = builder.create();
            //Setting the title manually
            alert.setTitle("مغادرة التطبيق");
            alert.show();


        }
    }


}