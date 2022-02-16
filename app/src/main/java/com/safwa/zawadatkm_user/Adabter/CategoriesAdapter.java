package com.safwa.zawadatkm_user.Adabter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
import com.safwa.zawadatkm_user.products.ProductsActivity;

public class CategoriesAdapter extends PagedListAdapter<CategoriesModel.Datum, CategoriesAdapter.CategoriesVmodel> {
    private Context context;

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

    public CategoriesAdapter(Context context) {
        super(item_COMPARATOR);
        this.context = context;
    }

    @NonNull
    @Override
    public CategoriesVmodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemcategorie, parent, false);
        return new CategoriesVmodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CategoriesVmodel holder, int position) {
        final CategoriesModel.Datum item = getItem(position);

        assert item != null;
        holder.txt1.setText(item.getName() + " ");
        Glide.with(holder.itemView.getContext()).load(item.getImage() + "")
                .placeholder(R.drawable.logocolor).into(holder.imageViewback);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("id",item.getId()+"");
                intent.putExtra("name",item.getName()+"");
                context.startActivity(intent);
            }
        });

            holder.layout.setBackgroundResource(R.drawable.edittextcardgray2);


    }

    public class CategoriesVmodel extends RecyclerView.ViewHolder {
        TextView txt1, txt2;
        ImageView imageViewback;
        LinearLayout layout;

        public CategoriesVmodel(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.txt1);
            imageViewback = itemView.findViewById(R.id.imgback);
            layout = itemView.findViewById(R.id.layback);
        }
    }

}

