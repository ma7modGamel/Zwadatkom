package com.safwa.zawadatkm_user.Adabter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.Gson;
import com.safwa.zawadatkm_user.Models.detailsorders.OrderDetailsModel;
import com.safwa.zawadatkm_user.Models.orders.OrdersListModel;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.Utils;
import com.safwa.zawadatkm_user.single.order.SingleOrderActivity;

import de.hdodenhof.circleimageview.CircleImageView;

public class OrdersAdapter extends PagedListAdapter<OrdersListModel.Datum, OrdersAdapter.OrdersVmodel> {
    private Context context;
    private static final DiffUtil.ItemCallback<OrdersListModel.Datum> item_COMPARATOR = new DiffUtil.ItemCallback<OrdersListModel.Datum>() {
        @Override
        public boolean areItemsTheSame(@NonNull OrdersListModel.Datum oldItem, @NonNull OrdersListModel.Datum newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull OrdersListModel.Datum oldItem, @NonNull OrdersListModel.Datum newItem) {
            return oldItem == newItem;
        }
    };

    public OrdersAdapter(Context context) {
        super(item_COMPARATOR);
        this.context = context;
    }

    @NonNull
    @Override
    public OrdersVmodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemorderposition, parent, false);
        return new OrdersVmodel(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final OrdersVmodel holder, int position) {
        final OrdersListModel.Datum item = getItem(position);
        Log.e("PXxXxX", item.getStatus().getId() + " ");
//

        if(position==1) {

            Log.e("mmmm",new Gson().toJson(item));
            item.getStatus().setId(1);
//        }else if(position==2){
//            item.getStatus().setId(2);
//        }else if(position==3){
//            item.getStatus().setId(3);
//        }else if(position==4){
//            item.getStatus().setId(4);
//        }else {
//            item.getStatus().setId(0);
        }
        setupStatus(holder, item.getStatus(), item);
        String date = item.getCreated() + "";
//        Log.e("AAAAAAA",date);
//        SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
//        Date newDate= null;
//        try {
//            newDate = spf.parse(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        spf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
//        date = spf.format(newDate);

        holder.txtDate.setText(Utils.timeAgo(date + ""));
        holder.txtIdOrder.setText(item.getId() + "");
//        holder.txt2.setText(item.getPromotion().getPromotionValue()+" ");
//        Glide.with(holder.itemView.getContext()).load(item.getImage())
//                .placeholder(R.drawable.logocolor).into(holder.imageViewback);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,SingleOrderActivity.class);
                intent.putExtra("id",item.getId()+"");
                context.startActivity(intent);
            }
        });

    }

    @SuppressLint("ResourceAsColor")
    private void setupStatus(OrdersVmodel holder, OrdersListModel.Status status, OrdersListModel.Datum item) {
        if (status.getId()+1 == 1) {
            holder.txtState1.setVisibility(View.VISIBLE);
            holder.txtState2.setVisibility(View.INVISIBLE);
            holder.txtState3.setVisibility(View.INVISIBLE);
            holder.txtState4.setVisibility(View.INVISIBLE);
            holder.txtState1.setText("تم الطلب");//item.getStatus().getName() + " ");


            holder.txtState1.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.purple_200));

            holder.c1.setImageResource(R.color.purple_200);
            holder.c2.setImageResource(R.color.white);
            holder.c3.setImageResource(R.color.white);
            holder.c4.setImageResource(R.color.white);

            holder.space1.setBackgroundResource(R.color.white);
            holder.space2.setBackgroundResource(R.color.white);
            holder.space3.setBackgroundResource(R.color.white);


        } else if (status.getId()+1 == 2) {
            holder.txtState1.setVisibility(View.INVISIBLE);
            holder.txtState2.setVisibility(View.VISIBLE);
            holder.txtState3.setVisibility(View.INVISIBLE);
            holder.txtState4.setVisibility(View.INVISIBLE);
            holder.txtState2.setText("تم استلام الطلب");//item.getStatus().getName() + " ");
            holder.txtState2.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.purple_500));
            holder.c1.setImageResource(R.color.purple_500);
            holder.c2.setImageResource(R.color.purple_500);
            holder.c3.setImageResource(R.color.white);
            holder.c4.setImageResource(R.color.white);

            holder.space1.setBackgroundResource(R.color.purple_500);
            holder.space2.setBackgroundResource(R.color.white);
            holder.space3.setBackgroundResource(R.color.white);


        } else if (status.getId()+1 == 3) {
            holder.txtState3.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.purple_700));

            holder.txtState1.setVisibility(View.INVISIBLE);
            holder.txtState2.setVisibility(View.INVISIBLE);
            holder.txtState3.setVisibility(View.VISIBLE);
            holder.txtState4.setVisibility(View.INVISIBLE);
            holder.txtState3.setText("جاري التوصيل");//item.getStatus().getName() + " ");

            holder.c1.setImageResource(R.color.purple_700);
            holder.c2.setImageResource(R.color.purple_700);
            holder.c3.setImageResource(R.color.purple_700);
            holder.c4.setImageResource(R.color.white);

            holder.space1.setBackgroundResource(R.color.purple_700);
            holder.space2.setBackgroundResource(R.color.purple_700);
            holder.space3.setBackgroundResource(R.color.white);


        } else if (status.getId()+1 == 4) {
            holder.txtState1.setVisibility(View.INVISIBLE);
            holder.txtState2.setVisibility(View.INVISIBLE);
            holder.txtState3.setVisibility(View.INVISIBLE);
            holder.txtState4.setVisibility(View.VISIBLE);
            holder.txtState4.setTextColor(holder.itemView.getContext().getResources().getColor(R.color.green));

            holder.txtState4.setText("تم التوصيل بنجاح");//item.getStatus().getName() + " ");

        }
    }

    public static class OrdersVmodel extends RecyclerView.ViewHolder {
        TextView txtIdOrder, txtState1, txtState2, txtState3, txtState4, txtDate;

        CircleImageView c1, c2, c3, c4;
        View space1, space2, space3;

        public OrdersVmodel(@NonNull View itemView) {
            super(itemView);
            txtIdOrder = itemView.findViewById(R.id.txtNumOrder);
            txtState1 = itemView.findViewById(R.id.stats1);
            txtState1 = itemView.findViewById(R.id.stats1);
            txtState2 = itemView.findViewById(R.id.stats2);
            txtState3 = itemView.findViewById(R.id.stats3);
            txtState4 = itemView.findViewById(R.id.stats4);

            space1 = itemView.findViewById(R.id.space1);
            space2 = itemView.findViewById(R.id.space2);
            space3 = itemView.findViewById(R.id.space3);
            c1 = itemView.findViewById(R.id.cir1);
            c2 = itemView.findViewById(R.id.cir2);
            c3 = itemView.findViewById(R.id.cir3);
            c4 = itemView.findViewById(R.id.cir4);
            txtDate = itemView.findViewById(R.id.txtDate);

//            imageViewback=itemView.findViewById(R.id.imgback);

        }
    }
}
