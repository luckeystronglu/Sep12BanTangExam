package com.qf.util;

import com.qf.entity.HomeHeadEntity;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by lenovo on 2016/9/11.
 */
public interface JsonUtil {


    @GET("recommend/operationElement?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=&oauth_token=&track_deviceid=352284040348801&track_device_info=SCH-I879&channel_name=sougou&app_installtime=1473471392&app_versions=5.8.8&os_versions=4.4.2&screensize=1080&v=19")
    Call<HomeHeadEntity> getpicDatas();

//    @GET("recommend/index?app_id=com.jzyd.BanTang&client_id=bt_app_android&client_secret=ffcda7a1c4ff338e05c42e7972ba7b8d&track_user_id=&oauth_token=&track_deviceid=861759037795209&track_device_info=EVA-AL00&channel_name=_360&app_installtime=1473560687&app_versions=5.8.7&os_versions=6.0&screensize=1080&v=19&page=0&pagesize=20&app_open_count=1&update_time=0&last_get_time=0")
//    Call<SelectEntity.DataBean.TopicBean> getSelectDatas();


}
