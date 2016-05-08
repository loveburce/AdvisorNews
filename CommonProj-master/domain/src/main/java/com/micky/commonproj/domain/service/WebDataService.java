package com.micky.commonproj.domain.service;


import com.micky.commonproj.domain.service.response.WeatherResponse;

import org.w3c.dom.UserDataHandler;

import java.util.List;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @Project CommonProject
 * @Packate com.micky.commonproj.data.api
 * @Description
 * @Author Micky Liu
 * @Email mickyliu@126.com
 * @Date 2015-12-21 17:22
 * @Version 1.0
 */
public interface WebDataService {

    /*@GET("service/getIpInfo.php")
    Call<GetIpInfoResponse> getIpInfo(@Query("ip") String ip);*/

//    @GET("service/getIpInfo.php")
//    Observable<GetIpInfoResponse> getIpInfo(@Query("ip") String ip);


    //http://api.map.baidu.com/telematics/v3/weather?location=%E6%88%90%E9%83%BD&output=json&ak=MPDgj92wUYvRmyaUdQs1XwCf
    @POST("/telematics/v3/weather?output=json")
    Observable<WeatherResponse> getWeatherInfo(@Query("location") String location, @Query("ak") String ak);

    public static final String host = "http://c.m.163.com/";
    public static final String endUrl = "-20.html";
    public static final String endDetailUrl = "/full.html";
    // 头条
    public static final String TopUrl = host + "nc/article/headline/";
    public static final String TopId = "T1348647909107";



    @POST("http://c.m.163.com/nc/article/headline/")
    Observable<WeatherResponse> getNewsInfo(@Query("location") String location, @Query("ak") String ak);


}
