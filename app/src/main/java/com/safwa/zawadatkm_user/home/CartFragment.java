package com.safwa.zawadatkm_user.home;

import static com.safwa.zawadatkm_user.Adabter.CartOnlineAdapter.dataListProduct;
import static com.safwa.zawadatkm_user.home.MainActivity.layoutTpFront;
import static com.safwa.zawadatkm_user.home.MainActivity.layoutTpback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;
import com.safwa.zawadatkm_user.Adabter.CartAdapter;
import com.safwa.zawadatkm_user.Adabter.CartOnlineAdapter;
import com.safwa.zawadatkm_user.Models.BaseModel;
import com.safwa.zawadatkm_user.Models.CartListModel;
import com.safwa.zawadatkm_user.Models.UpdateCartModel;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.DeletCartItemInfoInterface;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.RecyclerItemTouchHelper;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.databinding.FragmentCartBinding;
import com.safwa.zawadatkm_user.paymentsteps.PaymentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment implements CartOnlineAdapter.onChangeQuantityListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        layoutTpback.setVisibility(View.GONE);
        layoutTpFront.setVisibility(View.GONE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    public static List<UpdateCartModel.CartItem> updateCartModel = new ArrayList<>();
    public static UpdateCartModel updateModel = new UpdateCartModel();
    FragmentCartBinding fragmentCartBinding;
    CartViewModel mViewModel;
    GlobalPrefrencies globalPrefrencies;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentCartBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        mViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        globalPrefrencies = new GlobalPrefrencies(getContext());
        Utils.setLocale(getContext(), "ar");//globalPrefrencies.getLanguage());
        fragmentCartBinding.setCartViewModel(mViewModel);
        fragmentCartBinding.setLifecycleOwner(this);


        MainActivity.drawerLayout.setFocusableInTouchMode(false);
        setUpRvOnLine();
        detailsOrderModel = new ArrayList<>();
        //
        setUpRv();
        //   cartDetails();


        setupUI(fragmentCartBinding.lay);
        fragmentCartBinding.codeEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    getActivity().getWindow().setSoftInputMode(
                            WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

                }
            }
        });

        fragmentCartBinding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // cartDetails();
                doYourUpdate();
            }
        });
        fragmentCartBinding.ContinuoToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateOrderQuntity();
                if (cartListModel3.getCart() != null) {
                    if (cartListModel3.getCart().getItems().size() > 0) {
                        openActivity();
                    } else {
                        Toast.makeText(getContext(), "يجب اضافة عناصر في السلة اولا", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        fragmentCartBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getFragmentManager().getBackStackEntryCount() > 1) {
                    getFragmentManager().popBackStack();
                    MainActivity.bottomNavigation.show(0, true);
                    layoutTpback.setVisibility(View.VISIBLE);
                    layoutTpFront.setVisibility(View.VISIBLE);
                }
            }
        });

        fragmentCartBinding.addProCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPromotion(fragmentCartBinding.codeEt.getText().toString());
            }
        });


        return fragmentCartBinding.getRoot();
    }

    private void CheckPromotion(String txtPromotion) {
        mViewModel.setUpPromotion(txtPromotion, getContext());
        mViewModel.checkPromotionMutableLiveData.observe(getViewLifecycleOwner(), new Observer<BaseModel>() {
            @Override
            public void onChanged(BaseModel baseModel) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (baseModel.getSucess()) {
                            fragmentCartBinding.txthint.setText(baseModel.getMessage());
                            updateOrderQuntity();
                        } else {
                            fragmentCartBinding.txthint.setText(baseModel.getMessage());
                        }
                    }
                }, 1000);
            }
        });


    }

    private void updateOrderQuntity() {

        Gson gson = new Gson();
        JsonElement element = gson.toJsonTree(updateCartModel, new TypeToken<List<UpdateCartModel.CartItem>>() {
        }.getType());

        if (!element.isJsonArray()) {
            // fail appropriately

        }
        JsonArray jsonArray = element.getAsJsonArray();
        String value = String.valueOf(jsonArray);

        UpdateCRTeVENT(value);


    }

    private void UpdateCRTeVENT(String value) {
        mViewModel.upDateCart(value, getContext());
        mViewModel.cartUpdateMutableLiveData.observe(this, new Observer<BaseModel>() {
            @Override
            public void onChanged(BaseModel baseModel) {
                Log.e(new Gson().toJson(baseModel), new Gson().toJson(updateCartModel));
                if (baseModel.getSucess()) {
                    setUpRvOnLine();
                } else {
                    Toast.makeText(getContext(), "Error" + baseModel.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    double finalTxt = 0;

    private void openActivity() {
        Intent intent = new Intent(getActivity(), PaymentActivity.class);

        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra("finalTxt", fragmentCartBinding.finalTxt.getText().toString());
        startActivity(intent);
    }

    public void setupUI(View view) {

        // Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(getActivity());
                    return false;
                }
            });
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    private void cartIsEmpty(CartListModel cartListModel) {
        if (cartListModel.getCart() != null && cartListModel.getCart().getItems() != null) {
            if (cartListModel.getCart().getItems().isEmpty()) {

                fragmentCartBinding.emptyCartTv.setVisibility(View.VISIBLE);
                fragmentCartBinding.rvCart.setVisibility(View.GONE);

            } else {
                fragmentCartBinding.emptyCartTv.setVisibility(View.GONE);
                fragmentCartBinding.rvCart.setVisibility(View.VISIBLE);
            }
        }
    }

    CartListModel cartListModel3;

    private void setUpRvOnLine() {

        mViewModel.setUpListCart(getContext(), fragmentCartBinding.codeEt.getText().toString());
        mViewModel.cartMutableLiveData.observe(getViewLifecycleOwner(), new Observer<CartListModel>() {
            @Override
            public void onChanged(CartListModel cartListModel) {

                cartListModel3 = cartListModel;
                cartIsEmpty(cartListModel);
                if (cartListModel.getCart() != null && cartListModel.getCart().getItems() != null) {

                    if (cartListModel.getCart().getItems().size() > 0) {

                        setupData(cartListModel);
                        fragmentCartBinding.txtPrice.setText(cartListModel.getCart().getTotal() + " ر.س ");
                        finalTxt = cartListModel.getCart().getTotal();
                        if (cartListModel.getPromotion().getSuccess()) {

                            double total = Double.parseDouble(cartListModel.getCart().getTotal().toString());
                            total = total + (0.5 * total);
                            if (cartListModel.getPromotion().getData().getType().equals("fixed")) {
                                if (Double.parseDouble(cartListModel.getPromotion().getData().getPromotionValue()) < total) {
                                    fragmentCartBinding.txtProm.setText(cartListModel.getPromotion().getData().getPromotionValue() + " ر.س ");
                                    fragmentCartBinding.finalTxt.setText((cartListModel.getCart().getTotal() - Integer.parseInt(cartListModel.getPromotion().getData().getPromotionValue())) + " ر.س ");
                                    finalTxt = cartListModel.getCart().getTotal() - Integer.parseInt(cartListModel.getPromotion().getData().getPromotionValue());
                                } else {
                                    fragmentCartBinding.txthint.setText("لاستخدام الكود يجب ان يكون قيمة الطلب " + total + " ر.س ");
                                }
                            } else {

                                fragmentCartBinding.txtProm.setText(cartListModel.getPromotion().getData().getPromotionValue() + " % ");

                                double percentValue = Double.parseDouble(cartListModel.getPromotion().getData().getPromotionValue());

                                double percentPoromotion = percentValue/ 100;
                                double priceAfterSale = (cartListModel.getCart().getTotal() - (percentPoromotion * cartListModel.getCart().getTotal()));
                                Log.e("SSS",percentValue+" "+priceAfterSale+" "+percentPoromotion) ;
                                fragmentCartBinding.finalTxt.setText(priceAfterSale + " ر.س ");
                                finalTxt = percentPoromotion;
                            }

                        } else {
                            fragmentCartBinding.txtProm.setText(" لا يوجد ");
                            fragmentCartBinding.finalTxt.setText(cartListModel.getCart().getTotal() + " ر.س ");
                        }
                    }
                }
            }
        });

    }

    CartListModel model;
    ArrayList<CartListModel.Item> detailsOrderModel;

    private void setupData(CartListModel cartListModel) {
        model = new CartListModel();
        model = cartListModel;
        detailsOrderModel.clear();
        this.detailsOrderModel.addAll(cartListModel.getCart().getItems());
        onlineAdapter = new CartOnlineAdapter(cartListModel.getCart().getItems(), new DeletCartItemInfoInterface() {
            @Override
            public void onDelet(int i) {
                if (i == 10) {
                    // fetchCartInfo();
                    if (cartListModel.getCart().getItems().isEmpty()) {
                        fragmentCartBinding.emptyCartTv.setVisibility(View.VISIBLE);
                    } else {
                        fragmentCartBinding.emptyCartTv.setVisibility(View.GONE);
                    }
                }
            }
        }, this);

        onlineAdapter.notifyDataSetChanged();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        fragmentCartBinding.rvCart.setLayoutManager(layoutManager);
        fragmentCartBinding.rvCart.setAdapter(onlineAdapter);
        fragmentCartBinding.numItem.setText(cartListModel.getCart().getItems().size() + "  عنصر ");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }


    CartOnlineAdapter.onChangeQuantityListener onChangeQuantityListener;

    @Override
    public void onResume() {
        super.onResume();
        onChangeQuantityListener = this;

    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(), 0);
        }
    }

    CartOnlineAdapter onlineAdapter;

    private void setUpRv() {
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.START, new RecyclerItemTouchHelper.RecyclerItemTouchHelperListener() {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
                if (viewHolder instanceof CartOnlineAdapter.ViewHolder) {
                    // get the removed item name to display it in snack ba
                    // remove the item from recycler view

                    onlineAdapter.removeItem(dataListProduct.get(viewHolder.getAdapterPosition()).getProductOption().getProductDetails().getId() + "", getContext());
                    //    fragmentCartBinding.numItem.setText(dataArrayListProduct.size() + " عنصر");

                    //  cartDetails();

                    // showing snack bar with Undo option

                    if (onlineAdapter.getItemCount() == 1) {
                        Intent intent = getActivity().getIntent();
                        getActivity().finish();
                        startActivity(intent);

                    }
                    setUpRvOnLine();
                }
            }
        });
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(fragmentCartBinding.rvCart);

    }

    private void doYourUpdate() {
        // TODO implement a refresh
        fragmentCartBinding.swiperefresh.setRefreshing(false); // Disables the refresh icon
    }

