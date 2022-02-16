package com.safwa.zawadatkm_user.myorders.ui.orders.all;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.safwa.zawadatkm_user.Adabter.OrdersAdapter;
import com.safwa.zawadatkm_user.Models.orders.OrdersListModel;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.databinding.AllorderFragmentBinding;

public class AllorderFragment extends Fragment {

    private AllorderViewModel mViewModel;

    GlobalPrefrencies globalPrefrencies;
    AllorderFragmentBinding binding;
    public static AllorderFragment newInstance() {
        return new AllorderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding= DataBindingUtil.inflate(inflater,R.layout.allorder_fragment,container,false);
        mViewModel = new ViewModelProvider(this).get(AllorderViewModel.class);

        binding.setAllVmodel(mViewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        globalPrefrencies=new GlobalPrefrencies(getContext());
        Utils.setLocale(getContext(),"ar");
        setupRvOrders();
        binding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupRvOrders();
            }
        });


    }

    OrdersAdapter adapter;
    private void setupRvOrders() {
        adapter=new OrdersAdapter(getContext());
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.rvOderrs.setLayoutManager(gridLayoutManager);
        binding.rvOderrs.setHasFixedSize(false);
        mViewModel.getOrders(getContext());
        mViewModel.mutableLiveDataOrdersPageList.observe(this, new Observer<PagedList<OrdersListModel.Datum>>() {
            @Override
            public void onChanged(PagedList<OrdersListModel.Datum> data) {

                adapter.submitList(data);

            }
        });
        binding.rvOderrs.setAdapter(adapter);
        binding.swiperefresh.setRefreshing(false);
    }

}