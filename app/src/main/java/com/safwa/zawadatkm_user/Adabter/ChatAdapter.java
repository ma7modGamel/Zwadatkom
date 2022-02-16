package com.safwa.zawadatkm_user.Adabter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.libraries.places.internal.m;
import com.safwa.zawadatkm_user.Models.ChatListModel;
import com.safwa.zawadatkm_user.R;
import com.safwa.zawadatkm_user.Utils.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatVholder> {


    ArrayList<ChatListModel.Message> messages;
    Context context;

    public ChatAdapter(Context context, ArrayList<ChatListModel.Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @NonNull
    @Override
    public ChatVholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemchat, parent, false);
        return new ChatVholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatVholder holder, int position) {

        ChatListModel.Message message = messages.get(position);
        //boolean myMsg = messages.get(position) ;//Just a dummy check
        //to simulate whether it me or other sender

        boolean myMsg = false;

        if(message.getAgentId()==null){
            myMsg=true;
        }
        setAlignment(holder, myMsg);
        holder.txtMessage.setText(message.getMessage());
        String date=message.getCreatedAt()+"";
        Log.e("AAAAAAA",date);
        SimpleDateFormat spf=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        Date newDate= null;
        try {
            newDate = spf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        spf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        date = spf.format(newDate);

        holder.txtInfo.setText(Utils.timeAgo(date+""));
        //holder.txtInfo.setText(message.getCreatedAt() + "");

    }


    public ChatListModel.Message getItem(int position) {
        if (messages != null) {
            return messages.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (messages != null) {
            return messages.size();
        } else {
            return 0;
        }
    }


    public void add(ChatListModel.Message message) {
        messages.add(message);
    }

    public void add(List<ChatListModel.Message> messages) {
        messages.addAll(messages);
    }

    private void setAlignment(ChatVholder holder, boolean isMe) {
        if (!isMe) {
            holder.txtMessage.setTextColor(ContextCompat.getColor(context, R.color.white));
            holder.contentWithBG.setBackgroundResource(R.drawable.in_message_bg);

            LinearLayout.LayoutParams layoutParams =
                    (LinearLayout.LayoutParams) holder.contentWithBG.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            holder.contentWithBG.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams lp =
                    (RelativeLayout.LayoutParams) holder.content.getLayoutParams();
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.content.setLayoutParams(lp);
            layoutParams = (LinearLayout.LayoutParams) holder.txtMessage.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            holder.txtMessage.setLayoutParams(layoutParams);

            layoutParams = (LinearLayout.LayoutParams) holder.txtInfo.getLayoutParams();
            layoutParams.gravity = Gravity.RIGHT;
            holder.txtInfo.setLayoutParams(layoutParams);
        } else {
            holder.contentWithBG.setBackgroundResource(R.drawable.out_message_bg);

            LinearLayout.LayoutParams layoutParams =
                    (LinearLayout.LayoutParams) holder.contentWithBG.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            holder.contentWithBG.setLayoutParams(layoutParams);

            RelativeLayout.LayoutParams lp =
                    (RelativeLayout.LayoutParams) holder.content.getLayoutParams();
            lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
            lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
            holder.content.setLayoutParams(lp);
            layoutParams = (LinearLayout.LayoutParams) holder.txtMessage.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            holder.txtMessage.setLayoutParams(layoutParams);

            layoutParams = (LinearLayout.LayoutParams) holder.txtInfo.getLayoutParams();
            layoutParams.gravity = Gravity.LEFT;
            holder.txtInfo.setLayoutParams(layoutParams);
        }
    }


    public static class ChatVholder extends RecyclerView.ViewHolder {
        TextView txtMessage;
        TextView txtInfo;
        LinearLayout content;
        LinearLayout contentWithBG;

        public ChatVholder(@NonNull View v) {
            super(v);
            txtMessage = (TextView) v.findViewById(R.id.txtMessage);
            content = (LinearLayout) v.findViewById(R.id.content);
            contentWithBG = (LinearLayout) v.findViewById(R.id.contentWithBackground);
            txtInfo = (TextView) v.findViewById(R.id.txtInfo);
        }
    }
}
//public class ChatAdapter extends BaseAdapter {
//
//private final List<ChatListModel.Message> chatMessages;
//private Context context;
//
//public ChatAdapter(Context context, List<ChatListModel.Message> chatMessages) {
//        this.context = context;
//        this.chatMessages = chatMessages;
//        }
//
//@Override
//public int getCount() {
//        if (chatMessages != null) {
//        return chatMessages.size();
//        } else {
//        return 0;
//        }
//        }
//
//@Override
//public ChatListModel.Message getItem(int position) {
//        if (chatMessages != null) {
//        return chatMessages.get(position);
//        } else {
//        return null;
//        }
//        }
//
//@Override
//public long getItemId(int position) {
//        return position;
//        }
//
//@Override
//public View getView(final int position, View convertView, ViewGroup parent) {
//        ViewHolder holder;
//    ChatListModel.Message chatMessage = getItem(position);
//        LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        if (convertView == null) {
//        convertView = vi.inflate(R.layout.itemchat, null);
//        holder = createViewHolder(convertView);
//        convertView.setTag(holder);
//        } else {
//        holder = (ViewHolder) convertView.getTag();
//        }
//
//        boolean myMsg = false;
//
//        if(chatMessage.getAgentId()==null){
//            myMsg=true;
//        }
//        //Just a dummy check
//        //to simulate whether it me or other sender
//        setAlignment(holder, myMsg);
//        holder.txtMessage.setText(chatMessage.getMessage());
//        holder.txtInfo.setText(chatMessage.getCreatedAt());
//
//        return convertView;
//        }
//
//public void add(ChatListModel.Message message) {
//        chatMessages.add(message);
//        }
//
//public void add(List<ChatListModel.Message> messages) {
//        chatMessages.addAll(messages);
//        }
//
//private void setAlignment(ViewHolder holder, boolean isMe) {
//        if (!isMe) {
//        holder.contentWithBG.setBackgroundResource(R.drawable.in_message_bg);
//
//        LinearLayout.LayoutParams layoutParams =
//        (LinearLayout.LayoutParams) holder.contentWithBG.getLayoutParams();
//        layoutParams.gravity = Gravity.RIGHT;
//        holder.contentWithBG.setLayoutParams(layoutParams);
//
//        RelativeLayout.LayoutParams lp =
//        (RelativeLayout.LayoutParams) holder.content.getLayoutParams();
//        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, 0);
//        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//        holder.content.setLayoutParams(lp);
//        layoutParams = (LinearLayout.LayoutParams) holder.txtMessage.getLayoutParams();
//        layoutParams.gravity = Gravity.RIGHT;
//        holder.txtMessage.setLayoutParams(layoutParams);
//
//        layoutParams = (LinearLayout.LayoutParams) holder.txtInfo.getLayoutParams();
//        layoutParams.gravity = Gravity.RIGHT;
//        holder.txtInfo.setLayoutParams(layoutParams);
//        } else {
//        holder.contentWithBG.setBackgroundResource(R.drawable.out_message_bg);
//
//        LinearLayout.LayoutParams layoutParams =
//        (LinearLayout.LayoutParams) holder.contentWithBG.getLayoutParams();
//        layoutParams.gravity = Gravity.LEFT;
//        holder.contentWithBG.setLayoutParams(layoutParams);
//
//        RelativeLayout.LayoutParams lp =
//        (RelativeLayout.LayoutParams) holder.content.getLayoutParams();
//        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
//        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
//        holder.content.setLayoutParams(lp);
//        layoutParams = (LinearLayout.LayoutParams) holder.txtMessage.getLayoutParams();
//        layoutParams.gravity = Gravity.LEFT;
//        holder.txtMessage.setLayoutParams(layoutParams);
//
//        layoutParams = (LinearLayout.LayoutParams) holder.txtInfo.getLayoutParams();
//        layoutParams.gravity = Gravity.LEFT;
//        holder.txtInfo.setLayoutParams(layoutParams);
//        }
//        }
//
//private ViewHolder createViewHolder(View v) {
//        ViewHolder holder = new ViewHolder();
//        holder.txtMessage = (TextView) v.findViewById(R.id.txtMessage);
//        holder.content = (LinearLayout) v.findViewById(R.id.content);
//        holder.contentWithBG = (LinearLayout) v.findViewById(R.id.contentWithBackground);
//        holder.txtInfo = (TextView) v.findViewById(R.id.txtInfo);
//        return holder;
//        }
//
//private static class ViewHolder {
//    public TextView txtMessage;
//    public TextView txtInfo;
//    public LinearLayout content;
//    public LinearLayout contentWithBG;
//}
//}