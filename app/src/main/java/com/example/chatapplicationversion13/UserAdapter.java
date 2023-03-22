package com.example.chatapplicationversion13;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private Context mContext;
    private static List<User> mUsers;

    public UserAdapter(Context context, List<User> users) {
        this.mContext = context;
        this.mUsers = users;
    }
    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_layout, parent, false);
        final UserViewHolder holder = new UserViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                // Handle the click on the item at position
                Intent intent = new Intent(mContext, ChatActivity.class);

                // putting uid of user in extras
                mContext.startActivity(intent);

            }
        });
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mUsers.get(position);
        Toast.makeText(mContext, "Inside " + user.getUserName() + " clicked", Toast.LENGTH_SHORT).show();
        holder.bind(user);
        holder.mTextViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ChatActivity.class);

                // putting uid of user in extras
                intent.putExtra("name",user.getUserName());
                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void setUserList(List<User> userList) {
        this.mUsers= userList;
        notifyDataSetChanged();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Button mTextViewName;

        public UserViewHolder(View view) {
            super(view);
            Toast.makeText(view.getContext(), "Inside " + "UserViewHolder", Toast.LENGTH_SHORT).show();
            mTextViewName = view.findViewById(R.id.txt_name);
            view.setOnClickListener(this);
        }
        public void bind(User user) {
            mTextViewName.setText(user.getUserName());

        }
        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "Inside " + " clicked", Toast.LENGTH_SHORT).show();
            int position = getAdapterPosition(); // Get item position
            String item = String.valueOf(mUsers.get(position)); // Get item data
            Intent intent = new Intent(view.getContext(),ChatActivity.class);
            intent.putExtra("item_text", item); // Pass data to the new activity
            view.getContext().startActivity(intent);
        }
    }
}


