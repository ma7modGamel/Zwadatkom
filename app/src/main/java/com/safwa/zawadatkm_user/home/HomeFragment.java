package com.safwa.zawadatkm_user.home;

import static com.safwa.zawadatkm_user.home.MainActivity.layoutTpFront;
import static com.safwa.zawadatkm_user.home.MainActivity.layoutTpback;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.safwa.zawadatkm_user.Adabter.CategoriesAdapter;
import com.safwa.zawadatkm_user.Adabter.OffersAdapter;
import com.safwa.zawadatkm_user.Adabter.ProductsAdapter;
import com.safwa.zawadatkm_user.Models.Datum;
import com.safwa.zawadatkm_user.Models.categories.CategoriesModel;
import com.safwa.zawadatkm_user.Models.offer.OffersModel;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.categories.CatigoriesActivity;
import com.safwa.zawadatkm_user.databinding.FragmentHomeBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ViewPagerAdapter myViewPagerAdapter2;
//    SliderPagerAdapter myViewPagerAdapter;
    int currentPage = 0;
    FragmentHomeBinding fragmentHomeBinding;
    HomeViewModel mViewModel;
    GlobalPrefrencies globalPrefrencies;
    ArrayList<OffersModel.Offer> datumArrayList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mViewModel = new HomeViewModel(getContext());

        globalPrefrencies = new GlobalPrefrencies(getContext());
        Utils.setLocale(getContext(), "ar");//globalPrefrencies.getLanguage());
        fragmentHomeBinding.setHomeViewModel(mViewModel);
        fragmentHomeBinding.setLifecycleOwner(this);


        setupOOffers();
        setupCatigories();
        setUpProducts();

        fragmentHomeBinding.txtShowAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), CatigoriesActivity.class);
                startActivity(intent);
            }
        });
        fragmentHomeBinding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setupOOffers();
                setupCatigories();
                setUpProducts();
            }
        });

        return fragmentHomeBinding.getRoot();
    }

    private void setUpProducts() {
        final ProductsAdapter adapter = new ProductsAdapter(getContext());
        StaggeredGridLayoutManager gridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        gridLayoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        fragmentHomeBinding.rvProducts.setLayoutManager(gridLayoutManager);
        fragmentHomeBinding.rvProducts.setHasFixedSize(false);
        mViewModel.mutableLiveDataProductsPageList.observe(getViewLifecycleOwner(), new Observer<PagedList<Datum>>() {
            @Override
            public void onChanged(PagedList<Datum> data) {
                adapter.submitList(data);
                Log.e("SSS",data.size()+"");
            }
        });

        fragmentHomeBinding.swiperefresh.setRefreshing(false);
        fragmentHomeBinding.rvProducts.setAdapter(adapter);

    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        layoutTpback.setVisibility(View.VISIBLE);
        layoutTpFront.setVisibility(View.VISIBLE);
    }

    private void setupCatigories() {
        final CategoriesAdapter adapter = new CategoriesAdapter(getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        fragmentHomeBinding.catRv.setLayoutManager(linearLayoutManager);
        mViewModel.mutableLiveDataCatigoriesPageList.observe(getViewLifecycleOwner(), new Observer<PagedList<CategoriesModel.Datum>>() {
            @Override
            public void onChanged(PagedList<CategoriesModel.Datum> data) {
                adapter.submitList(data);
            }
        });
        fragmentHomeBinding.catRv.setAdapter(adapter);
    }


    OffersAdapter adapter;
    private void setupOOffers() {


        mViewModel.init(getContext());
        mViewModel.OffersModelDataSourceMutableLiveData.observe(getViewLifecycleOwner(), new Observer<OffersModel>() {
            @Override
            public void onChanged(OffersModel offersModel) {
               if (offersModel.getOffers().size()>0){
                   fragmentHomeBinding.RelativePager.setVisibility(View.VISIBLE);
                   datumArrayList.clear();
                   datumArrayList.addAll(offersModel.getOffers());
                   myViewPagerAdapter2 = new ViewPagerAdapter(getContext(),datumArrayList);
                   adapter = new OffersAdapter(datumArrayList, getContext());
                   myViewPagerAdapter2.notifyDataSetChanged();
                   setUpAdapter();
               }else {
                   fragmentHomeBinding.RelativePager.setVisibility(View.GONE);
               }

            }
        });
    }


    private void setUpAdapter() {

        fragmentHomeBinding.indecator.setViewPager(fragmentHomeBinding.viewPager);
        fragmentHomeBinding.viewPager.setAdapter(myViewPagerAdapter2);
        fragmentHomeBinding.swiperefresh.setRefreshing(false);

        setupChangeViwePager();

    }

    private void setupChangeViwePager() {

        Handler handler = new Handler();
        Runnable update = new Runnable() {
            @Override
            public void run() {
                {
                    if (currentPage == myViewPagerAdapter2.getCount()) {
                        currentPage = 0;
                    }
                    fragmentHomeBinding.viewPager.setCurrentItem(currentPage++, true);
                }
            }
        };

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(update);
            }
        }, 3000, 5000);
    }


    class ViewPagerAdapter extends PagerAdapter {

        // Context object
        Context context;

        // Array of images
        ArrayList<OffersModel.Offer> images;

        // Layout Inflater
        LayoutInflater mLayoutInflater;


        // Viewpager Constructor
        public ViewPagerAdapter(Context context, ArrayList<OffersModel.Offer> images) {
            this.context = context;
            this.images = images;
            mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // return the number of images
            return images.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == ((RelativeLayout) object);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            // inflating the item.xml
            View itemView = mLayoutInflater.inflate(R.layout.itemads, container, false);

            // referencing the image view from the item.xml file
            ImageView imageView = (ImageView) itemView.findViewById(R.id.imgback);

            // setting the image in the imageView
            //imageView.setImageResource(images[position]);

            Picasso.with(getActivity()).load(datumArrayList.get(position).getImage()).
                    placeholder(R.drawable.logocolor)
                   .into(imageView);

            // Adding the View
            Objects.requireNonNull(container).addView(itemView);

            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((RelativeLayout) object);
        }
    }
}