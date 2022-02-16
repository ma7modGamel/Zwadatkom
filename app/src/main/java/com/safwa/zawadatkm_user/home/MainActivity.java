package com.safwa.zawadatkm_user.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.Task;
import com.google.gson.Gson;
import com.safwa.zawadatkm_user.Adabter.CartOnlineAdapter;
import com.safwa.zawadatkm_user.Adabter.MenuAdapter;
import com.safwa.zawadatkm_user.Models.CartListModel;
import com.safwa.zawadatkm_user.Network.RetroWeb;
import com.safwa.zawadatkm_user.Network.ServiceApi;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.home.chat.ChatActivity;
import com.safwa.zawadatkm_user.home.chat.ChatFragment;
import com.safwa.zawadatkm_user.map.MapsActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import nl.psdcompany.duonavigationdrawer.views.DuoDrawerLayout;
import nl.psdcompany.duonavigationdrawer.views.DuoMenuView;
import nl.psdcompany.duonavigationdrawer.widgets.DuoDrawerToggle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity implements DuoMenuView.OnMenuClickListener, CartOnlineAdapter.onChangeQuantityListener {


    GlobalPrefrencies globalPrefrencies;
    private MenuAdapter mMenuAdapter;
    private ViewHolder mViewHolder;
    Toolbar toolbar;
    LinearLayout layoutMyProfile;
    private ArrayList<String> mTitles = new ArrayList<>();
    Intent intent;
    TextView textViewAdreess, txtRate;
    ImageView imageViewShowMenu, imageViewShowMenu2,bag;
    TextView callUs, main_fg;
    static DuoDrawerLayout drawerLayout;
    static MeowBottomNavigation bottomNavigation;
    MeowBottomNavigation.Model model;
    LinearLayout layoutAddress;
    static LinearLayout layoutTpback, layoutTpFront;
    RelativeLayout mainLayout;
    CartOnlineAdapter.onChangeQuantityListener onChangeQuantityListener;
    public static CircleImageView circleImageViewBandge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        globalPrefrencies = new GlobalPrefrencies(getApplicationContext());
        Utils.setLocale(this, "ar");

        imageViewShowMenu = findViewById(R.id.imgShowMenu);
        imageViewShowMenu2 = findViewById(R.id.imgShowMenu2);
        drawerLayout = findViewById(R.id.drawer);

        bag=findViewById(R.id.bag);
        txtRate = findViewById(R.id.rateApp);
        textViewAdreess = findViewById(R.id.txtAddress);
        layoutTpback = findViewById(R.id.topLayBack);
        layoutTpFront = findViewById(R.id.topLayFront);
        layoutAddress = findViewById(R.id.layoutAddress);
        circleImageViewBandge = findViewById(R.id.bndgRed);

        bottomNavigation = findViewById(R.id.btm);
        model = new MeowBottomNavigation.Model(0, R.drawable.homemenu);
        bottomNavigation.add(model);
        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.shoppingcart));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.setting));
        bottomNavigation.show(0, true);
        showFragment(new HomeFragment());

        initRate();
        setUpListCart();

        bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(new CartFragment());
                bottomNavigation.show(1, true);
                drawerLayout.closeDrawer();
            }
        });
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
                        layoutTpback.setVisibility(View.GONE);
                        layoutTpFront.setVisibility(View.GONE);
                        break;
                }

                return null;
            }
        });
        initialWeget();

        intent = new Intent(MainActivity.this, DrawerActivity.class);

        nameTv.setText(globalPrefrencies.getNameAR());
        textViewAdreess.setText(globalPrefrencies.getAddress() + "");
        // Initialize the views
        mViewHolder = new ViewHolder();
        // Handle menu actions
        handleMenu();
        // Handle drawer actions
        handleDrawer();
        // Show main fragment in container
        mMenuAdapter.setViewSelected(0, true);

        Intent intent = getIntent();
        if(intent.getExtras()!=null&&intent.getExtras().getString("opencart").equals("opencart")){
            showFragment(new CartFragment());
            bottomNavigation.show(1,true);
        }
        layoutAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

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
              Intent intent1=new Intent(MainActivity.this, ChatActivity.class);
              startActivity(intent1);
                drawerLayout.closeDrawer();

            }
        });
        main_fg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewHolder.mDuoDrawerLayout.closeDrawer();
