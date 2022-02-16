package com.safwa.zawadatkm_user.single.order;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.safwa.zawadatkm_user.Adabter.OrderCartAdapter;
import com.safwa.zawadatkm_user.Adabter.OrdersAdapter;
import com.safwa.zawadatkm_user.Models.ProductsDetailsModel;
import com.safwa.zawadatkm_user.Models.detailsorders.OrderDetailsModel;
import com.safwa.zawadatkm_user.Models.orders.OrdersListModel;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.databinding.ActivitySingleOrderBinding;

public class SingleOrderActivity extends AppCompatActivity {

    GlobalPrefrencies globalPrefrencies;
    ActivitySingleOrderBinding binding;
    SingleOrderViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalPrefrencies = new GlobalPrefrencies(this);
        Utils.setLocale(SingleOrderActivity.this, "ar");//globalPrefrencies.getLanguage());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_single_order);
        mViewModel = ViewModelProviders.of(this).get(SingleOrderViewModel.class);
        binding.setSingleOrderVmodel(mViewModel);
        binding.setLifecycleOwner(this);

        String id = getIntent().getExtras().getString("id");
        getDetailsOrder(id);

    }

    private void getDetailsOrder(String id) {
        mViewModel.setUpOrders(id + "", this);
        mViewModel.ProductsMutableLiveData.observe(this, new Observer<OrderDetailsModel>() {
            @Override
            public void onChanged(OrderDetailsModel productsDetailsModel) {
                setUpDataItem(productsDetailsModel);
            }
        });
    }

    OrderCartAdapter adapter;
    private void setUpDataItem(OrderDetailsModel productsDetailsModel) {

        if(productsDetailsModel.getOrder().getCart()!=null&&productsDetailsModel.getOrder().getCart().getItems().size()>0) {
            adapter = new OrderCartAdapter(productsDetailsModel.getOrder().getCart().getItems());
            Toast.makeText(this, "" + productsDetailsModel.getOrder().getCart().getItems().size(), Toast.LENGTH_SHORT).show();

            adapter.notifyDataSetChanged();
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
            binding.rvOderrs.setLayoutManager(layoutManager);
            binding.rvOderrs.setAdapter(adapter);
        }
        setupStatus(productsDetailsModel.getOrder().getStatus());
    }


    @SuppressLint("ResourceAsColor")
    private void setupStatus(OrderDetailsModel.Status status) {
        if (status.getId()+1 == 1) {
            binding.status1.setVisibility(View.VISIBLE);
            binding.status2.setVisibility(View.INVISIBLE);
            binding.status3.setVisibility(View.INVISIBLE);
            binding.status4.setVisibility(View.INVISIBLE);
            binding.status1.setText("تم الطلب");//item.getStatus().getName() + " ");


            binding.status1.setTextColor(getResources().getColor(R.color.purple_200));

            binding.cir1.setImageResource(R.color.purple_200);
            binding.cir2.setImageResource(R.color.white);
            binding.cir3.setImageResource(R.color.white);
            binding.cir4.setImageResource(R.color.white);

            binding.space1.setBackgroundResource(R.color.white);
            binding.space2.setBackgroundResource(R.color.white);
            binding.space3.setBackgroundResource(R.color.white);


        } else if (status.getId()+1 == 2) {
            binding.status1.setVisibility(View.INVISIBLE);
            binding.status2.setVisibility(View.VISIBLE);
            binding.status3.setVisibility(View.INVISIBLE);
            binding.status4.setVisibility(View.INVISIBLE);
            binding.status2.setText("تم استلام الطلب");//item.getStatus().getName() + " ");
            binding.status2.setTextColor(getResources().getColor(R.color.purple_500));
            binding.cir1.setImageResource(R.color.purple_500);
            binding.cir2.setImageResource(R.color.purple_500);
            binding.cir3.setImageResource(R.color.white);
            binding.cir4.setImageResource(R.color.white);

            binding.space1.setBackgroundResource(R.color.purple_500);
            binding.space2.setBackgroundResource(R.color.white);
            binding.space3.setBackgroundResource(R.color.white);


        } else if (status.getId()+1 == 3) {
            binding.status3.setTextColor(getResources().getColor(R.color.purple_700));

            binding.status1.setVisibility(View.INVISIBLE);
            binding.status2.setVisibility(View.INVISIBLE);
            binding.status3.setVisibility(View.VISIBLE);
            binding.status4.setVisibility(View.INVISIBLE);
            binding.status3.setText("جاري التوصيل");//item.getStatus().getName() + " ");

            binding.cir1.setImageResource(R.color.purple_700);
            binding.cir2.setImageResource(R.color.purple_700);
            binding.cir3.setImageResource(R.color.purple_700);
            binding.cir4.setImageResource(R.color.white);

            binding.space1.setBackgroundResource(R.color.purple_700);
            binding.space2.setBackgroundResource(R.color.purple_700);
            binding.space3.setBackgroundResource(R.color.white);


        } else if (status.getId()+1 == 4) {
            binding.status1.setVisibility(View.INVISIBLE);
            binding.status2.setVisibility(View.INVISIBLE);
            binding.status3.setVisibility(View.INVISIBLE);
            binding.status4.setVisibility(View.VISIBLE);
            binding.status4.setTextColor(getResources().getColor(R.color.green));

            binding.status4.setText("تم التوصيل بنجاح");//item.getStatus().getName() + " ");

        }
    }

}
