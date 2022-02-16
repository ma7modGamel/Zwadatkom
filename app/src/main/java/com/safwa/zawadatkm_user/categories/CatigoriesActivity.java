package com.safwa.zawadatkm_user.categories;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.safwa.zawadatkm_user.Adabter.CategoriesAdapter2;
import com.safwa.zawadatkm_user.Models.categories.CategoriesModel;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.databinding.ActivityCatigoriesBinding;

public class CatigoriesActivity extends AppCompatActivity {

    CatigoriesViewModel mViewModel;
    GlobalPrefrencies globalPrefrencies;
    private ActivityCatigoriesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_catigories);
        mViewModel= ViewModelProviders.of(this).get(CatigoriesViewModel.class);
        globalPrefrencies=new GlobalPrefrencies(this);
        Utils.setLocale(this,globalPrefrencies.getLanguage());
        binding.setCatigoriesVmodel(mViewModel);
        binding.setLifecycleOwner(binding.getLifecycleOwner());

        setupCatigories();

        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupCatigories();
            }
        });

    }


    private void setupCatigories() {
        final CategoriesAdapter2 adapter = new CategoriesAdapter2(this);
        GridLayoutManager linearLayoutManager = new GridLayoutManager(this, 2,LinearLayoutManager.VERTICAL, false);
        binding.rvCat.setLayoutManager(linearLayoutManager);
        mViewModel.initCat(this);
        mViewModel.mutableLiveDataCatigoriesPageList.observe(this, new Observer<PagedList<CategoriesModel.Datum>>() {
            @Override
            public void onChanged(PagedList<CategoriesModel.Datum> data) {
                adapter.submitList(data);
            }
        });
        binding.rvCat.setAdapter(adapter);
        binding.swiperefresh.setRefreshing(false);
    }
}