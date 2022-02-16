package com.safwa.zawadatkm_user.products;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.products.ui.main.ProductsFragment;

public class ProductsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_activity);
        Utils.setLocale(this,"ar");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ProductsFragment.newInstance())
                    .commitNow();
        }
    }
}