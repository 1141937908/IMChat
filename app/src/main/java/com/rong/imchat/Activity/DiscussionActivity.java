package com.rong.imchat.Activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;

import com.rong.imchat.R;

/**
 * Created by llc_1 on 2016/9/19.
 */
public class DiscussionActivity extends FragmentActivity implements View.OnClickListener {
    private Button button1, button2, button3;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        context = this;
        initView();
    }

    private void initView() {
        button1 = (Button) findViewById(R.id.discussion_button1);
        button2 = (Button) findViewById(R.id.discussion_button2);
        button3 = (Button) findViewById(R.id.discussion_button3);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.discussion_button1:

        }
    }
}
