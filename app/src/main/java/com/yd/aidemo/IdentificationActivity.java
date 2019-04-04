package com.yd.aidemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class IdentificationActivity extends AppCompatActivity {

    private ImageView photo;
    private ImageView back;
    private Button next;

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
                final InputBean inputBean = new InputBean();
                inputBean.setProjectPersonnels(list1);
                inputBean.setSubcontractingUnits(list);
                inputBean.setVoiceText("5栋4层消防连廊处未搭设临边防护，存在高空坠落风险，张三应督促深圳蒹葭劳务公司马上整改，逾期未整改将按照合同规定进行处罚");

                RetrofitFactory.getService().getResult(inputBean)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<ResultBean>() {
                        @Override
                        public void accept(ResultBean resultBean) throws Exception {
                            Log.d("data", resultBean.toString());
                            Intent intent = new Intent(IdentificationActivity.this, RectificationActivity.class);
                            intent.putExtra("data", resultBean);
                            startActivity(intent);
                            finish();
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.d("data", throwable.getMessage());
                        }
                    });
            }
        });

    }
}
