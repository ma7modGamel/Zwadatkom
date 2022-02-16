package com.safwa.zawadatkm_user.Adabter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.safwa.zawadatkm_user.Models.BaseModel;
import com.safwa.zawadatkm_user.Models.Datum;
import com.safwa.zawadatkm_user.Network.RetroWeb;
import com.safwa.zawadatkm_user.Network.ServiceApi;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.GlobalPrefrencies;
import com.safwa.zawadatkm_user.single.SingleProductActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsAdapter extends PagedListAdapter<Datum, ProductsAdapter.ProductsVmodel> implements CartOnlineAdapter.onChangeQuantityListener {
    private Context context;
    private static final DiffUtil.ItemCallback<Datum> item_COMPARATOR = new DiffUtil.ItemCallback<Datum>() {
        @Override
        public boolean areItemsTheSame(@NonNull Datum oldItem, @NonNull Datum newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Datum oldItem, @NonNull Datum newItem) {
            return oldItem == newItem;
        }
    };

    public ProductsAdapter(Context context) {
        super(item_COMPARATOR);
        this.context = context;
        globalPrefrencies=new GlobalPrefrencies(context);
    }

    @NonNull
    @Override
    public ProductsVmodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemproducthome, parent, false);
        return new ProductsVmodel(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final ProductsVmodel holder, @SuppressLint("RecyclerView") int position) {
        final Datum item = getItem(position);

        holder.txt1.setText(item.getName()+" ");
        holder.txt2.setText(item.getCategories().get(0).getCategory().getName() + " ");
        if(item.getLowestOption()!=null) {
            holder.txt3.setText(item.getLowestOption().getPrice() + " ر.س ");
        }else {
            if(item.getPrice()!=null) {
                holder.txt3.setText(item.getPrice() + " ر.س ");
            }else {
                holder.txt3.setVisibility(View.INVISIBLE);
            }
        }
        if(item.getImages().size()>0){

            String image = item.getImages().get(0).getImage();
            image.replaceAll("\'","");
            Glide.with(holder.itemView.getContext()).load(image)
                    .placeholder(R.drawable.logocolor).into(holder.imageViewback);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(holder.itemView.getContext(), SingleProductActivity.class);
                intent.putExtra("product_id",item.getId()+"");

                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.relativeLayoutAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //  AddToMyCart(item.getId(),item,holder,position);

                if(item.getCount()==null) {

                    item.setCount(1);
                }

                    AddToCartOnLine(item.getId(), item.getCount(), context,item.getLowestOption().getId());

            }
        });


    }

    private void AddToCartOnLine(Integer id, Integer count, Context context, Integer id_option) {

        RetroWeb.getClient().create(ServiceApi.class).addNewProduct(id_option+"",count+"",id_option+"","Bearer " +globalPrefrencies.getApi_token()).enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                Toast.makeText(context, ""+id, Toast.LENGTH_SHORT).show();
                //Toast.makeText(context, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {

            }
        });

    }

    GlobalPrefrencies globalPrefrencies;


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

    @Override
    public void onChange() {
//        if(dataArrayListProduct.size()>0) {
//            MainActivity.circleImageViewBandge.setVisibility(View.VISIBLE);
//            Log.e("SSSS",dataArrayListProduct.size()+" ");
//        }else {
//            MainActivity.circleImageViewBandge.setVisibility(View.GONE);
//            Log.e("SSSS2",dataArrayListProduct.size()+" ");
//        }
    }

    public class ProductsVmodel extends RecyclerView.ViewHolder {
        TextView txt1 , txt2 , txt3 ;
        ImageButton relativeLayoutAdd;
        ImageView imageViewback;
        public ProductsVmodel(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.namePro);
            txt2=itemView.findViewById(R.id.catPro);
            txt3=itemView.findViewById(R.id.pricePro);
            imageViewback=itemView.findViewById(R.id.imgPro);
            relativeLayoutAdd=itemView.findViewById(R.id.rlAddtocart);
        }
    }

}

