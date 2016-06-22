package com.example.amol.letsbet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Debug;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.amol.letsbet.adapter.ChatAdapter;
import com.example.amol.letsbet.model.Message;
import com.example.amol.letsbet.service.ChatService;
import com.example.amol.letsbet.utils.Constants;
import com.example.amol.letsbet.utils.Util;

import java.util.ArrayList;
import java.util.List;


public class ChatActivity extends AppCompatActivity {

    private static final String MEMORY_MONITOR = "memory";
    private ResponseReceiver receiver;
    private Handler handler;
    private EditText etMessage;
    private ImageButton btSend;
    private RecyclerView rvChat;
    private ArrayList<Message> mMessages;
    private ChatAdapter chatRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        etMessage = (EditText) findViewById(R.id.messageEditText);
        btSend = (ImageButton) findViewById(R.id.sendMessageButton);
        rvChat = (RecyclerView) findViewById(R.id.rvChat);

        // Setting the LayoutManager.
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.scrollToPosition(0);

        //Set LayoutManager to RecyclerView
        rvChat.setLayoutManager(layoutManager);

        // initialize the adapter
        mMessages = new ArrayList<Message>();

        chatRecyclerAdapter = new ChatAdapter(mMessages);
        // attach the adapter to the RecyclerView
        rvChat.setAdapter(chatRecyclerAdapter);

        // When send button is clicked, create message object on Parse
        btSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendMessage(etMessage.getText().toString().trim());
            }
        });
    }

    private void sendMessage(String trim) {
        //TODO send message
    }

    @Override
    protected void onResume() {
        super.onResume();
        // create intent filter and register the broadcast receiver for the chat service
        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver();
        registerReceiver(receiver, filter);

        handler = new Handler();
        refreshMessages();

        // Run the runnable object defined every 100ms
        handler.postDelayed(runnable, 5000);

    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
        handler = null;
    }

    // Defines a runnable which is run every 100ms
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            chatRecyclerAdapter.addMessage(Util.getNewMessage());
            rvChat.scrollToPosition(chatRecyclerAdapter.getItemCount() - 1);
            monitorMemoryUsage();
            if (handler != null) {
                handler.postDelayed(this, 5000);
            }
        }
    };

    /**
     * Monitors heap memory usage
     * if memory usage increase more than 80% out of available heap request garbage collector.
     */
    private void monitorMemoryUsage() {
//		long availableHeap = Debug.getNativeHeapSize();
//		long freeHeap = Debug.getNativeHeapFreeSize();
//		long allocatedHeap = Debug.getNativeHeapAllocatedSize();

        long totalHeap = Runtime.getRuntime().totalMemory();
        long freeHeap = Runtime.getRuntime().freeMemory();
        long maxHeapAllowed = Runtime.getRuntime().maxMemory();
        long allocatedHeap = (totalHeap - freeHeap);
        float factor = Float.valueOf(allocatedHeap) / totalHeap;
        float heapUsagePercentage = (factor * 100);
        factor = Float.valueOf(totalHeap) / maxHeapAllowed;
        float heapPercentage = ((factor * 100));
        if (heapUsagePercentage > 80 || heapPercentage > 80) {
            Log.w(MEMORY_MONITOR, "Memory usage increased... Requesting garbage collector to free up some memory ");
            System.gc();
        } else {
            Log.i(MEMORY_MONITOR, "Available heap " + totalHeap + " Used heap " + allocatedHeap +  " - " + heapUsagePercentage + " % memory used" );
        }
    }


    private void refreshMessages() {
        // start intent service
        Intent msgIntent = new Intent(this, ChatService.class);
        //TODO send authentication and other required info.
        startService(msgIntent);
    }

    // Broadcast receiver that will receive data from service
    public class ResponseReceiver extends BroadcastReceiver {
        public static final String ACTION_RESP = "action_msgs_response";

        @Override
        public void onReceive(Context context, Intent intent) {
            ArrayList<Message> messages = (ArrayList<Message>) intent.getSerializableExtra(Constants.INTENT_MSGS_EXTRA);
            chatRecyclerAdapter.updateList(messages);
            rvChat.scrollToPosition(messages.size() - 1);
        }
    }
}
