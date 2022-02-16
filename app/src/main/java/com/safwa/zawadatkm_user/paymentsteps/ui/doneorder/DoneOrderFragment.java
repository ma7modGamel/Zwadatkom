package com.safwa.zawadatkm_user.paymentsteps.ui.doneorder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.databinding.DoneOrderFragmentBinding;
import com.safwa.zawadatkm_user.home.MainActivity;
import com.safwa.zawadatkm_user.myorders.OrdersActivity;

public class DoneOrderFragment extends Fragment {

    private DoneOrderViewModel mViewModel;

    DoneOrderFragmentBinding binding;
    public static DoneOrderFragment newInstance() {
        return new DoneOrderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater,R.layout.done_order_fragment,container,false);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DoneOrderViewModel.class);

        binding.ContinuoShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                getActivity().finish();
            }
        });



        binding.goToMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), OrdersActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                getActivity().finish();            }
        });
        // TODO: Use the ViewModel
    }

}