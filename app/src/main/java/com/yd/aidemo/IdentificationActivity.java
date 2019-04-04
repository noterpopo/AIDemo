package com.yd.aidemo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.baidu.aip.asrwakeup3.core.recog.listener.ChainRecogListener;
import com.baidu.aip.asrwakeup3.core.recog.listener.MessageStatusRecogListener;
import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;
import com.baidu.voicerecognition.android.ui.DigitalDialogInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class IdentificationActivity extends ActivityAbstractRecog {

    private DigitalDialogInput input;
    private ChainRecogListener chainRecogListener;
    private static String TAG = "IdentificationActivity";
    private ImageView photo;
    private ImageView back;
    private Button next;
    private Button voiceButton;
    final InputBean inputBean = new InputBean();

    public IdentificationActivity() {
        super();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);
        photo = findViewById(R.id.photo);
        Bitmap bitmap = getIntent().getParcelableExtra("photo");
        photo.setImageBitmap(bitmap);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        next = findViewById(R.id.next_step);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<SubcontractingUnitsBean> list = new ArrayList<>();
                list.add(new SubcontractingUnitsBean(1011, "深圳蒹葭劳务公司"));
                list.add(new SubcontractingUnitsBean(1012, "广州硕鼠劳务公司"));
                list.add(new SubcontractingUnitsBean(1013, "成都桃夭劳务公司"));
                List<PersonBean> list1 = new ArrayList<>();
                list1.add(new PersonBean(10, "张三"));
                list1.add(new PersonBean(11, "李四"));
                list1.add(new PersonBean(12, "王五"));
                list1.add(new PersonBean(13, "赵六"));
                list1.add(new PersonBean(14, "钱七"));
                list1.add(new PersonBean(15, "郑闪"));
                inputBean.setProjectPersonnels(list1);
                inputBean.setSubcontractingUnits(list);
                // inputBean.setVoiceText("5栋4层消防连廊处未搭设临边防护，存在高空坠落风险，张三应督促深圳蒹葭劳务公司马上整改，逾期未整改将按照合同规定进行处罚");
                Log.d(TAG, inputBean.getVoiceText());
                RetrofitFactory.getService().getResult(inputBean)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResultBean>() {
                        @Override
                        public void accept(ResultBean resultBean) throws Exception {
                            Log.d(TAG, resultBean.toString());
                            Intent intent = new Intent(IdentificationActivity.this, RectificationActivity.class);
                            intent.putExtra("data", resultBean);
                            startActivity(intent);
                            finish();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.d(TAG, throwable.getMessage());
                        }
                    });
            }
        });

        voiceButton=findViewById(R.id.vcbtn);
        voiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start();
            }
        });

        /**
         * 有2个listner，一个是用户自己的业务逻辑，如MessageStatusRecogListener。另一个是UI对话框的。
         * 使用这个ChainRecogListener把两个listener和并在一起
         */
        chainRecogListener = new ChainRecogListener();
        // DigitalDialogInput 输入 ，MessageStatusRecogListener可替换为用户自己业务逻辑的listener
        chainRecogListener.addListener(new MessageStatusRecogListener(handler));
        myRecognizer.setEventListener(chainRecogListener); // 替换掉原来的listener

    }



    /**
     * 开始录音，点击“开始”按钮后调用。
     */
    @Override
    protected void start() {
        // 此处params可以打印出来，直接写到你的代码里去，最终的json一致即可。
        final Map<String, Object> params = fetchParams();

        // BaiduASRDigitalDialog的输入参数
        input = new DigitalDialogInput(myRecognizer, chainRecogListener, params);
        BaiduASRDigitalDialog.setInput(input); // 传递input信息，在BaiduASRDialog中读取,
        Intent intent = new Intent(this, BaiduASRDigitalDialog.class);

        // 修改对话框样式
        // intent.putExtra(BaiduASRDigitalDialog.PARAM_DIALOG_THEME, BaiduASRDigitalDialog.THEME_ORANGE_DEEPBG);


        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i(TAG, "requestCode" + requestCode);
        if (requestCode == 2) {
            String message = "";
            if (resultCode == RESULT_OK) {
                ArrayList results = data.getStringArrayListExtra("results");
                if (results != null && results.size() > 0) {
                    message += results.get(0);
                }
            } else {
                message += "没有结果";
            }
            inputBean.setVoiceText(message);
        }

    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause");
        super.onPause();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myRecognizer.release();
    }
}
