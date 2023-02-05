package com.mvp.forecasting.tool.service.service;

import com.mvp.forecasting.tool.service.model.Forecasts;
import com.mvp.forecasting.tool.service.model.SalesForecastRequest;

import java.text.ParseException;
import java.util.List;

public interface SalesForecastService {

    List<Forecasts> getAllForecasts();

    List<Forecasts> getForecastForLocation(String location);

    List<Forecasts> getForecastForDate(String startDate, String toDate) throws ParseException;

    List<Forecasts> timelineSalesForecasts(SalesForecastRequest salesForecastRequest) throws ParseException;

}
