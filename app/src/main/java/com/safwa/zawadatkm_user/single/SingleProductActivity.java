package com.safwa.zawadatkm_user.single;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.safwa.zawadatkm_user.Models.BaseModel;
import com.safwa.zawadatkm_user.Models.Datum;
import com.safwa.zawadatkm_user.Models.ProductsDetailsModel;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.ChangeCountCartInterface;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.databinding.ActivitySingleProductBinding;
import com.safwa.zawadatkm_user.home.CartFragment;
import com.safwa.zawadatkm_user.home.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class SingleProductActivity extends AppCompatActivity implements ChangeCountCartInterface  {



    ChangeCountCartInterface changeCountCartInterface;
    ActivitySingleProductBinding binding;
    SingleProductViewModel mViewModel;
    GlobalPrefrencies globalPrefrencies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalPrefrencies = new GlobalPrefrencies(this);
        Utils.setLocale(SingleProductActivity.this,"ar");//globalPrefrencies.getLanguage());
        binding= DataBindingUtil.setContentView(this,R.layout.activity_single_product);
        mViewModel= ViewModelProviders.of(this).get(SingleProductViewModel.class);
        binding.setSingleViewModel(mViewModel);
        binding.setLifecycleOwner(this);

        String id_= Objects.requireNonNull(getIntent().getExtras()).getString("product_id");



        getDetailsItem(id_);
        binding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getDetailsItem(id_);
            }
        });
        binding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.imgIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.txtQuantity.getText().toString().equals("1") || !binding.txtQuantity.getText().toString().equals("0")) {
                    String tvQuantityText = binding.txtQuantity.getText().toString();
                    int quantInt = Integer.parseInt(tvQuantityText);
                    if (quantInt != 1) {
                        quantInt = quantInt - 1;
                    } else {
                        Toast.makeText(SingleProductActivity.this, "cannotmakethisprocess", Toast.LENGTH_SHORT).show();
                    }

                    binding.txtQuantity.setText(quantInt + "");
                    onChangeCount();
                }
            }
        });

        binding.bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SingleProductActivity.this, MainActivity.class);
                intent.putExtra("opencart","opencart");
                startActivity(intent);
                finish();
            }
        });
        binding.addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToOnlineCart(id_,binding.txtQuantity.getText().toString(),idOpion,v);
                if (getFragmentManager().getBackStackEntryCount() > 1) {
                    getFragmentManager().popBackStack();
                }
            }
        });
        binding.imgDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tvQuantityText = binding.txtQuantity.getText().toString();
                int quantInt = Integer.parseInt(tvQuantityText);
                quantInt = quantInt + 1;
                binding.txtQuantity.setText(quantInt + "");
                onChangeCount();
            }
        });


    }

    String idOpion="1";
    private void addToOnlineCart(String id_, String qnt, String idOpion, View v) {

        mViewModel.AddToCartOnLine(id_,qnt,idOpion,this);

        mViewModel.addToCartMutableLiveData.observe(this, new Observer<BaseModel>() {
            @Override
            public void onChanged(BaseModel baseModel) {
                if(baseModel.getSucess()){
                    Snackbar snackbar;
                    if(!globalPrefrencies.getLanguage().equals("ar")){


                        snackbar = Snackbar.make(v, "Done , Already found", Snackbar.LENGTH_LONG);
                        snackbar.setAction("Go to Cart", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "تم فتح السلة", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        snackbar = Snackbar.make(v, "هذا الطلب موجود بالفعل", Snackbar.LENGTH_LONG);
                        snackbar.setAction("فتح السلة", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "تم فتح السلة", Toast.LENGTH_SHORT).show();
                            }
                        });

                        //   }

                    }



                    View snackView = snackbar.getView();
                    snackView.setBackgroundResource(R.drawable.edittextcardgray2);
                    snackView.setPadding(4, 4, 4, 4);
                    snackView.setBackgroundColor(ContextCompat.getColor(SingleProductActivity.this, R.color.white));
                    TextView snackViewText = snackView.findViewById(R.id.snackbar_text);
                    snackViewText.setTextSize(14);
                    snackViewText.setTextColor(ContextCompat.getColor(SingleProductActivity.this, R.color.green));
                    Button snackViewButton = snackView.findViewById(R.id.snackbar_action);
                    snackViewButton.setTextColor(ContextCompat.getColor(SingleProductActivity.this, R.color.black));
                    snackViewButton.setTextSize(20);
                    snackbar.show();

                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        changeCountCartInterface = this;
        onChangeCount();
    }

    private void getDetailsItem(String id_) {
        mViewModel.setUpProduct(id_ + "", this);
        mViewModel.ProductsMutableLiveData.observe(this, new Observer<ProductsDetailsModel>() {
            @Override
            public void onChanged(ProductsDetailsModel productsDetailsModel) {
                item = productsDetailsModel.getProduct();
                idOpion=productsDetailsModel.getProduct().getLowestOption().getId()+"";
                setUpDataItem(productsDetailsModel);
                setUpModel(productsDetailsModel);
            }
        });

    }
    private void setUpModel(ProductsDetailsModel detailsProductModel) {

        binding.txtNameCategory.setText(detailsProductModel.getProduct().getListCategory().get(0).getName()+"");//.get(0).g+ "");

        binding.txtNameItem.setText(detailsProductModel.getProduct().getName()+"");
        if(detailsProductModel.getProduct().getPrice()!=null) {
            binding.txtprice.setText(detailsProductModel.getProduct().getPrice() + " ر.س ");
        }else {
            binding.txtprice.setVisibility(View.INVISIBLE);
        }
        binding.txtDetails.setText(detailsProductModel.getProduct().getDescription() + "");
       if(detailsProductModel.getProduct().getOptions()!=null&&detailsProductModel.getProduct().getOptions().size()>0) {
           getTypeSpinnerOption(detailsProductModel.getProduct().getOptions());
       }

        binding.swiperefresh.setRefreshing(false);
//        binding.namesm.setText(detailsProductModel.getData().getSupermarket().getName() + "");
//        binding.txtname.setText(detailsProductModel.getData().getName() + "");

    }

 //   ArrayList<Datum> data;




    ProductsDetailsModel.Product item;
    Timer timer;

    SliderPagerAdapter sliderPagerAdapter;


    int i;

    private boolean contains(ArrayList<Datum> dataArrayListProduct, Integer id) {


        for (i = 0; i < dataArrayListProduct.size(); i++) {

            Log.e("*  *"+dataArrayListProduct.get(i).getId()+"*  *","*"+id+"*   *"+i);
            if (dataArrayListProduct.get(i).getId().toString().equals(id+"")) {

                Log.e("positionsssss", String.valueOf(i));
                Log.e("connnnnn", "yes");

                return true;
            }
        }
        return false;

    }




    ArrayList<ProductsDetailsModel.Image> listSliderUrl = new ArrayList<>();


    private void setUpDataItem(ProductsDetailsModel detailsProductModel) {
        //image.replaceAll("\'","");
        Glide.with(this).load(detailsProductModel.getProduct().getListCategory().get(0).getImage())
                .placeholder(R.drawable.logocolor).into(binding.catimage);
        listSliderUrl.clear();
        if (detailsProductModel.getProduct().getImages() != null) {
            listSliderUrl.addAll(detailsProductModel.getProduct().getImages());
            for (int i = 0; i < listSliderUrl.size(); i++) {
                Log.e("Lis"+i,listSliderUrl.get(i)+"");
            }

            binding.indicator.setViewPager(binding.viewPager);


            final int[] ci = {0};

            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    binding.viewPager.post(new Runnable() {
                        @Override
                        public void run() {

                            Log.e("viewPager", "" + ci[0]);
                            binding.viewPager.setCurrentItem(ci[0] % 3);
                            ci[0]++;

                        }
                    });
                }
            }, 1000, 6000);

            sliderPagerAdapter = new SliderPagerAdapter();
            binding.viewPager.setAdapter(sliderPagerAdapter);
            binding.viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
            binding.indicator.setViewPager(binding.viewPager);
            sliderPagerAdapter.notifyDataSetChanged();
        }



    }

    private void doYourUpdate() {
        // TODO implement a refresh
        binding.swiperefresh.setRefreshing(false); // Disables the refresh icon
    }
    @Override
    public void onChangeCount() {

//        if(dataArrayListProduct.size()>0) {
//            binding.bndgRed.setVisibility(View.VISIBLE);
//        }else {
//            binding.bndgRed.setVisibility(View.GONE);
//
//        }
////        int size = CartActivity.dataArrayListProduct.size();
////        if(size>0){
////            binding.cartText.setText(size+"");
////            binding.cartText.setVisibility(View.VISIBLE);
////        }else {
////            binding.cartText.setVisibility(View.INVISIBLE);
////        }
    }

    @Override
    public void onChangeCountNotifi() {

    }

    public class SliderPagerAdapter extends PagerAdapter {

        private LayoutInflater layoutInflater;
        public SliderPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {


            layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.image_slider_preview, container, false);

            ImageView imageViewPreview = view.findViewById(R.id.sliderIv);


            String linkImageSlider = listSliderUrl.get(position).getImage();
            final Uri uri = Uri.parse(linkImageSlider);
            Log.e("URI SLIDER", uri + "");
            Glide.with(SingleProductActivity.this)
                    .load(uri).placeholder(R.drawable.logocolor)
//                    .thumbnail(0.5f)
                    .into(imageViewPreview);


            imageViewPreview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent fullScreenIntent = new Intent(SingleProductActivity.this, FullImage.class);
//                    fullScreenIntent.setData(uri);
//                    startActivity(fullScreenIntent);
                }
            });

            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return listSliderUrl.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    //  page change listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            displayMetaInfo(position);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    private void displayMetaInfo(int position) {
