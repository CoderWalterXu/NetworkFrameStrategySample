package com.xlh.strategy.networkframe;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.xlh.strategy.networkframe.bean.WeatherBean;
import com.xlh.strategy.networkframe.http.HttpCallBack;
import com.xlh.strategy.networkframe.http.HttpHelper;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String url = "http://v.juhe.cn/historyWeather/citys";

    Button btnRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRequest = findViewById(R.id.btn_request);
        btnRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });

    }

    private void sendRequest() {
        Map<String, Object> params = new HashMap<>();
        params.put("province_id", 1);
        HttpHelper.getInstance().post(url, params, new HttpCallBack<WeatherBean>() {
            @Override
            public void onJsonSuccess(WeatherBean weatherBean) {
                Log.e("Test", "请求结果---->" + weatherBean.toString());
            }
        });
    }
}
