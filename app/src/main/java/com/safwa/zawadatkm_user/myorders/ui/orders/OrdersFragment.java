package com.safwa.zawadatkm_user.myorders.ui.orders;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.safwa.zawadatkm_user.Adabter.PagerAdaptar;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.databinding.OrdersFragmentBinding;
import com.safwa.zawadatkm_user.myorders.ui.orders.all.AllorderFragment;
import com.safwa.zawadatkm_user.myorders.ui.orders.finish.FinishOrdersFragment;
import com.safwa.zawadatkm_user.myorders.ui.orders.pinding.pindingFragment;


public class OrdersFragment extends Fragment {

    private OrdersViewModel mViewModel;

    OrdersFragmentBinding binding;

    public static OrdersFragment newInstance() {
        return new OrdersFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.orders_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(OrdersViewModel.class);
        binding.setLifecycleOwner(this);
        binding.setOrdersVmodel(mViewModel);
        return binding.getRoot();
    }

    GlobalPrefrencies globalPrefrencies;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        globalPrefrencies = new GlobalPrefrencies(getContext());
        Utils.setLocale(getContext(), "ar");//globalPrefrencies.getLanguage());
        // TODO: Use the ViewModel
        initWidget();
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    PagerAdaptar adaptar;

    private void initWidget() {

        adaptar = new PagerAdaptar(getChildFragmentManager());
        adaptar.addNewFragment(new AllorderFragment());
        adaptar.addNewFragment(new pindingFragment());
        adaptar.addNewFragment(new FinishOrdersFragment());
        binding.tabLayout.setupWithViewPager(binding.viewpager);
        binding.viewpager.setAdapter(adaptar);

        setUpTabsTopPager();

    }

    private void setUpTabsTopPager() {

        binding.tabLayout.getTabAt(0).setText("جميع الطلبات");
        binding.tabLayout.getTabAt(1).setText("الجارية");
        binding.tabLayout.getTabAt(2).setText("السابقة");
        binding.tabLayout.setTabTextColors(Color.parseColor("#FFAFAFAF"), Color.parseColor("#000000"));
    }
}