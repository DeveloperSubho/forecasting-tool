package com.mvp.forecasting.tool.service.service;

import com.mvp.forecasting.tool.service.model.WeatherForecast;
import com.mvp.forecasting.tool.service.model.WeatherForecastRequest;

import java.util.List;

public interface WeatherForecastService {

    String timelineRequestHttpClient(WeatherForecastRequest weatherForecastRequest) throws Exception;

}
