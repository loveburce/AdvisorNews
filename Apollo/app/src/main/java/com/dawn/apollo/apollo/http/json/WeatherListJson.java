package com.dawn.apollo.apollo.http.json;

import android.content.Context;

import com.dawn.apollo.apollo.bean.WeatherModel;
import com.dawn.apollo.apollo.utils.TimeUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/1.
 */
public class WeatherListJson extends JsonPacket{
    public List<WeatherModel> weatherListModles = new ArrayList<WeatherModel>();

    public static WeatherListJson weatherListJson;

    public WeatherListJson(Context context) {
        super(context);
    }

    public static WeatherListJson instance(Context context) {
        if (weatherListJson == null) {
            weatherListJson = new WeatherListJson(context);
        }

        return weatherListJson;
    }

    public List<WeatherModel> readJsonWeatherListModles(String res) {
        weatherListModles.clear();
        try {
            if (res == null || res.equals("")) {
                return null;
            }
            WeatherModel weatherModel = null;
            JSONObject jsonObject = new JSONObject(res);
            JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("forecast");
            for (int i = 0; i < jsonArray.length(); i++) {
                weatherModel = readJsonWeatherModles(jsonArray.getJSONObject(i));
                weatherListModles.add(weatherModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.gc();
        }
        return weatherListModles;
    }

    public List<WeatherModel> readJsonweatherListModles(String res) {
        weatherListModles.clear();
        try {
            if (res == null || res.equals("")) {
                return null;
            }
            WeatherModel weatherModel = null;
            JSONObject jsonObject = new JSONObject(res);
            JSONObject jsonArray = jsonObject.getJSONObject("result").getJSONObject("future");
            for (int i = 0; i < 7; i++) {
                weatherModel = readJsonWeatherModles(jsonArray.getJSONObject("day_"
                        + TimeUtils.dateToWeek(i)));
                weatherListModles.add(weatherModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.gc();
        }
        return weatherListModles;
    }

    private WeatherModel readJsonWeatherModles(JSONObject jsonObject) throws Exception {
        WeatherModel weatherModel = null;

        String temperature = "";
        String weather = "";
        String wind = "";
        String date = "";

        temperature = getString("high", jsonObject) + " " + getString("low", jsonObject);
        weather = getString("type", jsonObject);
        wind = getString("fengxiang", jsonObject);
        date = getString("date", jsonObject);

        weatherModel = new WeatherModel();

        weatherModel.setDate(TimeUtils.getCurrentTime() + date);
        weatherModel.setTemperature(temperature);
        weatherModel.setWeather(weather);
        weatherModel.setWeek("");
        weatherModel.setWind(wind);

        return weatherModel;
    }

    private WeatherModel readJsonWeatherModel(JSONObject jsonObject) throws Exception {

        WeatherModel weatherModel = null;

        String temperature = "";
        String weather = "";
        String wind = "";
        String week = "";
        String date = "";

        temperature = getString("temperature", jsonObject);
        weather = getString("weather", jsonObject);
        wind = getString("wind", jsonObject);
        week = getString("week", jsonObject);
        date = getString("date", jsonObject);

        weatherModel = new WeatherModel();

        weatherModel.setDate(date);
        weatherModel.setTemperature(temperature);
        weatherModel.setWeather(weather);
        weatherModel.setWeek(week);
        weatherModel.setWind(wind);

        return weatherModel;
    }
}
