package com.safwa.zawadatkm_user.paymentsteps.ui.detailspayment;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.safwa.zawadatkm_user.Adabter.AddressesAdapter;
import com.safwa.zawadatkm_user.Models.MakeOrderModel;
import com.safwa.zawadatkm_user.Models.address.AddressListModel;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.databinding.DetailsPaymentFragmentBinding;
import com.safwa.zawadatkm_user.map.MapsActivity;
import com.safwa.zawadatkm_user.paymentsteps.PaymentActivity;
import com.safwa.zawadatkm_user.paymentsteps.ui.doneorder.DoneOrderFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Objects;


public class DetailsPaymentFragment extends Fragment {

    private DetailsPaymentViewModel mViewModel;

    DetailsPaymentFragmentBinding binding;
    GlobalPrefrencies globalPrefrencies;

    public static DetailsPaymentFragment newInstance() {
        return new DetailsPaymentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.details_payment_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(DetailsPaymentViewModel.class);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    String methodPayment = "cash";

    private GregorianCalendar calendar, pickedDate;
    AddressesAdapter adapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
        globalPrefrencies = new GlobalPrefrencies(getContext());
        Utils.setLocale(getContext(), "ar");//globalPrefrencies.getLanguage());
        dataArrayList = new ArrayList<>();
        binding.setDetailsPaymentViewModel(mViewModel);
        binding.setLifecycleOwner(this);

        adapter = new AddressesAdapter(getContext());

        String finalTxt = requireActivity().getIntent().getExtras().getString("finalTxt");

        binding.finalTxt.setText(finalTxt+"");
        binding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupAdeess();
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        binding.txtAddNewAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewAddress();
            }
        });
        binding.ContinuoToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                makeOrder();
            }
        });
        calendar = new GregorianCalendar();
        binding.edTime.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        new DatePickerDialog(getContext(), (datePicker, i, i1, i2) -> {
                            pickedDate = new GregorianCalendar(i, i1, i2);
                            if (calendar.before(pickedDate) || calendar.equals(pickedDate)) {
                                new TimePickerDialog(getContext(), (timePicker, hour, min) -> {
                                    pickedDate = new GregorianCalendar(i, i1, i2, hour, min);
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH);
                                    txtDate = dateFormat.format(pickedDate.getTime()) + "";
                                    binding.etTime.setText(dateFormat.format(pickedDate.getTime()));
                                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();

                            } else {
                                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH)).show();

                    }
                }
        );

    }

    private void makeOrder() {

        if (onCheackValidation()) {
            Log.e("text", txtDate);
            mViewModel.makeNewOrder(adapter.idAddress+"",1+"","cash",getContext());
            mViewModel.makeOrderModelMutableLiveData.observe(this, new Observer<MakeOrderModel>() {
                @Override
                public void onChanged(MakeOrderModel makeOrderModel) {
                    Log.e("SSSfff0000000000000",makeOrderModel.getSuccess()+"");
                    if(makeOrderModel.getSuccess()){
                        ((PaymentActivity) requireActivity()).showFragment(new DoneOrderFragment());
                    }
                }
            });


        }

    }

    String txtDate = "";
    int statusWallet = 0;



    @Override
    public void onResume() {
        super.onResume();
        setupPaymentCart();
        setupAdeess();
    }

    private void AddNewAddress() {
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        intent.putExtra("fromaddress", "yes");
        startActivity(intent);

    }

    private void setupPaymentCart() {

        binding.layPayOnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.layPayOnHome.setBackgroundResource(R.drawable.cardbordergreenwhite);
                binding.layPayStc.setBackgroundResource(R.drawable.cartwhite);
                methodPayment = "cash";
            }
        });
        binding.layPayStc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.layPayStc.setBackgroundResource(R.drawable.cardbordergreenwhite);
                binding.layPayOnHome.setBackgroundResource(R.drawable.cartwhite);
                methodPayment = "paypal";
            }
        });




    }



    ArrayList<AddressListModel.Data> dataArrayList;

    private void setupAdeess() {
        mViewModel.setUpAdresses(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rvAddress.setLayoutManager(linearLayoutManager);
        mViewModel.mutableLiveDataOrdersPageList.observe(requireActivity(), new Observer<PagedList<AddressListModel.Datum>>() {
            @Override
            public void onChanged(PagedList<AddressListModel.Datum> data) {
                Log.e("455", "--" + data.size());

                adapter.submitList(data);
                if (data.snapshot().size() > 0) {
                    binding.emptyCartTv.setVisibility(View.VISIBLE);
                } else {
                    binding.emptyCartTv.setVisibility(View.GONE);
                }

            }
        });
        binding.swiperefresh.setRefreshing(false);
        binding.rvAddress.setAdapter(adapter);

    }

    public boolean onCheackValidation() {


        if (!ValidateAddress()) {
            Log.e("mm1", false + "");
            return false;
        }
        if (!ValidateWallet()) {
            Log.e("mm2", false + "");
            return false;
        }
        if (!ValidateDate()) {
            Log.e("mm3", false + "");
            return false;
        }
        Log.e("mm4", true + "");


        return ValidateAddress();
    }

    private boolean ValidateWallet() {
        if (!binding.switchId.isChecked()) {
            statusWallet = 0;
            return true;
        } else {
            statusWallet = 1;
            return true;
        }
    }

    private boolean ValidateDate() {
        if (txtDate.equals("")) {
            binding.etTime.setError("من فضلك املأ الحقل التاريخ");
            Utils.requestFocus(binding.etTime, getActivity().getWindow());
            return false;
        }
        return true;
    }

    private boolean ValidateAddress() {
        if (adapter.getItemCount() == 0) {
            Toast.makeText(getContext(), "يرجي ادخال عنوان صحيح اولا..", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}