//                Fragment fragment = new HomeFragment();
//                showFragment(fragment);
            }
        });
        imageViewShowMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (drawerLayout.isDrawerOpen()) {
                    drawerLayout.closeDrawer();
                } else {
                    drawerLayout.openDrawer();
                }
            }
        });
        imageViewShowMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drawerLayout.isDrawerOpen()) {
                    drawerLayout.closeDrawer();
                } else {
                    drawerLayout.openDrawer();
                }
            }
        });

        cartTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer();
                bottomNavigation.show(1,true);
                showFragment(new CartFragment());
            }
        });

    }

    ReviewManager reviewManager;

    private void initRate() {
        reviewManager = ReviewManagerFactory.create(this);

        txtRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer();
                showRateApp();
            }
        });
    }
    ReviewManager manager;
    private ReviewInfo reviewInfo;
    public void showRateApp() {

        manager = ReviewManagerFactory.create(this);

        Task<ReviewInfo> request = manager.requestReviewFlow();
        request.addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                reviewInfo = task.getResult();
                Task<Void> flow = manager.launchReviewFlow(MainActivity.this, reviewInfo);
                flow.addOnCompleteListener(taskdone -> {
                    // This is the next follow of your app
                    Toast.makeText(this, "تم تقييم التطبيق بنجاح", Toast.LENGTH_SHORT).show();

                });
            }else {
                showRateAppFallbackDialog();
            }
        });
    }

    /**
     * Showing native dialog with three buttons to review the app
     * Redirect user to playstore to review the app
     */
    private void showRateAppFallbackDialog() {
        new MaterialAlertDialogBuilder(this)
                .setTitle("R.string.rate_app_title")
                .setMessage("R.string.rate_app_message")
                .setPositiveButton("R.string.rate_btn_pos", (dialog, which) -> {

                })
                .setNegativeButton("R.string.rate_btn_neg",
                        (dialog, which) -> {
                        })
                .setNeutralButton("R.string.rate_btn_nut",
                        (dialog, which) -> {
                        })
                .setOnDismissListener(dialog -> {
                })
                .show();
    }


    TextView cartTv,nameTv;


    private void initialWeget() {
        layoutMyProfile = findViewById(R.id.id_layout_Myprofile);
        nameTv=findViewById(R.id.nameTv);
        callUs = findViewById(R.id.callUs_id);
        main_fg = findViewById(R.id.main_tv);
        cartTv = findViewById(R.id.cartTv);
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

    @Override
    protected void onResume() {
        super.onResume();

        onChangeQuantityListener = this;
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
  public static List<CartListModel.Item> cart;
    public void setUpListCart(){
        cart=new ArrayList<>();
        RetroWeb.getClient().create(ServiceApi.class).onGetCartList("",globalPrefrencies.getLanguage(),"Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<CartListModel>() {
            @Override
            public void onResponse(Call<CartListModel> call, Response<CartListModel> response) {
                if(response.isSuccessful()){
                    if(response.body().getCart()!=null) {
                        cart.addAll(response.body().getCart().getItems());
                        onChange();
                    }
                }
            }

            @Override
            public void onFailure(Call<CartListModel> call, Throwable t) {

                Log.e("AMAM",t.getMessage()+" ");
                try {

                    Utils.handleException(getWindow().getDecorView(),t);

                }catch (Exception e){

                    Log.e("onFailure ",t.toString());

                }
            }
        });
    }


    @Override
    public void onChange() {

        Log.e("dataaaaa",cart.size()+" ");
        if(cart.size()>0){
            circleImageViewBandge.setVisibility(View.VISIBLE);
       }else {
            circleImageViewBandge.setVisibility(View.GONE);
        }
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
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();

        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.item_anim_fall_down, R.anim.fadeout, R.anim.item_anim_fall_down, R.anim.fragment_slide_right_exit);
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();

    }

    AlertDialog.Builder builder;

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
            bottomNavigation.show(0, true);
            layoutTpback.setVisibility(View.VISIBLE);
            layoutTpFront.setVisibility(View.VISIBLE);
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