package com.safwa.zawadatkm_user.OnBoard;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.usercontrol.LoginSignupActivity;


public class OnBoardActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    TabLayout tabLayout;

    private ViewPager mViewPager;
    ProgressBar progressBar;
GlobalPrefrencies globalPrefrencies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_board);

        globalPrefrencies=new GlobalPrefrencies(this);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(mViewPager);
        progressBar=findViewById(R.id.progress_bar);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //progressBar.setProgress();
               animatProgresspar(tab);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
               animatProgresspar(tab);

            }
        });


    }

    private void animatProgresspar(TabLayout.Tab tab) {
        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress",tab.getPosition()*30 );
        animation.setDuration(500); // 0.5 second
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();
    }

    public void GotoNextFragment(View view) {
        if(mViewPager.getCurrentItem()!=2) {
            tabLayout.selectTab(tabLayout.getTabAt(mViewPager.getCurrentItem() + 1));
            progressBar.setProgress((mViewPager.getCurrentItem() )*30);
        }else {
            ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress",100 );
            animation.setDuration(500); // 0.5 second
            animation.setInterpolator(new DecelerateInterpolator());
            animation.start();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Do something after 5s = 5000ms
                    globalPrefrencies.setIsFirstOpeen(false);
                    Intent intent = new Intent(OnBoardActivity.this, LoginSignupActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 1000);
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new FirstOnBoardFragment();
                    break;
                case 1:
                    fragment = new SecondOnBoardFragment();
                    break;
                case 2:
                    fragment = new ThirdOnBoardFragment();
                    break;
            }
            return fragment;
        }
        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Fragment 1";
                case 1:
                    return "Fragment 2";
                case 2:
                    return "Fragment 3";
            }
            return null;
        }

    }

}