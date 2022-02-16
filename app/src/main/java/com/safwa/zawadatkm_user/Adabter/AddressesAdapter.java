package com.safwa.zawadatkm_user.Adabter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.safwa.zawadatkm_user.Models.address.AddressListModel;
import com.safwa.zawadatkm_user.R;


public class AddressesAdapter extends PagedListAdapter<AddressListModel.Datum, AddressesAdapter.AddressVmodel> {
    private Context context;
    public int idAddress=0;
    private static final DiffUtil.ItemCallback<AddressListModel.Datum> item_COMPARATOR = new DiffUtil.ItemCallback<AddressListModel.Datum>() {
        @Override
        public boolean areItemsTheSame(@NonNull AddressListModel.Datum oldItem, @NonNull AddressListModel.Datum newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull AddressListModel.Datum oldItem, @NonNull AddressListModel.Datum newItem) {
            return oldItem == newItem;
        }
    };

    public AddressesAdapter(Context context) {
        super(item_COMPARATOR);
        this.context = context;
    }

    @NonNull
    @Override
    public AddressVmodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_address, parent, false);
        return new AddressVmodel(view);
    }
    private int lastCheckedPosition = 0;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final AddressVmodel holder, int position) {
        final AddressListModel.Datum item = getItem(position);

//        if(position==0){
//            holder.linearLayout.setBackgroundResource(R.drawable.cardgraybordergreenaddress);
//        }

        holder.radioButton.setChecked(position == lastCheckedPosition);
        holder.txt1.setText(item.getCountry()+" - "+ item.getCity());
        holder.txt2.setText(item.getAddressType()+" ");
        holder.txt3.setText(item.getAddress()+" ");
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastCheckedPosition=position;
                idAddress=item.getId();

                notifyDataSetChanged();
            }
        });
        if(lastCheckedPosition==position){
            holder.linearLayout.setBackgroundResource(R.drawable.cardgraybordergreenaddress);
            idAddress=item.getId();

        }
        else
        {
            holder.linearLayout.setBackgroundResource(R.drawable.edittextcardgray);
        }

    }

    public class AddressVmodel extends RecyclerView.ViewHolder {
        TextView txt1 , txt2,txt3 ;

        LinearLayout linearLayout;
        RadioButton radioButton;
        public AddressVmodel(@NonNull View itemView) {
            super(itemView);
            txt1=itemView.findViewById(R.id.tv_Country);
            radioButton=itemView.findViewById(R.id.rb);
            txt2=itemView.findViewById(R.id.tv_typeAddress);
            txt3=itemView.findViewById(R.id.tv_moreAddress);
            radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int copyOfLastCheckedPosition = lastCheckedPosition;
                    lastCheckedPosition = getAdapterPosition();
                    notifyItemChanged(copyOfLastCheckedPosition);
                    notifyItemChanged(lastCheckedPosition);
                }
            });
            linearLayout=itemView.findViewById(R.id.lay);
        }
    }
}

