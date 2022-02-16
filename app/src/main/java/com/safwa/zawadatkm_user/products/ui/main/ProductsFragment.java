package com.safwa.zawadatkm_user.products.ui.main;

import android.os.Bundle;
import android.util.Log;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.safwa.zawadatkm_user.Adabter.CategoriesWithProductsAdapter;
import com.safwa.zawadatkm_user.Adabter.ProductsAdapter;
import com.safwa.zawadatkm_user.Models.Datum;
import com.safwa.zawadatkm_user.Models.categories.CategoriesModel;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.databinding.ProductFragmentBinding;


public class ProductsFragment extends Fragment implements CategoriesWithProductsAdapter.ChangeProducts {

    private ProducViewModel mViewModel;

    CategoriesWithProductsAdapter.ChangeProducts changeProducts;
    public static ProductsFragment newInstance() {
        return new ProductsFragment();
    }

    ProductFragmentBinding binding;
    GlobalPrefrencies globalPrefrencies;

    String id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.product_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(ProducViewModel.class);
//        mViewModel=new ProducViewModel(getContext());
        globalPrefrencies = new GlobalPrefrencies(getContext());
        Utils.setLocale(getContext(), "ar");
        binding.setProductsVmodel(mViewModel);
        binding.setLifecycleOwner(this);


        changeProducts=this;
        return binding.getRoot();
    }

    String name;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(requireActivity().getIntent()!=null) {
            id = requireActivity().getIntent().getExtras().getString("id");
            name = requireActivity().getIntent().getExtras().getString("name");
            binding.txtCat.setText(name+"");
            getItems(id);
        }
        setupCatigories();


        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });


        binding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupCatigories();
            }
        });
    }

    private void getItems(String id) {

        final ProductsAdapter adapter = new ProductsAdapter(getContext());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
        binding.rvProduvts.setLayoutManager(gridLayoutManager);
        binding.rvProduvts.setHasFixedSize(false);
        mViewModel.initPro(getContext(),id);
        mViewModel.mutableLiveDataProductsPageList.observe(getViewLifecycleOwner(), new Observer<PagedList<Datum>>() {
            @Override
            public void onChanged(PagedList<Datum> data) {
                binding.noitem.setVisibility(View.VISIBLE);

                if(data!=null||data.snapshot().size()>0) {

                    adapter.submitList(data);
                    binding.noitem.setVisibility(View.GONE);
                }
                Log.e("SSS",data.size()+"");
            }
        });

        binding.swiperefresh.setRefreshing(false);
        binding.rvProduvts.setAdapter(adapter);
    }

    private void setupCatigories() {
        final CategoriesWithProductsAdapter adapter = new CategoriesWithProductsAdapter(getContext(),this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.rvCat.setLayoutManager(linearLayoutManager);
        mViewModel.initCat(getContext());
        mViewModel.mutableLiveDataCatigoriesPageList.observe(getViewLifecycleOwner(), new Observer<PagedList<CategoriesModel.Datum>>() {
            @Override
            public void onChanged(PagedList<CategoriesModel.Datum> data) {
                adapter.submitList(data);
            }
        });
        binding.rvCat.setAdapter(adapter);
        binding.swiperefresh.setRefreshing(false);
    }


    @Override
    public void OnChangeProducts(String id) {

        getItems(id);

    }
}