package com.safwa.zawadatkm_user.home.chat;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.safwa.zawadatkm_user.R;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        showFragment(new ChatFragment());

    }


    public void showFragment(Fragment fragment) {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();

        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.item_anim_fall_down, R.anim.fadeout, R.anim.item_anim_fall_down, R.anim.fragment_slide_right_exit);
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();

    }
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        }else {
            finish();
        }
    }
}
