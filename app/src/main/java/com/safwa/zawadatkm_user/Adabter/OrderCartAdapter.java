package com.safwa.zawadatkm_user.Adabter;


import static com.safwa.zawadatkm_user.home.CartFragment.updateCartModel;
import static com.safwa.zawadatkm_user.home.CartFragment.updateModel;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.safwa.zawadatkm_user.Models.detailsorders.OrderDetailsModel;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;

import java.util.List;


public class OrderCartAdapter extends RecyclerView.Adapter<OrderCartAdapter.ViewHolder> {


    List<OrderDetailsModel.Item> dataListProduct;


    public OrderCartAdapter(List<OrderDetailsModel.Item> dataListProduct) {

        this.dataListProduct = dataListProduct;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_order_details, viewGroup, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") int i) {

        OrderDetailsModel.Item item = dataListProduct.get(i);
        Log.e("idddd", item.getProductOptionId() + "");


        Glide.with(holder.itemView.getContext()).load(item.getProductOption().getProductDetails().getImages().get(0).getImage() + "")
                .placeholder(R.drawable.logocolor).into(holder.imageViewItem);
        holder.textViewQuantity.setText(item.getQty() + "  ⓧ  " );
        holder.txtName.setText(item.getProductOption().getProductDetails().getName() + " ");

        holder.txtPrice.setText(item.getProductOption().getPrice() + "");
        holder.textViewType.setText(item.getProductOption().getName() + " ");


    }


    @Override
    public int getItemCount() {
        return dataListProduct.size();
    }

    GlobalPrefrencies globalPrefrencies;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewType, textViewQuantity, txtName, txtPrice;
        ImageView imageViewItem;
        LinearLayout linearLayout;
        public RelativeLayout viewBackground, viewForeground;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.layoutnoti);
            textViewType = itemView.findViewById(R.id.txtType);
            txtPrice = itemView.findViewById(R.id.txtprice);
            txtName = itemView.findViewById(R.id.txtName);
            textViewType = itemView.findViewById(R.id.txtType);
            imageViewItem = itemView.findViewById(R.id.img);
            textViewQuantity = itemView.findViewById(R.id.txtQuantity);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);
        }
    }


    public Double getAllPrice() {
        int countProduct = 0;
        double totalPrice = 0;
        for (int i = 0; i < dataListProduct.size(); i++) {

            if (dataListProduct.get(i).getQty() == null) {
                dataListProduct.get(i).setQty(1);
                try {
                    countProduct = Integer.parseInt(dataListProduct.get(i).getQty().toString());
//                    if(dataListProduct.get(i).getOffer()!=null){
//                        priceOffer=dataListProduct.get(i).getOffer();
//                    }else {
//                        priceOffer=(Double) dataListProduct.get(i).getPrice();
//                    }
                    totalPrice += (countProduct * Integer.parseInt(dataListProduct.get(i).getProductOption().getPrice()));
                } catch (Exception e) {
                    Log.e("ssAA", e.getMessage());
                }
            } else {
                try {
                    countProduct = Integer.parseInt(dataListProduct.get(i).getQty().toString());

//                    if(dataListProduct.get(i).getOffer()!=null){
//                        priceOffer=dataListProduct.get(i).getOffer();
//                    }else {
//                        priceOffer=(Double) dataListProduct.get(i).getPrice();
//                    }
                    totalPrice += (countProduct * Double.parseDouble(dataListProduct.get(i).getProductOption().getPrice().toString()));
                } catch (Exception e) {
                    Log.e("ssAA2", e.getMessage());
                }
            }
            Log.e("SAAWWW", i + " " + countProduct * Double.parseDouble(dataListProduct.get(i).getProductOption().getPrice()));
        }

        return (double) totalPrice;
    }

    public String getAllQuantity() {
        int totalQuant = 0;
        for (int i = 0; i < dataListProduct.size(); i++) {
            if (dataListProduct.get(i).getQty() == null) {
                dataListProduct.get(i).setQty(1);
                totalQuant += (Integer.parseInt(dataListProduct.get(i).getQty().toString()));
            } else {
                totalQuant += (Integer.parseInt(dataListProduct.get(i).getQty().toString()));
            }
        }
        return "( " + totalQuant + " items ): ";
    }

}
