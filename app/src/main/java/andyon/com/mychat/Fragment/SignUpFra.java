package andyon.com.mychat.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import andyon.com.mychat.R;
import andyon.com.mychat.base.BaseFragment;
import andyon.com.mychat.netcore.ChatManager;
import andyon.com.mychat.netcore.MyObjectCallback;

/**
 * Created by app on 15/6/7.
 * 登录页面的Fragment
 */
public class SignUpFra extends BaseFragment implements View.OnClickListener {

    private String TAG = "SignUpFra";
    private EditText etAccount;
    private EditText etPwd;
    private Button btnSignUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fra_sign_up, container, false);
        initView(view);
        initEvent();
        return view;
    }

    private void initView(View view) {
        etAccount = (EditText) view.findViewById(R.id.et_sign_up_account);
        etPwd = (EditText) view.findViewById(R.id.et_sign_up_pwd);
        btnSignUp = (Button) view.findViewById(R.id.btn_sign_up);

    }

    private void initEvent() {
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnSignUp) {
            doSignUp();
        }
    }

    private void doSignUp() {
        Context context = getActivity();
        if (context == null) {
            return;
        }
        String account = etAccount.getText().toString().trim();
        if (TextUtils.isEmpty(account)) {
            Toast.makeText(context, "用户名为空", Toast.LENGTH_SHORT).show();
            return;
        }
        String password = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(context, "密码为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "http://10.0.2.2:8080/ChatServer/register";
        Map<String, String> parameters = new HashMap<>();
        parameters.put("account", account);
        parameters.put("password", password);

        ChatManager.getInstance().send(url, null, parameters, new MyObjectCallback() {
            @Override
            public void onSuccess(Object data) {
                if(data !=null){
                   Log.d("Andyon", data +"");
                }
            }

            @Override
            public void onFailure(int errorCode, String errorString) {
               Log.d("Andyon", "访问网路失败" +errorCode +":" + errorString);
            }
        });
    }
}