//    private void cartDetails() {
//        adapter = new CartAdapter(dataArrayListProduct, new DeletCartItemInfoInterface() {
//            @Override
//            public void onDelet(int i) {
//                if (i == 10) {
//                    fetchCartInfo();
//                    if (dataArrayListProduct.isEmpty()) {
//                        fragmentCartBinding.emptyCartTv.setVisibility(View.VISIBLE);
//                    } else {
//                        fragmentCartBinding.emptyCartTv.setVisibility(View.GONE);
//                    }
//                }
//            }
//        }, this);
//
//        adapter.notifyDataSetChanged();
//
//        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        fragmentCartBinding.rvCart.setLayoutManager(layoutManager);
//
//        fragmentCartBinding.rvCart.setAdapter(adapter);
//
//        fragmentCartBinding.numItem.setText(dataArrayListProduct.size() + "  عنصر ");
//
//
//    }
//
//
//    private void fetchCartInfo() {
//        String price;
//        int count = 0;
//        if (!dataArrayListProduct.isEmpty()) {
//
//            for (int i = 0; i < dataArrayListProduct.size(); i++) {
//
//                price = dataArrayListProduct.get(i).getPrice() + "";
//
//                  * CartActivity.shopProductsList.get(i).getProductCount();
//                Log.e("price", price);
//                // activityCartBinding.subPrice.setText(price);
//                count += dataArrayListProduct.get(i).getCount();
//            }
//        } else {
//            Log.e("price", String.valueOf(0));
//            // activityCartBinding.subPrice.setText("");
//
//            if (!globalPrefrencies.getLanguage().equals("ar")) {
//                //   activityCartBinding.subPrice.setText(0 + " EGY");
//            } else {
//                //   activityCartBinding.subPrice.setText(0 + " جنيه ");
//            }
//
//        }
//    }


    @Override
    public void onChange() {
        if (model.getPromotion().getSuccess()) {
            fragmentCartBinding.txtPrice.setText(onlineAdapter.getAllPrice() + " ر.س ");
            fragmentCartBinding.txtProm.setText(model.getPromotion().getData().getPromotionValue() + " ر.س ");
            fragmentCartBinding.finalTxt.setText((onlineAdapter.getAllPrice() - Integer.parseInt(model.getPromotion().getData().getPromotionValue())) + " ر.س ");
        } else {
            fragmentCartBinding.txtPrice.setText(onlineAdapter.getAllPrice() + " ر.س ");
            fragmentCartBinding.txtProm.setText(" لا يوجد ");
            fragmentCartBinding.finalTxt.setText(onlineAdapter.getAllPrice() + " ر.س ");
        }
//        activityCartBinding.totaltext.setText(" " + (Double.parseDouble(cartAdapter.getAllPrice())) + "");
//        int i = dataArrayListProduct.size() + notes.size();
//        activityCartBinding.count.setText("( " + i + " ) ");
//
//
//        if (!globalPrefrencies.getLanguage().equals("ar")) {
//            activityCartBinding.totalCurrency.setText(" EGY");
//        } else {
//            activityCartBinding.totalCurrency.setText(" جنيه ");
//        }
        //cartPriceTv.setText(" " + (Integer.parseInt(cartAdapter.getAllPrice().toString())));
    }
}