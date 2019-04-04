package com.yd.aidemo;

import io.reactivex.Flowable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author ZCB 2575490085@qq.com
 * Created on 2019/4/4.
 */
public interface CommonService {

    @POST("mkln/")
    Flowable<ResultBean> getResult(@Body InputBean inputBean);
}
