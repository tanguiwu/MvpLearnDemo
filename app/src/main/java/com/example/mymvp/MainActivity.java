package com.example.mymvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mymvp.presenter.PresenterCallbackView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ResultCallbackViewListener {

    public static String TAG = "tgw" + MainActivity.class.getName();

    private TextView tvResult;
    private Button btSucceed, btFail, btException;
    private EditText editContent;


    private PresenterCallbackView presenterCallbackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tv_result);
        btSucceed = findViewById(R.id.bt_succeed);
        btFail = findViewById(R.id.bt_fail);
        btException = findViewById(R.id.bt_exception);
        editContent = findViewById(R.id.edit_content);
        presenterCallbackView = new PresenterCallbackView();
        presenterCallbackView.setPresenterCallbackView(this);
        btSucceed.setOnClickListener(this);
        btFail.setOnClickListener(this);
        btException.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.bt_succeed) {
            presenterCallbackView.setType(1);
        } else if (viewId == R.id.bt_fail) {
            presenterCallbackView.setType(2);
        }
        if (viewId == R.id.bt_exception) {
            presenterCallbackView.setType(3);
        }
        //利用p层将modle层的数据返回--注意顺序先传输在调用
        presenterCallbackView.onTransportDataListener(MainActivity.this, editContent.getText().toString());

        presenterCallbackView.onPresenterCallbackView(MainActivity.this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(String data) {
        tvResult.setText(data);
    }

    @Override
    public void showFailureMessage(String msg) {
        tvResult.setText(msg);
    }

    @Override
    public void showErrorMessage(String msg) {
        tvResult.setText(msg);
    }

    @Override
    public void showComplete(String msg) {
        Log.d(TAG, "showComplete: "+msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterCallbackView.detachView();
    }
}
