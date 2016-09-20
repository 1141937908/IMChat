package com.rong.imchat.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rong.imchat.App;
import com.rong.imchat.R;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * 登陆界面LoginActivity
 * Created by llc_1 on 2016/9/19.
 */
public class LoginActivity extends Activity implements View.OnClickListener{

    /**
     * 三个用户Token及一个切换TOKEN
     */
    private static final String Token1 = "xUMMnzafpj2zMs7Nnza9Jo8OMKposaixThIz3uNSGoQGtg81H+278JqgaTp8jMkROU5IUUPcpTILQZtGEdXb2w==";
    private static final String Token2 = "ANFQ7Ww1wAgG8LhS83BV7Y8OMKposaixThIz3uNSGoQGtg81H+278B7ZVhAfOOssOU5IUUPcpTLXTC3mC7IEvw==";
    private static final String Token3 = "2n39ZjsSQxCJlOQmS+noAo8OMKposaixThIz3uNSGoQGtg81H+278KhbqDK9MoCrOU5IUUPcpTLHc9DByhUmZw==";
    private static String TOKEN;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        context=this;
    }

    /**
     * 初始化界面各个组件
     */
    private void initView() {
        Button btn1= (Button) findViewById(R.id.login_btn1);
        Button btn2= (Button) findViewById(R.id.login_btn2);
        Button btn3= (Button) findViewById(R.id.login_btn3);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn1:
                TOKEN=Token1;
                connect(Token1);
                break;
            case R.id.login_btn2:
                TOKEN=Token2;
                connect(Token2);
                break;
            case R.id.login_btn3:
                TOKEN=Token3;
                connect(Token3);
                break;
        }
    }


    /**
     * 连接融云
     *
     * @param token
     */
    private void connect(String token){
        if(getApplicationInfo().packageName.equals(App.getCurProcessName(getApplicationContext()))){
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                //Token错误
                @Override
                public void onTokenIncorrect() {
                    Log.d("LoginActivity", "--onTokenIncorrect");
                }

                /**
                 * 连接融云成功
                 *
                 * @param userid
                 */
                @Override
                public void onSuccess(String userid) {
                    //提示登陆成功
                    Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();
                    Log.e("LoginActivity","--onSuccess"+userid);
                    startActivity(new Intent(context, MainActivity.class));
                    finish();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 *                  http://www.rongcloud.cn/docs/android.html#常见错误码
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Log.d("LoginActivity", "--onError" + errorCode);
                }
            });
        }
    }

    public static String getTargetUserId(){
        if(TOKEN.equals(Token1))
            return "user002";
        if (TOKEN.equals(Token2))
            return "user001";
        if (TOKEN.equals(Token3))
            return "user003";
        return null;
    }

}
