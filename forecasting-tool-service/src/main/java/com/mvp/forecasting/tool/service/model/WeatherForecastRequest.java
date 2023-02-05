package com.mvp.forecasting.tool.service.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherForecastRequest {

    private String location;
    private String startDate;
    private String endDate;
}
