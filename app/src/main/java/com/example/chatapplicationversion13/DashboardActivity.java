package com.example.chatapplicationversion13;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter<User, UserViewHolder> mAdapter;
    private DatabaseReference mDatabaseRef;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("User");

        mRecyclerView = findViewById(R.id.userRecyclerView);

        mRecyclerView.setClickable(false);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<User> options =
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(mDatabaseRef, User.class)
                        .build();

        mAdapter = new FirebaseRecyclerAdapter<User, UserViewHolder>(options) {


            @Override
            protected void onBindViewHolder(@NonNull UserViewHolder holder, int position, @NonNull User model) {
                holder.bind(model);
            }


            @NonNull
            @Override
            public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout, parent, false);
                // Set an OnClickListener on the item view
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Get the position of the clicked item
                        int position =mRecyclerView.getChildAdapterPosition(view);
                        if (position != RecyclerView.NO_POSITION) {
                            ObservableSnapshotArray<User> snapshots = getSnapshots();
                            User snapshot = snapshots.get(position);
                            String userId = snapshot.getUserName();
                            // Do something with the selected user ID
                            Intent intent = new Intent(DashboardActivity.this, ChatActivity.class);

                            // putting uid of user in extras
                            //intent.putExtra("name",user.getUserName());
                            view.getContext().startActivity(intent);
                        }
                    }
                });

                return new UserViewHolder(view);
            }
        };

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        public Button mTextViewName;

        public UserViewHolder(View view) {
            super(view);
            mTextViewName = view.findViewById(R.id.txt_name);

        }
        public void bind(User user) {
            mTextViewName.setText(user.getUserName());

        }





    }
}
