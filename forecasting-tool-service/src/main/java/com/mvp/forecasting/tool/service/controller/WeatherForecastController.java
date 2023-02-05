package com.mvp.forecasting.tool.service.controller;

import com.mvp.forecasting.tool.service.model.Forecasts;
import com.mvp.forecasting.tool.service.model.WeatherForecastRequest;
import com.mvp.forecasting.tool.service.service.WeatherForecastService;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller is for fetching weather forecast for a particular timeline
 *
 * @author Subhajit
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/forecasting-weather-service/v1")
public class WeatherForecastController {

    private Logger logger = LoggerFactory.getLogger(WeatherForecastController.class);

    @Autowired
    private WeatherForecastService weatherForecastService;


    @PostMapping(path = "/weather-forecasts", produces = "application/json")
    public ResponseEntity<Object> findTimelineWeatherData(@RequestBody WeatherForecastRequest weatherForecastRequest) {
        logger.info(weatherForecastRequest.toString());
        logger.info("=== Fetching weather forecast data ===");
        String weatherForecastResult;
        try {
            weatherForecastResult = weatherForecastService.timelineRequestHttpClient(weatherForecastRequest);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Internal issue");
        }
        logger.info("=== Fetched weather forecast data ===");
        return ResponseEntity.status(HttpStatus.OK).body(weatherForecastResult);
    }

}
