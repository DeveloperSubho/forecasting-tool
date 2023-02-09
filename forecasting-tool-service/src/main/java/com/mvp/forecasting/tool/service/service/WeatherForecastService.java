package com.mvp.forecasting.tool.service.service;

import com.mvp.forecasting.tool.service.model.WeatherForecastRequest;

public interface WeatherForecastService {

    String timelineRequestHttpClient(WeatherForecastRequest weatherForecastRequest) throws Exception;

}
