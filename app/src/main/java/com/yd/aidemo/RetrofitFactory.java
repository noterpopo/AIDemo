package com.yd.aidemo;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author ZCB 2575490085@qq.com
 * Created on 2019/4/4.
 */
public class RetrofitFactory {

    private static String baseUrl = "http://119.23.239.177/";

    public static CommonService getService(){
        return new Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(CommonService.class);
    }

}
