package com.mvp.forecasting.tool.service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WeatherForecast {

    @JsonProperty("Date")
    private Date date;

    @JsonProperty("MaxTemp")
    private double maxTemp;

    @JsonProperty("MinTemp")
    private double minTemp;

    @JsonProperty("Precip")
    private double precip;

    @JsonProperty("Source")
    private String Source;

}
