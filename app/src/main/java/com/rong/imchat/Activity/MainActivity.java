package com.rong.imchat.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.rong.imchat.R;
import com.rong.imchat.Users;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

import static com.rong.imchat.Activity.LoginActivity.getTargetUserId;

public class MainActivity extends Activity implements View.OnClickListener, RongIM.UserInfoProvider {

    private static String User;
    private List<Users> userIdList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        Button btn1 = (Button) findViewById(R.id.bt2);
        Button btn2 = (Button) findViewById(R.id.bt3);
        Button btn3 = (Button) findViewById(R.id.bt4);
        Button btn4 = (Button) findViewById(R.id.bt5);
        Button btn5 = (Button) findViewById(R.id.bt6);
        ImageView mImageView = (ImageView) findViewById(R.id.img1);
        TextView mTitle = (TextView) findViewById(R.id.txt1);

        mImageView.setVisibility(View.GONE);
        mTitle.setText("主界面");

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt2:
                if (RongIM.getInstance() != null)
                /**
                 * 启动单聊界面。
                 *
                 * @param context      应用上下文。
                 * @param targetUserId 要与之聊天的用户 Id。
                 * @param title        聊天的标题，如果传入空值，则默认显示与之聊天的用户名称。
                 */
                    RongIM.getInstance().startPrivateChat(MainActivity.this, getTargetUserId(), null);
                break;
            case R.id.bt3:
                if (RongIM.getInstance() != null)
                    //启动会话列表界面
                    RongIM.getInstance().startConversationList(MainActivity.this);
                break;
            case R.id.bt4:
                if (RongIM.getInstance() != null)
                    //启动聚合会话列表界面
                    RongIM.getInstance().startSubConversationList(MainActivity.this, Conversation.ConversationType.GROUP);
                break;
            case R.id.bt5:
                //
                startActivity(new Intent(MainActivity.this, ContactsActivity.class));
                break;
            case R.id.bt6:
                //
                RongIM.getInstance().startConversation(MainActivity.this, Conversation.ConversationType.CHATROOM, "1001","标题");
                break;
        }
    }

    /**
     * 初始化用户信息
     */
    private void initUserInfo() {
        userIdList = new ArrayList<>();
        userIdList.add(new Users("user001", "user001", "http://v1.qzone.cc/avatar/201408/24/19/20/53f9ca6a38f51240.jpg!200x200.jpg"));
        userIdList.add(new Users("user002", "user002", "http://v1.qzone.cc/avatar/201408/24/19/20/53f9ca6985671899.jpg!200x200.jpg"));
        userIdList.add(new Users("user003", "user003", "http://v1.qzone.cc/avatar/201408/24/19/20/53f9ca71ef61d211.jpg!200x200.jpg"));
        RongIM.setUserInfoProvider(this, true);
    }

    /**
     * 获得用户信息
     *
     * @param s
     * @return
     */
    public UserInfo getUserInfo(String s) {
        for (Users i : userIdList) {
            if (i.getUserId().equals(s)) {
                Log.e("MainActivity", i.getPortraitUri());
                return new UserInfo(i.getUserId(), i.getName(), Uri.parse(i.getPortraitUri()));
            }
        }
        Log.e("MainActivity", "UserId is" + s);
        return null;
    }


    /**
     * 退出应用
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == event.getKeyCode()) {
            final AlertDialog.Builder alterDialog = new AlertDialog.Builder(this);
            alterDialog.setMessage("确定退出应用？");
            alterDialog.setCancelable(true);

            alterDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (RongIM.getInstance() != null)
                        RongIM.getInstance().disconnect(true);

                    android.os.Process.killProcess(Process.myPid());
                }
            });
            alterDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            alterDialog.show();
        }
        return false;
    }
}
