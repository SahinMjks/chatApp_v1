package com.example.chatapplicationversion13;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_SENDER = 1;
    private static final int VIEW_TYPE_RECEIVER = 2;

    private List<Message> mMessages;
    private Context mContext;
    private String mCurrentUser;

    public MessageAdapter(Context context, List<Message> messages, String currentUser) {
        mMessages = messages;
        mContext = context;
        mCurrentUser = currentUser;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = mMessages.get(position);
        if (message.getSender().equals(mCurrentUser)) {
            return VIEW_TYPE_SENDER;
        } else {
            return VIEW_TYPE_RECEIVER;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENDER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.send, parent, false);
            return new SenderViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.receive, parent, false);
            return new ReceiverViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = mMessages.get(position);
        if (holder instanceof SenderViewHolder) {
            ((SenderViewHolder) holder).messageTextView.setText(message.getMessage());
        } else if (holder instanceof ReceiverViewHolder) {
            ((ReceiverViewHolder) holder).messageTextView.setText(message.getMessage());
            ((ReceiverViewHolder) holder).authorTextView.setText(message.getSender());
        }
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder {
        public TextView messageTextView;

        public SenderViewHolder(View itemView) {
            super(itemView);

            messageTextView = itemView.findViewById(R.id.sent_message_id);
        }
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {
        public TextView messageTextView;
        public TextView authorTextView;

        public ReceiverViewHolder(View itemView) {
            super(itemView);

            messageTextView = itemView.findViewById(R.id.received_message_id);
        }
    }
}