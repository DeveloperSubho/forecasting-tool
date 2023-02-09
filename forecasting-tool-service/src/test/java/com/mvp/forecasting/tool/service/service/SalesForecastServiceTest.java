package com.mvp.forecasting.tool.service.service;

import com.mvp.forecasting.tool.service.entity.Forecasts;
import com.mvp.forecasting.tool.service.model.SalesForecastRequest;
import com.mvp.forecasting.tool.service.repository.ForecastRepository;
import com.mvp.forecasting.tool.service.service.impl.SalesForecastServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SalesForecastServiceTest {

    @Mock
    private ForecastRepository forecastRepository;

    @InjectMocks
    private SalesForecastServiceImpl salesForecastService;

    private Forecasts forecasts;

    private List<Forecasts> listOfForecasts = new ArrayList<>();

    private SalesForecastRequest salesForecastRequest;

    @BeforeEach
    public void setup() throws ParseException, JSONException {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        forecasts = forecasts.builder()
                .date(formatter.parse("2023-02-05 00:00:00"))
                .location("Hamburg")
                .forecasted_sales_quantity(394.1765034648572)
                .build();

        listOfForecasts.add(forecasts);

        salesForecastRequest = salesForecastRequest.builder()
                .location("Hamburg")
                .startDate("2023-02-05")
                .endDate("2023-02-07")
                .build();

    }

    // JUnit test for saveEmployee method
    @DisplayName("JUnit test for timelineSalesForecasts method")
    @Test
    public void givenSalesForecastRequest_whenFetchTimelineSalesForecasts_thenReturnForecastList() throws ParseException {
        // given - precondition or setup
        when(forecastRepository.getForecastForLocationAndDate(any(String.class), any(Date.class), any(Date.class)))
                .thenReturn(listOfForecasts);

        // when -  action or the behaviour that we are going test
        List<Forecasts> fetchTimeline = salesForecastService.timelineSalesForecasts(salesForecastRequest);

        // then - verify the output
        assertThat(fetchTimeline).isNotNull();
        assertEquals(fetchTimeline, listOfForecasts);
    }

    // JUnit test for timelineSalesForecasts method
    @DisplayName("JUnit test for timelineSalesForecasts method (negative scenario)")
    @Test
    public void givenSalesForecastRequest_whenFetchTimelineSalesForecasts_thenReturnEmptyForecastList() throws ParseException {
        // given - precondition or setup
        when(forecastRepository.getForecastForLocationAndDate(any(String.class), any(Date.class), any(Date.class)))
                .thenReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<Forecasts> fetchTimeline = salesForecastService.timelineSalesForecasts(salesForecastRequest);

        // then - verify the output
        assertThat(fetchTimeline).isEmpty();
        assertThat(fetchTimeline.size()).isEqualTo(0);
    }


}