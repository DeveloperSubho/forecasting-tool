package com.mvp.forecasting.tool.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.mvp.forecasting.tool.service.entity.Forecasts;
import com.mvp.forecasting.tool.service.model.SalesForecastRequest;
import com.mvp.forecasting.tool.service.model.WeatherForecastRequest;
import com.mvp.forecasting.tool.service.service.SalesForecastService;
import com.mvp.forecasting.tool.service.service.WeatherForecastService;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(SalesForecastToolController.class)
public class SalesForecastToolControllerTest {

    private Logger logger = LoggerFactory.getLogger(SalesForecastToolControllerTest.class);

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SalesForecastService salesForecastServiceMock;

    private List<Forecasts> salesForecast = new ArrayList<>();

    private JSONObject inputObject;

    @BeforeEach
    public void init() throws JSONException, ParseException {
        logger.info("Startup");
        ObjectMapper objectMapper = new ObjectMapper();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);

        salesForecast.add(new Forecasts(formatter.parse("2023-02-05 00:00:00"),
                "Hamburg",394.1765034648572));
        salesForecast.add(new Forecasts(formatter.parse("2023-02-06 00:00:00"),
                "Hamburg",479.01574214914166));

        inputObject = new JSONObject("{\n" +
                "    \"location\": \"Hamburg\",\n" +
                "    \"startDate\": \"2023-02-05\",\n" +
                "    \"endDate\": \"2023-02-07\"\n" +
                "}");
    }

    @Test
    public void givenCorrectPICall_whenWeatherForecastsIsReceived_then200IsReceived() throws Exception {

        // Given
        when(salesForecastServiceMock.timelineSalesForecasts(any(SalesForecastRequest.class)))
                .thenReturn(salesForecast);

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/sales-forecast-service/v1/sales-forecasts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(inputObject)))
                .andReturn();

        int status = result.getResponse().getStatus();
        logger.info("Status: "+ status);

        // Then
        assertEquals(HttpStatus.SC_OK, status);

    }

    @Test
    public void givenWrongAPICall_whenWeatherForecastsIsReceived_then404IsReceived() throws Exception {

        // Given
        when(salesForecastServiceMock.timelineSalesForecasts(any(SalesForecastRequest.class)))
                .thenReturn(salesForecast);

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/forecasting-weather-service/v1/weather-forecast")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(inputObject)))
                .andReturn();

        int status = result.getResponse().getStatus();
        logger.info("Status: "+ status);

        // Then
        assertEquals(HttpStatus.SC_NOT_FOUND, status);
    }

    @Test
    public void givenPostAPICall_whenSalesForecastsIsReceived_thenValidJSONIsReceived() throws Exception {

        // Given
        when(salesForecastServiceMock.timelineSalesForecasts(any(SalesForecastRequest.class)))
                .thenReturn(salesForecast);

        // When
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .post("/sales-forecast-service/v1/sales-forecasts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(inputObject)))
                .andReturn();

        int status = result.getResponse().getStatus();
        logger.info("Status: "+ status);

        // Then
        JSONAssert.assertEquals(new Gson().toJson(salesForecast), result.getResponse()
                .getContentAsString(), true);    }

}
