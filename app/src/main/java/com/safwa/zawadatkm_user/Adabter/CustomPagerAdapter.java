package com.safwa.zawadatkm_user.Adabter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import com.safwa.zawadatkm_user.Models.offer.OffersModel;
import com.safwa.zawadatkm_user.R;

import java.util.List;


public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;

    List<OffersModel.Offer> offerList;
    public CustomPagerAdapter(Context context, List<OffersModel.Offer> offerList ){
        mContext=context;
        this.offerList=offerList;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position){
        OffersModel.Offer modelObject = offerList.get(position);

        LayoutInflater inflater= LayoutInflater.from(mContext);
        ViewGroup layout=(ViewGroup)inflater.inflate(R.layout.itemads,collection,false);
        collection.addView(layout);
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection,int position,Object view){
        collection.removeView((View)view);
    }

    @Override
    public int getCount(){
        return offerList.size();
    }

    @Override
    public boolean isViewFromObject(View view,Object object){
        return view==object;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return "";
    }


}
