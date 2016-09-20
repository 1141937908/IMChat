package com.rong.imchat.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rong.imchat.Adapter.ContactsAdapter;
import com.rong.imchat.R;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by llc_1 on 2016/9/19.
 */
public class ContactsActivity extends Activity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private ContactsAdapter mAdapter;

    /**
     * ids 收消息人的 id
     */
    String[] ids = {"user001","user002","user003"};
    List mLists = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        initView();
    }

    private void initView() {
        ListView mListView = (ListView) findViewById(R.id.list);
        TextView mTitle = (TextView) findViewById(R.id.txt1);
        RelativeLayout mBack = (RelativeLayout) findViewById(R.id.back);
        TextView mSet = (TextView) findViewById(R.id.img3);

        mSet.setText("讨论组");

        mAdapter = new ContactsAdapter(ContactsActivity.this, ids);
        mListView.setAdapter(mAdapter);

        for (int i=0;i<ids.length;i++){
            mLists.add(ids[i]);
        }

        mListView.setOnItemClickListener(this);
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
        /**
         * 创建讨论组会话并进入会话界面。
         * 讨论组创建成功后，会返回讨论组 id。
         *
         * @param context 应用上下文。
         * @param targetUserIds 要与之聊天的讨论组用户 Id 列表。
         * @param title 聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
         * @param callback 讨论组回调，成功时，返回讨论组 id。
         */
        if(RongIM.getInstance()!=null){
            RongIM.getInstance().createDiscussionChat(ContactsActivity.this, mLists, "success", new RongIMClient.CreateDiscussionCallback() {

                @Override
                public void onSuccess(String s) {

                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {

                }
            });
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (RongIM.getInstance() != null)
            //启动单聊
            RongIM.getInstance().startPrivateChat(ContactsActivity.this, ids[position], "title");
    }
}
