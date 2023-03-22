package com.example.chatapplicationversion13;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView chatRecyclerView;
    private EditText messagebox;
    private ImageView sendButton;
    private LinearLayoutManager mLayoutManager;
    private MessageAdapter mAdapter;

    private FirebaseUser mCurrentUser;
    private DatabaseReference mMessagesDatabase;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent=new Intent();
        String name=intent.getStringExtra("name");
        String uid=intent.getStringExtra("uid");

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle(name);

        chatRecyclerView=findViewById(R.id.chatRecyclerView);
        messagebox=findViewById(R.id.messageEditText);
        sendButton=findViewById(R.id.sendButton);

    }
}