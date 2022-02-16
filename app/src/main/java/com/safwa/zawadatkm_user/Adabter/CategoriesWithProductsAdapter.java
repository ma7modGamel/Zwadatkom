package com.safwa.zawadatkm_user.Adabter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.safwa.zawadatkm_user.Models.categories.CategoriesModel;
import com.safwa.zawadatkm_user.R;


public class CategoriesWithProductsAdapter extends PagedListAdapter<CategoriesModel.Datum, CategoriesWithProductsAdapter.CategoriesVmodel> {
    private Context context;
    ChangeProducts changeProducts;
    private static final DiffUtil.ItemCallback<CategoriesModel.Datum> item_COMPARATOR = new DiffUtil.ItemCallback<CategoriesModel.Datum>() {
        @Override
        public boolean areItemsTheSame(@NonNull CategoriesModel.Datum oldItem, @NonNull CategoriesModel.Datum newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull CategoriesModel.Datum oldItem, @NonNull CategoriesModel.Datum newItem) {
            return oldItem == newItem;
        }
    };

    public CategoriesWithProductsAdapter(Context context,ChangeProducts changeProducts) {
        super(item_COMPARATOR);
        this.context = context;
        this.changeProducts=changeProducts;

    }

    @NonNull
    @Override
    public CategoriesVmodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcategorie, parent, false);
        return new CategoriesVmodel(view);
    }
int lastCheckedPosition=-1;
    @Override
    public void onBindViewHolder(@NonNull final CategoriesVmodel holder, int position) {
        final CategoriesModel.Datum item = getItem(position);

        assert item != null;
        holder.txt1.setText(item.getName()+" ");
        Glide.with(holder.itemView.getContext()).load(item.getImage()+"")
              .placeholder(R.drawable.logocolor).into(holder.imageViewback);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastCheckedPosition=position;
                notifyDataSetChanged();
                changeProducts.OnChangeProducts(item.getId()+"");
            }
        });
        if(lastCheckedPosition==position){
            holder.layout.setBackgroundResource(R.drawable.cardgraybordergreenaddress16);
        }
        else
        {

                holder.layout.setBackgroundResource(R.drawable.edittextcardgray2);


        }

    }

    public class CategoriesVmodel extends RecyclerView.ViewHolder {
        TextView txt1 , txt2 ;
        ImageView imageViewback;
        LinearLayout layout;
        public CategoriesVmodel(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.txt1);
            imageViewback=itemView.findViewById(R.id.imgback);
            layout=itemView.findViewById(R.id.layback);

        }
    }
    public interface ChangeProducts{
         void OnChangeProducts(String id);
    }
}

