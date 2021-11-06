package com.safwa.zwadatkom.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.safwa.zwadatkom.Adabter.MenuAdapter;
import com.safwa.zwadatkom.R;
import com.safwa.zwadatkom.Utils.GlobalPrefrencies;
import com.safwa.zwadatkom.Utils.Utils;

import java.util.ArrayList;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;


public class MainActivity extends AppCompatActivity implements DuoMenuView.OnMenuClickListener {

    GlobalPrefrencies globalPrefrencies;
    private MenuAdapter mMenuAdapter;
    private ViewHolder mViewHolder;
    Toolbar toolbar;
    LinearLayout layoutMyProfile;
    private ArrayList<String> mTitles = new ArrayList<>();
    Intent intent;
    ImageView imageViewShowMenu,imageViewShowMenu2;
    TextView callUs, main_fg;
    DuoDrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        globalPrefrencies = new GlobalPrefrencies(getApplicationContext());
        Utils.setLocale(this, "ar");

        imageViewShowMenu = findViewById(R.id.imgShowMenu);

        imageViewShowMenu2 = findViewById(R.id.imgShowMenu2);

        drawerLayout = findViewById(R.id.drawer);
        MeowBottomNavigation bottomNavigation = findViewById(R.id.btm);
        MeowBottomNavigation.Model model = new MeowBottomNavigation.Model(0, R.drawable.homemenu);
        bottomNavigation.add(model);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.shoppingcart));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.setting));
        bottomNavigation.show(1, true);
        showFragment(new HomeFragment());

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {

                int x = model.getId();
                switch (x) {
                    case 0:
                        showFragment(new HomeFragment());
                        break;
                    case 1:
                        showFragment(new CartFragment());
                        break;
                    case 2:
                        showFragment(new ProfileFragment());
                        break;
                }

                return null;
            }
        });
        initialWeget();

        intent = new Intent(MainActivity.this, DrawerActivity.class);

        // Initialize the views
        mViewHolder = new ViewHolder();
        // Handle menu actions
        handleMenu();
        // Handle drawer actions
        handleDrawer();
        // Show main fragment in container
        mMenuAdapter.setViewSelected(0, true);


        layoutMyProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewHolder.mDuoDrawerLayout.closeDrawer();
                Fragment fragment = new ProfileFragment();
                showFragment(fragment);
            }
        });

        callUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtra("fragment", 6);
                startActivity(intent);
                mViewHolder.mDuoDrawerLayout.closeDrawer();

            }
        });
        main_fg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewHolder.mDuoDrawerLayout.closeDrawer();
                Fragment fragment = new HomeFragment();
                showFragment(fragment);
            }
        });
        imageViewShowMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("isDrawerOpen",drawerLayout.isDrawerOpen()+"");
                if(drawerLayout.isDrawerOpen()){
                    drawerLayout.closeDrawer();
                }else {
                    drawerLayout.openDrawer();
                }
            }
        });
        imageViewShowMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("isDrawerOpen",drawerLayout.isDrawerOpen()+"");
                if(drawerLayout.isDrawerOpen()){
                    drawerLayout.closeDrawer();
                }else {
                    drawerLayout.openDrawer();
                }
            }
        });

    }

    private void initialWeget() {
        layoutMyProfile = findViewById(R.id.id_layout_Myprofile);
        callUs = findViewById(R.id.callUs_id);
        main_fg = findViewById(R.id.main_tv);
    }


    @SuppressLint({"WrongConstant", "RtlHardcoded"})
    private void handleDrawer() {

        DuoDrawerToggle drawerToggle = new DuoDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        if (item != null && item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(Gravity.START)) {
                drawerLayout.closeDrawer(Gravity.START);
            } else {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        }
        return false;


    }

    private void handleMenu() {
        mMenuAdapter = new MenuAdapter(mTitles);
        mViewHolder.mDuoMenuView.setOnMenuClickListener(this);
        mViewHolder.mDuoMenuView.setAdapter(mMenuAdapter);
    }


    @Override
    public void onFooterClicked() {
        Toast.makeText(this, "onFooterClicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onHeaderClicked() {
        Toast.makeText(this, "onHeaderClicked", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onOptionClicked(int position, Object objectClicked) {
        // Set the toolbar title
        setTitle(mTitles.get(position));

        // Set the right options selected
        mMenuAdapter.setViewSelected(position, true);
        // Navigate to the right fragment
        switch (position) {
            default:
                showFragment(new HomeFragment());
                break;
        }
        // Close the drawer
        mViewHolder.mDuoDrawerLayout.closeDrawer();
    }

    private class ViewHolder {
        private DuoDrawerLayout mDuoDrawerLayout;
        private DuoMenuView mDuoMenuView;

        ViewHolder() {
            mDuoDrawerLayout = (DuoDrawerLayout) findViewById(R.id.drawer);
            mDuoMenuView = (DuoMenuView) mDuoDrawerLayout.getMenuView();

        }
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