package com.mvp.forecasting.tool.service.controller;

import com.google.gson.Gson;
import com.mvp.forecasting.tool.service.entity.Forecasts;
import com.mvp.forecasting.tool.service.model.SalesForecastRequest;
import com.mvp.forecasting.tool.service.service.SalesForecastService;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/sales-forecast-service/v1")
public class SalesForecastToolController {

    private Logger logger = LoggerFactory.getLogger(SalesForecastToolController.class);

    @Autowired
    private SalesForecastService salesForecastService;

    @GetMapping(path = "/all-forecasts", produces = "application/json")
    public ResponseEntity<Object> findTimelineWeatherData() {
        logger.info("=== Fetching all forecast data ===");
        List<Forecasts> forecastResponse = null;
        try {
            forecastResponse = salesForecastService.getAllForecasts();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Internal issue");
        }
        logger.info("=== Fetched all forecast data ===");
        return ResponseEntity.status(HttpStatus.OK).body(new JSONArray(forecastResponse).toString());
    }

    @GetMapping(path = "/location-based-forecasts", produces = "application/json")
    public ResponseEntity<Object> findTimelineWeatherDataForLocation(@RequestParam(value = "location") String location) {
        logger.info("=== Fetching location based forecast data ===");
        List<Forecasts> forecastResponseForLocation = null;
        try {
            forecastResponseForLocation = salesForecastService.getForecastForLocation(location);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Internal issue");
        }
        logger.info("=== Fetched location based forecast data ===");
        return ResponseEntity.status(HttpStatus.OK).body(new JSONArray(forecastResponseForLocation).toString());
    }

    @GetMapping(path = "/date-based-forecasts", produces = "application/json")
    public ResponseEntity<Object> findTimelineWeatherDataForDates(@RequestParam(value = "fromDate") String fromDate,
                                                                  @RequestParam(value = "toDate") String toDate) {
        logger.info("=== Fetching date based forecast data ===");
        List<Forecasts> forecastResponseForDate = null;
        try {
            forecastResponseForDate = salesForecastService.getForecastForDate(fromDate, toDate);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Internal issue");
        }
        logger.info("=== Fetched date based forecast data ===");
        return ResponseEntity.status(HttpStatus.OK).body(new JSONArray(forecastResponseForDate).toString());
    }

    @PostMapping(path = "/sales-forecasts", produces = "application/json")
    public ResponseEntity<Object> findTimelineSalesData(@RequestBody SalesForecastRequest salesForecastRequest) {
        logger.info(salesForecastRequest.toString());
        logger.info("=== Fetching weather forecast data ===");
        List<Forecasts> weatherForecastResult = null;
        try {
            weatherForecastResult = salesForecastService.timelineSalesForecasts(salesForecastRequest);
        } catch (Exception e) {
            logger.error(e.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Internal issue");
        }
        logger.info("=== Fetched weather forecast data ===");
        return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(weatherForecastResult).toString());
    }

}
