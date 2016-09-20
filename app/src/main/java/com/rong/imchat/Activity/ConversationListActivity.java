package com.rong.imchat.Activity;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rong.imchat.R;

/**
 * Created by llc_1 on 2016/9/19.
 */
public class ConversationListActivity extends FragmentActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversationlist);

        initView();
    }

    private void initView() {
        TextView mTitle = (TextView) findViewById(R.id.txt1);
        RelativeLayout mBack = (RelativeLayout) findViewById(R.id.back);
        TextView mSet = (TextView) findViewById(R.id.img3);

        mSet.setText("会话列表");

        mSet.setOnClickListener(this);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }


    @Override
    public void onClick(View v) {

    }
}
