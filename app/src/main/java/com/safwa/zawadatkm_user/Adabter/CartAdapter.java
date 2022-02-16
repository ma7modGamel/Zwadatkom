package com.safwa.zawadatkm_user.Adabter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.safwa.zawadatkm_user.Models.Datum;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.DeletCartItemInfoInterface;

import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {


    List<Datum> dataListProduct;
    DeletCartItemInfoInterface deletCartItemInfoInterface;
    CartOnlineAdapter.onChangeQuantityListener onChangeQuantityListener;
    int delet = 10;

    public CartAdapter(List<Datum> dataListProduct, DeletCartItemInfoInterface deletCartItemInfoInterface, CartOnlineAdapter.onChangeQuantityListener onChangeQuantityListener) {

        this.dataListProduct = dataListProduct;
        this.deletCartItemInfoInterface = deletCartItemInfoInterface;
        this.onChangeQuantityListener = onChangeQuantityListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cart, viewGroup, false);

        return new ViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {


//        data = dataListProduct.get(i);
//
//        if (data.getPrice() == null) {
//            data.setPrice(0+"");
//            holder.tv_name.setText(data.getDescription() + " ");
//
//        }
//
////        int actPrice=Integer.parseInt(data.getPrice()+"");
//        try {
//
//
//            holder.tv_Quantity.setText(data.getCount() + "");
//
//            Glide.with(holder.itemView.getContext())
//                    .load(data.getImages())
//                    .placeholder(R.drawable.logocolor)
//                    .into(holder.img_cart);
//
//            if (data.getPrice().equals("0.0")) {
//                holder.tv_name.setText(data.getDescription() + "");
//                holder.totalPrice.setText("CCC");
//                holder.totalPrice.setTextSize(10f);
//                holder.totalPrice.setTextColor(RED);
//
//            } else {
//                holder.tv_name.setText(data.getName() + "");
//                priceOffer=0;
//
//                holder.totalPrice.setText(priceOffer + holder.itemView.getContext().getResources().getString(R.string.EGP));
//            }
//        } catch (Exception e) {
//            Log.e("AA", "DF"+e.getMessage());
//        }

//
//        holder.deletIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                data.setCount(1);
//                dataListProduct.remove(i);
//                notifyDataSetChanged();
//                deletCartItemInfoInterface.onDelet(delet);
//                onChangeQuantityListener.onChange();
//
//            }
//        });

        holder.imageDecrese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String tvQuantityText = holder.textViewQuantity.getText().toString();
                int quantInt = Integer.parseInt(tvQuantityText);
                quantInt = quantInt + 1;
                holder.textViewQuantity.setText(quantInt + "");
                dataListProduct.get(i).setCount(quantInt);
                notifyDataSetChanged();
                onChangeQuantityListener.onChange();
            }
        });


        holder.imageIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.textViewQuantity.getText().toString().equals("1") || !holder.textViewQuantity.getText().toString().equals("0")) {
                    String tvQuantityText = holder.textViewQuantity.getText().toString();
                    int quantInt = Integer.parseInt(tvQuantityText);
                    if (quantInt != 1) {
                        quantInt = quantInt - 1;
                    } else {
                        Toast.makeText(holder.itemView.getContext(), "cannotmakethisprocess", Toast.LENGTH_SHORT).show();
                    }
                    dataListProduct.get(i).setCount(quantInt);
                    holder.textViewQuantity.setText(quantInt + "");
                    notifyDataSetChanged();
                    onChangeQuantityListener.onChange();
                }
            }
        });

//        holder.deletIv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                data.setCount(1);
//                dataListProduct.remove(i);
//                notifyDataSetChanged();
//                deletCartItemInfoInterface.onDelet(delet);
//                onChangeQuantityListener.onChange();
//
//            }
//        });
    }


    @Override
    public int getItemCount() {
        return dataListProduct.size();
    }

    public void removeItem(int adapterPosition, Context context) {
        dataListProduct.remove(adapterPosition);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(adapterPosition);

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewType,textViewQuantity;
        ImageView imageViewItem;
        ImageButton imageDecrese,imageIncrease;
        LinearLayout linearLayout;
        public RelativeLayout viewBackground, viewForeground;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.layoutnoti);
            textViewType = itemView.findViewById(R.id.txtType);
            imageViewItem = itemView.findViewById(R.id.img);

            textViewQuantity=itemView.findViewById(R.id.txtQuantity);
            viewBackground = itemView.findViewById(R.id.view_background);
            viewForeground = itemView.findViewById(R.id.view_foreground);

            imageDecrese=itemView.findViewById(R.id.img_decrease);
            imageIncrease=itemView.findViewById(R.id.img_increase);
        }

//    public String getAllPrice() {
//        int countProduct = 0;
//
//        priceOffer=0;
//        double totalPrice = 0;
//        for (int i = 0; i < dataListProduct.size(); i++) {
//
//            if (dataListProduct.get(i).getCount() == null) {
//                dataListProduct.get(i).setCount(1);
//                try {
//                    countProduct = Integer.parseInt(dataListProduct.get(i).getCount().toString());
//
////                    if(dataListProduct.get(i).getOffer()!=null){
////                        priceOffer=dataListProduct.get(i).getOffer();
////                    }else {
////                        priceOffer=(Double) dataListProduct.get(i).getPrice();
////                    }
//                    totalPrice += (countProduct * priceOffer);
//                } catch (Exception e) {
//                    Log.e("ssAA", e.getMessage());
//                }
//            } else {
//                try {
//                    countProduct = Integer.parseInt(dataListProduct.get(i).getCount().toString());
//
////                    if(dataListProduct.get(i).getOffer()!=null){
////                        priceOffer=dataListProduct.get(i).getOffer();
////                    }else {
////                        priceOffer=(Double) dataListProduct.get(i).getPrice();
////                    }
//                    totalPrice += (countProduct * priceOffer);
//                } catch (Exception e) {
//                    Log.e("ssAA2", e.getMessage());
//                }
//            }
//            Log.e("SAAWWW",i+" "+priceOffer);
//        }
//
//        return totalPrice + "";
//    }
//
//    public String getAllQuantity() {
//        int totalQuant = 0;
//        for (int i = 0; i < dataListProduct.size(); i++) {
//            if (dataListProduct.get(i).getCount() == null) {
//                dataListProduct.get(i).setCount(1);
//                totalQuant += (Integer.parseInt(dataListProduct.get(i).getCount().toString()));
//            } else {
//                totalQuant += (Integer.parseInt(dataListProduct.get(i).getCount().toString()));
//            }
//        }
//        return "( " + totalQuant + " items ): ";
//    }

    }
}