//        lblCount.setText((position + 1) + " / " + images.size());
//
//        MyImages image = images.get(position);
        //lblTitle.setText(image.getName());
        //lblDate.setText(image.getTimestamp());
    }

    ArrayList<String> arrayListOrtion=new ArrayList<>();
    List<ProductsDetailsModel.Option> valuesList=new ArrayList<>();
    private void getTypeSpinnerOption(List<ProductsDetailsModel.Option> values) {

        arrayListOrtion.clear();
        arrayListOrtion.add("الكمية");


        if(values!=null&&values.size()>0) {
            valuesList.addAll(values);
            for (int i = 0; i < this.valuesList.size(); i++) {
                arrayListOrtion.add(this.valuesList.get(i).getName());
            }
        }
        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, arrayListOrtion) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);

                }
                return view;
            }
        };

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                ((TextView) parent.getChildAt(0)).setTextSize(10);
                if (position > 0) {
//                    String id_item_to_get_brand = valuesList.get(position - 1).getUpdatedAt().toString();
//                    // Notify the selected item text
                    /////////
                    ProductsDetailsModel.Option option = valuesList.get(position-1);

                    binding.txtprice.setText(option.getPrice()+" ر.س ");

                    binding.txtprice.setVisibility(View.VISIBLE);

                    idOpion=valuesList.get(position-1).getId()+"";
                    Toast.makeText(SingleProductActivity.this, " تم اختيار "+selectedItemText, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        binding.spinner.setAdapter(spinnerArrayAdapter);

    }
    
}