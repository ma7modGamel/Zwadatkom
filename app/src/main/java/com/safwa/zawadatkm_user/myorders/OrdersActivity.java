package com.safwa.zawadatkm_user.myorders;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.myorders.ui.orders.OrdersFragment;

public class OrdersActivity extends AppCompatActivity {

    GlobalPrefrencies globalPrefrencies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders_activity);
        globalPrefrencies=new GlobalPrefrencies(this);
        Utils.setLocale(this,"ar");
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, OrdersFragment.newInstance())
                    .commitNow();
        }
    }
}