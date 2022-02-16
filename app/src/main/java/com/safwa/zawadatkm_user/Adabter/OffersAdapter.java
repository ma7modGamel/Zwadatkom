package com.safwa.zawadatkm_user.Adabter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.safwa.zawadatkm_user.Models.offer.OffersModel;
import com.safwa.zawadatkm_user.R;


import java.util.List;

public class OffersAdapter extends RecyclerView.Adapter<OffersAdapter.OffersVmodel> {
    private Context context;


    List<OffersModel.Offer> datumList;

    int delet = 10;

    public OffersAdapter(List<OffersModel.Offer> dataListProduct,Context context) {

        this.context=context;
        this.datumList = dataListProduct;

    }
    @NonNull
    @Override
    public OffersVmodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new OffersVmodel(view);
    }


    @Override
    public int getItemCount() {
        return datumList.size();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final OffersVmodel holder, int position) {
        final OffersModel.Offer item = datumList.get(position);


        holder.txt1.setText(item.getName().getAr()+" ");
        holder.txt2.setText(item.getPromotion().getPromotionValue()+" ");
        Glide.with(holder.itemView.getContext()).load(item.getImage())
                .placeholder(R.drawable.logocolor).into(holder.imageViewback);

    }

    public static class OffersVmodel extends RecyclerView.ViewHolder {
        TextView txt1 , txt2 ;
        ImageView imageViewback;
        public OffersVmodel(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.txt1);
            txt2=itemView.findViewById(R.id.txt2);
            imageViewback=itemView.findViewById(R.id.imgback);

        }
    }
}

