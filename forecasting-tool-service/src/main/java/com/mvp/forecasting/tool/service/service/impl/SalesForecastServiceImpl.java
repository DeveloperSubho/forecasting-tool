package com.mvp.forecasting.tool.service.service.impl;

import com.mvp.forecasting.tool.service.model.Forecasts;
import com.mvp.forecasting.tool.service.model.SalesForecastRequest;
import com.mvp.forecasting.tool.service.repository.ForecastRepository;
import com.mvp.forecasting.tool.service.service.SalesForecastService;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class SalesForecastServiceImpl implements SalesForecastService {

    private Logger logger = LoggerFactory.getLogger(SalesForecastServiceImpl.class);

    @Autowired
    private ForecastRepository forecastRepository;

    @Override
    public List<Forecasts> getAllForecasts() {
        List<Forecasts> allForecastData= forecastRepository.getAllForecasts();
        logger.info("=== Data ===" + new JSONArray(allForecastData).toString());
        return allForecastData;
    }

    @Override
    public List<Forecasts> getForecastForLocation(String location) {
        List<Forecasts> allForecastData= forecastRepository.getForecastForLocation(location);
        logger.info("=== Data ===" + new JSONArray(allForecastData).toString());
        return allForecastData;
    }

    public List<Forecasts> getForecastForDate(String startDate, String endDate) throws ParseException {
        logger.info("=== From Date ===" + startDate);
        logger.info("=== To Date ===" + endDate);
        List<Forecasts> allForecastData= forecastRepository.getForecastForDate(new SimpleDateFormat("yyyy-MM-dd").parse(startDate), new SimpleDateFormat("yyyy-MM-dd").parse(endDate));
        logger.info("=== Data ===" + new JSONArray(allForecastData).toString());
        return allForecastData;
    }

    public List<Forecasts> timelineSalesForecasts(SalesForecastRequest salesForecastRequest) throws ParseException {
        logger.info("=== Fetching sales forecast for location and date range ===");
        List<Forecasts> allForecastData=
                forecastRepository.getForecastForLocationAndDate(salesForecastRequest.getLocation(),
                        new SimpleDateFormat("yyyy-MM-dd").parse(salesForecastRequest.getStartDate()),
                        new SimpleDateFormat("yyyy-MM-dd").parse(salesForecastRequest.getEndDate()));
        logger.info("=== Data ===" + new JSONArray(allForecastData).toString());
        return allForecastData;
    }

}
