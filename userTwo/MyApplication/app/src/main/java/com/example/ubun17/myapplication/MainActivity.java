package com.example.ubun17.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    DatabaseReference dbRef;
    EditText userInput, chatContent;
    Button sendButton;
    ListView listView;
    DatabaseReference mFirebaseRootRef;

    ArrayList<String> mMessages;

    private  ArrayAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        userInput = (EditText) findViewById(R.id.editText) ;
        chatContent = (EditText) findViewById(R.id.editTextTwo) ;
        sendButton = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listView);


        mFirebaseRootRef = FirebaseDatabase.getInstance().getReference();

        final DatabaseReference firebaseMessageRef = mFirebaseRootRef.child("messages2");

        mMessages = new ArrayList<>();


        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ObjectChat objectChat = new ObjectChat(userInput.getText().toString()
                        , chatContent.getText().toString());


                firebaseMessageRef.push().setValue(objectChat);
            }
        });

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this
                , android.R.layout.simple_list_item_1, mMessages);
        listView.setAdapter(adapter);

        firebaseMessageRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
              // String message = dataSnapshot.getValue(String.class);

                ObjectChat message = dataSnapshot.getValue(ObjectChat.class);
                String userContent = message.getUserName() + ": "+ message.getChat();
                mMessages.add(userContent);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
