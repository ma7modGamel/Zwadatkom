package com.safwa.zawadatkm_user.myorders.ui.orders.finish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.safwa.zawadatkm_user.R;

public class FinishOrdersFragment extends Fragment {

    private FinishOrdersViewModel mViewModel;

    public static FinishOrdersFragment newInstance() {
        return new FinishOrdersFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.finish_orders_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FinishOrdersViewModel.class);
        // TODO: Use the ViewModel
    }

}