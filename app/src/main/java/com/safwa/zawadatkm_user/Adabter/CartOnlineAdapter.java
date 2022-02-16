package com.safwa.zawadatkm_user.Adabter;


import static com.safwa.zawadatkm_user.home.CartFragment.updateCartModel;
import static com.safwa.zawadatkm_user.home.CartFragment.updateModel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
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


import com.bumptech.glide.Glide;
import com.safwa.zawadatkm_user.Models.BaseModel;
import com.safwa.zawadatkm_user.Models.CartListModel;
import com.safwa.zawadatkm_user.Models.UpdateCartModel;
import com.safwa.zawadatkm_user.Network.RetroWeb;
import com.safwa.zawadatkm_user.Network.ServiceApi;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.DeletCartItemInfoInterface;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartOnlineAdapter extends RecyclerView.Adapter<CartOnlineAdapter.ViewHolder> {


    public static List<CartListModel.Item> dataListProduct;
    DeletCartItemInfoInterface deletCartItemInfoInterface;
    onChangeQuantityListener onChangeQuantityListener;
    int delet = 10;

    public CartOnlineAdapter(List<CartListModel.Item> dataListProduct, DeletCartItemInfoInterface deletCartItemInfoInterface, onChangeQuantityListener onChangeQuantityListener) {

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

    double price = 0;
    int qty = 0;

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") int i) {

        CartListModel.Item item = dataListProduct.get(i);
        Log.e("idddd", item.getProductOptionId() + "");
        qty = item.getQty();
        UpdateCartModel.CartItem cartItem = new UpdateCartModel.CartItem();
        cartItem.setId(item.getProductOptionId());
        cartItem.setQty(item.getQty());


        updateCartModel.clear();
        updateCartModel.add(cartItem);
        updateModel.setCartItems(updateCartModel);



        Glide.with(holder.itemView.getContext()).load(item.getProductOption().getProductDetails().getImages().get(0).getImage() + "")
                .placeholder(R.drawable.logocolor).into(holder.imageViewItem);
        holder.textViewQuantity.setText(item.getQty() + "");
        holder.txtName.setText(item.getProductOption().getProductDetails().getName() + " ");
        price = Double.parseDouble(item.getPrice());
        holder.txtPrice.setText(item.getProductOption().getPrice()+ "");
        holder.textViewType.setText(item.getProductOption().getName()+ " ");


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
                dataListProduct.get(i).setQty(quantInt);

                notifyDataSetChanged();
                double allPrice = getAllPrice();
                holder.txtPrice.setText(allPrice + "");
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
                    dataListProduct.get(i).setQty(quantInt);
                    holder.textViewQuantity.setText(quantInt + "");
                    notifyDataSetChanged();
                    double allPrice = getAllPrice();
                    holder.txtPrice.setText(allPrice + "");
                    Log.e("All-", allPrice + "");
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

    GlobalPrefrencies globalPrefrencies;

    public void removeItem(String adapterPosition, Context context) {
        globalPrefrencies = new GlobalPrefrencies(context);
        RetroWeb.getClient().create(ServiceApi.class).deleteItem(adapterPosition, "Bearer " + globalPrefrencies.getApi_token()).enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                if (response.isSuccessful()) {
                    if (response.body().getSucess()) {
                        dataListProduct.remove(adapterPosition);
                        notifyItemRemoved(Integer.parseInt(adapterPosition));
                        if (response.body() != null) {
                            updateCartModel.remove(adapterPosition);
                            // notify the item removed by position
                            // to perform recycler view delete animations
                            // NOTE: don't call notifyDataSetChanged()
                            notifyItemRemoved(Integer.parseInt(adapterPosition));
                        }
                    } else {
                        Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {
                Toast.makeText(context, "لم يتم حذف العنصر" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewType, textViewQuantity, txtName, txtPrice;
        ImageView imageViewItem;
        ImageButton imageDecrese, imageIncrease;
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
            imageDecrese = itemView.findViewById(R.id.img_decrease);

            imageIncrease = itemView.findViewById(R.id.img_increase);
        }
    }

    public interface onChangeQuantityListener {
        void onChange();
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
